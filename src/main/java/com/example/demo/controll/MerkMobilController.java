package com.example.demo.controll;

import com.example.demo.entity.MerkMobil;
import com.example.demo.service.MerkMobilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mobil")
public class MerkMobilController {
    private  final MerkMobilService merkMobilService;

    public MerkMobilController(MerkMobilService merkMobilService) {
        this.merkMobilService = merkMobilService;
    }

    @PostMapping
    public ResponseEntity<MerkMobil> createMobil(@RequestBody MerkMobil merkMobil) {

        return merkMobilService.CreateMobil(merkMobil);
    }

    @GetMapping
    public List<MerkMobil> findAll(){

        return merkMobilService.FindAll();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<MerkMobil> deleteOne(@PathVariable Long id){

        return merkMobilService.DeleteOne(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody MerkMobil updatedData) {
        return merkMobilService.UpdateOne(id, updatedData);
    }

}
