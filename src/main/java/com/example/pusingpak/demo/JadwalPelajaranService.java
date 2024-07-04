package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JadwalPelajaranService {

    private final JadwalPelajaranRepository jadwalPelajaranRepository;
    private final SiswaRepository siswaRepository;

    @Autowired
    public JadwalPelajaranService(JadwalPelajaranRepository jadwalPelajaranRepository, SiswaRepository siswaRepository) {
        this.jadwalPelajaranRepository = jadwalPelajaranRepository;
        this.siswaRepository = siswaRepository;
    }

    // Menyimpan jadwal pelajaran
    public JadwalPelajaran saveJadwalPelajaran(JadwalPelajaran jadwalPelajaran) {
        return jadwalPelajaranRepository.save(jadwalPelajaran);
    }

    // Mengambil semua jadwal pelajaran
    public List<JadwalPelajaran> getAllJadwalPelajaran() {
        return jadwalPelajaranRepository.findAll();
    }

    // Mengambil jadwal pelajaran berdasarkan ID
    public JadwalPelajaran getJadwalPelajaranById(int id) {
        return jadwalPelajaranRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Jadwal pelajaran dengan ID " + id + " tidak ditemukan"));
    }

    // Menghapus jadwal pelajaran berdasarkan ID
    public void deleteJadwalPelajaran(int id) {
        jadwalPelajaranRepository.deleteById(id);
    }

    // Menambahkan siswa ke jadwal pelajaran
    public JadwalPelajaran addSiswaToJadwalPelajaran(int jadwalPelajaranId, int siswaId) {
        JadwalPelajaran jadwalPelajaran = getJadwalPelajaranById(jadwalPelajaranId);
        Siswa siswa = siswaRepository.findById(siswaId)
                .orElseThrow(() -> new IllegalArgumentException("Siswa dengan ID " + siswaId + " tidak ditemukan"));
        jadwalPelajaran.getSiswa().add(siswa);
        return jadwalPelajaranRepository.save(jadwalPelajaran);
    }

    // Menghapus siswa dari jadwal pelajaran
    public JadwalPelajaran removeSiswaFromJadwalPelajaran(int jadwalPelajaranId, int siswaId) {
        JadwalPelajaran jadwalPelajaran = getJadwalPelajaranById(jadwalPelajaranId);
        Siswa siswa = siswaRepository.findById(siswaId)
                .orElseThrow(() -> new IllegalArgumentException("Siswa dengan ID " + siswaId + " tidak ditemukan"));
        jadwalPelajaran.getSiswa().remove(siswa);
        return jadwalPelajaranRepository.save(jadwalPelajaran);
    }
}
