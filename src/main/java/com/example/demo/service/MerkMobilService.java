package com.example.demo.service;

import com.example.demo.entity.MerkMobil;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MerkMobilService {
    ResponseEntity CreateMobil(MerkMobil merkMobil);
    List<MerkMobil> FindAll();

    ResponseEntity DeleteOne(Long id);

    ResponseEntity UpdateOne(Long id, MerkMobil updatedData);
}
