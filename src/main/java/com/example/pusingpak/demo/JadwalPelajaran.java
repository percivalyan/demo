package com.example.pusingpak.demo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "jadwal_pelajaran")
@Data
@NoArgsConstructor
public class JadwalPelajaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "id_siswa")
    private int idSiswa;

    // @ManyToMany
    // @JoinTable(
    //     name = "jadwal_pelajaran_mapel",
    //     joinColumns = @JoinColumn(name = "id_jadwal"),
    //     inverseJoinColumns = @JoinColumn(name = "id_mapel")
    // )
    // private List<MataPelajaran> mataPelajaran;

    @ManyToMany
    @JoinTable(
        name = "jadwal_pelajaran_siswa",
        joinColumns = @JoinColumn(name = "id_siswa"),
        inverseJoinColumns = @JoinColumn(name = "id_mapel")
    )
    private List<Siswa> siswa;

    @Column(name = "id_guru")
    private int idGuru;

    @Column(name = "id_kelas")
    private int idKelas;

    @Column(name = "hari")
    private Hari hari;

    @Column(name = "jam_mulai")
    private int jamMulai;

    @Column(name = "jam_selesai")
    private int jamSelesai;

    public enum Hari {
        SENIN, SELASA, RABU, KAMIS, JUMAT, SABTU
    }

    // Constructors, getters, and setters
}
