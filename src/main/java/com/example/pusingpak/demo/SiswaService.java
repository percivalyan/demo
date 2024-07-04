package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiswaService {

    private final SiswaRepository siswaRepository;
    private final TahunAjaranRepository tahunAjaranRepository;

    @Autowired
    public SiswaService(SiswaRepository siswaRepository, TahunAjaranRepository tahunAjaranRepository) {
        this.siswaRepository = siswaRepository;
        this.tahunAjaranRepository = tahunAjaranRepository;
    }

    // Simpan Siswa
    public Siswa simpanSiswa(Siswa siswa) {
        return siswaRepository.save(siswa);
    }

    // Ambil semua Siswa
    public List<Siswa> semuaSiswa() {
        return siswaRepository.findAll();
    }

    // Ambil Siswa berdasarkan ID
    public Optional<Siswa> getSiswaById(int id) {
        return siswaRepository.findById(id);
    }

    // Hapus Siswa berdasarkan ID
    public void hapusSiswa(int id) {
        siswaRepository.deleteById(id);
    }

    // Update Siswa
    public Siswa updateSiswa(int id, Siswa siswaBaru) {
        return siswaRepository.findById(id)
                .map(siswa -> {
                    siswa.setNisn(siswaBaru.getNisn());
                    siswa.setNama_lengkap(siswaBaru.getNama_lengkap());
                    siswa.setTanggal_lahir(siswaBaru.getTanggal_lahir());
                    siswa.setAlamat(siswaBaru.getAlamat());
                    siswa.setNama_ortu(siswaBaru.getNama_ortu());
                    siswa.setTelp(siswaBaru.getTelp());
                    siswa.setFoto(siswaBaru.getFoto());
                    siswa.setStatus(siswaBaru.isStatus());
                    return siswaRepository.save(siswa);
                })
                .orElseGet(() -> {
                    siswaBaru.setId(id);
                    return siswaRepository.save(siswaBaru);
                });
    }

    // Ambil Tahun Ajaran dari Siswa berdasarkan ID Siswa
    public Optional<TahunAjaran> getTahunAjaranSiswa(int idSiswa) {
        return getSiswaById(idSiswa)
                .map(Siswa::getTahunAjaran);
    }

    // Simpan Tahun Ajaran untuk Siswa berdasarkan ID Siswa
    public Siswa simpanTahunAjaranSiswa(int idSiswa, TahunAjaran tahunAjaran) {
        return getSiswaById(idSiswa)
                .map(siswa -> {
                    siswa.setTahunAjaran(tahunAjaran);
                    return siswaRepository.save(siswa);
                })
                .orElseThrow(() -> new RuntimeException("Siswa dengan ID " + idSiswa + " tidak ditemukan"));
    }

    // Hapus Tahun Ajaran dari Siswa berdasarkan ID Siswa
    public Siswa hapusTahunAjaranSiswa(int idSiswa) {
        return getSiswaById(idSiswa)
                .map(siswa -> {
                    siswa.setTahunAjaran(null);
                    return siswaRepository.save(siswa);
                })
                .orElseThrow(() -> new RuntimeException("Siswa dengan ID " + idSiswa + " tidak ditemukan"));
    }
}
