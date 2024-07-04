package com.example.pusingpak.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tahun_ajaran")
public class TahunAjaran {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tahun", nullable = false)
    private String tahun;

    @Column(name = "periode", nullable = false)
    private int periode;

    @Temporal(TemporalType.DATE)
    @Column(name = "tglMulai", nullable = false)
    private Date tglMulai;

    @Temporal(TemporalType.DATE)
    @Column(name = "tglAkhir", nullable = false)
    private Date tglAkhir;

    @Column(name = "kurikulum", nullable = false)
    private String kurikulum;
}
