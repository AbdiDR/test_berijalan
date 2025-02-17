package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "merkmobil")
@Setter
@Getter
public class MerkMobil {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String merk;
    private String tipe;
    private Integer tahun;

}
