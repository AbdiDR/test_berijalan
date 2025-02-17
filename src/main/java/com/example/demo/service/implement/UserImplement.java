package com.example.demo.service.implement;

import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JWTGenerator;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserImplement implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTGenerator jwtGenerator;


    @Override
    public User CreateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> Login(User user) {
        // Mencari user berdasarkan username
        System.out.println("masuk");
        Optional<User> dataUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));

        // Jika user tidak ditemukan
        if (!dataUser.isPresent()) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        System.out.println("lewat isPresent");

        // Cek apakah password yang dimasukkan cocok dengan password di database
        if (!user.getPassword().equals(dataUser.get().getPassword())) {
            return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
        }
        System.out.println("lewat pengecekan");

        // Menggunakan JWTGenerator untuk membuat token
        String token = jwtGenerator.createJWT(dataUser.get().getUsername(), "Authentication");
        System.out.println("lewat jwtGenerator");

        // Mengembalikan response dengan token
        return ResponseEntity.ok(new LoginResponse(dataUser.get().getUsername(), token));
    }

}
