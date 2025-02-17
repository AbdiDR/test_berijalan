package com.example.demo.service.implement;

import com.example.demo.entity.MerkMobil;
import com.example.demo.repository.MerkMobilRepository;
import com.example.demo.service.MerkMobilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class MerkMobilImplement implements MerkMobilService {
    @Autowired
    MerkMobilRepository merkMobilRepository;
    @Override
    public ResponseEntity<MerkMobil> CreateMobil(@RequestBody MerkMobil merkMobil) {
        MerkMobil createdMobil = merkMobilRepository.save(merkMobil);
        return new ResponseEntity<>(createdMobil, HttpStatus.CREATED);
    }

    @Override
    public List<MerkMobil> FindAll() {
        return merkMobilRepository.findAll();
    }

    @Override
    public ResponseEntity DeleteOne(Long id) {
        MerkMobil dataMobil = new MerkMobil();
        dataMobil = merkMobilRepository.findById(id).get();
        if (dataMobil == null) {
            return new ResponseEntity<>("Mobil not found", HttpStatus.NOT_FOUND);
        }
        merkMobilRepository.delete(dataMobil);

        return new ResponseEntity(dataMobil, HttpStatus.OK);
    }

    @Override
    public ResponseEntity UpdateOne(Long id, MerkMobil updatedData) {
        Optional<MerkMobil> existingData = merkMobilRepository.findById(id);
        if (!existingData.isPresent()) {
            return new ResponseEntity<>("Mobil not found", HttpStatus.NOT_FOUND);
        }
        MerkMobil dataMobil = existingData.get();

        if (updatedData.getMerk() != null) {
            dataMobil.setMerk(updatedData.getMerk());
        }
        if (updatedData.getTahun() != null) {
            dataMobil.setTahun(updatedData.getTahun());
        }
        if (updatedData.getTipe() != null) {
            dataMobil.setTipe(updatedData.getTipe());
        }

        merkMobilRepository.save(dataMobil);
        return ResponseEntity.ok(dataMobil);
    }


}
