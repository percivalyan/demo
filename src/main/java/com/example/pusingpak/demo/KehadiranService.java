package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KehadiranService {

    private final KehadiranRepository kehadiranRepository;
    private final SiswaRepository siswaRepository;

    @Autowired
    public KehadiranService(KehadiranRepository kehadiranRepository, SiswaRepository siswaRepository) {
        this.kehadiranRepository = kehadiranRepository;
        this.siswaRepository = siswaRepository;
    }

    // Simpan Kehadiran
    public Kehadiran simpanKehadiran(Kehadiran kehadiran) {
        return kehadiranRepository.save(kehadiran);
    }

    // Ambil semua Kehadiran
    public List<Kehadiran> semuaKehadiran() {
        return kehadiranRepository.findAll();
    }

    // Ambil Kehadiran berdasarkan ID
    public Optional<Kehadiran> getKehadiranById(int id) {
        return kehadiranRepository.findById(id);
    }

    // Hapus Kehadiran berdasarkan ID
    public void hapusKehadiran(int id) {
        kehadiranRepository.deleteById(id);
    }

    // Update Kehadiran
    public Kehadiran updateKehadiran(int id, Kehadiran kehadiranBaru) {
        return kehadiranRepository.findById(id)
                .map(kehadiran -> {
                    kehadiran.setTanggal(kehadiranBaru.getTanggal());
                    kehadiran.setStatus(kehadiranBaru.getStatus());
                    kehadiran.setKehadiran(kehadiranBaru.getKehadiran());
                    kehadiran.setKeterangan(kehadiranBaru.getKeterangan());
                    kehadiran.setSiswa(kehadiranBaru.getSiswa());
                    return kehadiranRepository.save(kehadiran);
                })
                .orElseGet(() -> {
                    kehadiranBaru.setId(id);
                    return kehadiranRepository.save(kehadiranBaru);
                });
    }

    // Ambil Siswa dari Kehadiran berdasarkan ID Kehadiran
    public Optional<Siswa> getSiswaKehadiran(int idKehadiran) {
        return getKehadiranById(idKehadiran)
                .map(Kehadiran::getSiswa);
    }

    // Simpan Siswa untuk Kehadiran berdasarkan ID Kehadiran
    public Kehadiran simpanSiswaKehadiran(int idKehadiran, Siswa siswa) {
        return getKehadiranById(idKehadiran)
                .map(kehadiran -> {
                    kehadiran.setSiswa(siswa);
                    return kehadiranRepository.save(kehadiran);
                })
                .orElseThrow(() -> new RuntimeException("Kehadiran dengan ID " + idKehadiran + " tidak ditemukan"));
    }

    // Hapus Siswa dari Kehadiran berdasarkan ID Kehadiran
    public Kehadiran hapusSiswaKehadiran(int idKehadiran) {
        return getKehadiranById(idKehadiran)
                .map(kehadiran -> {
                    kehadiran.setSiswa(null);
                    return kehadiranRepository.save(kehadiran);
                })
                .orElseThrow(() -> new RuntimeException("Kehadiran dengan ID " + idKehadiran + " tidak ditemukan"));
    }
}
