package com.example.pusingpak.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "siswa")
public class Siswa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_ta", referencedColumnName = "id")
    private TahunAjaran tahunAjaran;

    @Column(name = "nisn", nullable = false, unique = true)
    private String nisn;

    @Column(name = "nama_lengkap", nullable = false)
    private String nama_lengkap;

    @Temporal(TemporalType.DATE)
    @Column(name = "tanggal_lahir", nullable = false)
    private Date tanggal_lahir;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "nama_ortu", nullable = false)
    private String nama_ortu;

    @Column(name = "telp", nullable = false)
    private String telp;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "status", nullable = false)
    private boolean status;
}
