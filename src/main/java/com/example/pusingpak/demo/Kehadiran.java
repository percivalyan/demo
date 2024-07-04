package com.example.pusingpak.demo;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Kehadiran")
public class Kehadiran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_kehadiran")
    private int id;

    @Column(name = "tanggal")
    private String tanggal;

    @Column(name = "status")
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(name = "kehadiran_status")
    private KehadiranStatus kehadiran;

    @Column(name = "keterangan")
    private String keterangan;

    @ManyToOne
    @JoinColumn(name = "siswa_id")
    private Siswa siswa;

    public enum KehadiranStatus {
        HADIR, ABSEN, IJIN, SAKIT
    }
}
