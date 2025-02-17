package com.example.demo.repository;

import com.example.demo.entity.MerkMobil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerkMobilRepository extends JpaRepository<MerkMobil,Long> {
}
