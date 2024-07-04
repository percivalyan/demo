package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/jadwal-pelajaran")
public class JadwalPelajaranController {

    private final JadwalPelajaranService jadwalPelajaranService;

    @Autowired
    public JadwalPelajaranController(JadwalPelajaranService jadwalPelajaranService) {
        this.jadwalPelajaranService = jadwalPelajaranService;
    }

    // Endpoint untuk menyimpan jadwal pelajaran
    @PostMapping
    public ResponseEntity<JadwalPelajaran> saveJadwalPelajaran(@RequestBody JadwalPelajaran jadwalPelajaran) {
        JadwalPelajaran savedJadwalPelajaran = jadwalPelajaranService.saveJadwalPelajaran(jadwalPelajaran);
        return new ResponseEntity<>(savedJadwalPelajaran, HttpStatus.CREATED);
    }

    // Endpoint untuk mengambil semua jadwal pelajaran
    @GetMapping
    public ResponseEntity<List<JadwalPelajaran>> getAllJadwalPelajaran() {
        List<JadwalPelajaran> jadwalPelajaranList = jadwalPelajaranService.getAllJadwalPelajaran();
        return new ResponseEntity<>(jadwalPelajaranList, HttpStatus.OK);
    }

    // Endpoint untuk mengambil jadwal pelajaran berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<JadwalPelajaran> getJadwalPelajaranById(@PathVariable("id") int id) {
        JadwalPelajaran jadwalPelajaran = jadwalPelajaranService.getJadwalPelajaranById(id);
        return new ResponseEntity<>(jadwalPelajaran, HttpStatus.OK);
    }

    // Endpoint untuk menghapus jadwal pelajaran berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJadwalPelajaran(@PathVariable("id") int id) {
        jadwalPelajaranService.deleteJadwalPelajaran(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint untuk menambahkan siswa ke jadwal pelajaran berdasarkan ID jadwal pelajaran dan ID siswa
    @PostMapping("/{jadwalPelajaranId}/siswa/{siswaId}")
    public ResponseEntity<JadwalPelajaran> addSiswaToJadwalPelajaran(
            @PathVariable("jadwalPelajaranId") int jadwalPelajaranId,
            @PathVariable("siswaId") int siswaId) {
        JadwalPelajaran updatedJadwalPelajaran = jadwalPelajaranService.addSiswaToJadwalPelajaran(jadwalPelajaranId, siswaId);
        return new ResponseEntity<>(updatedJadwalPelajaran, HttpStatus.OK);
    }

    // Endpoint untuk menghapus siswa dari jadwal pelajaran berdasarkan ID jadwal pelajaran dan ID siswa
    @DeleteMapping("/{jadwalPelajaranId}/siswa/{siswaId}")
    public ResponseEntity<JadwalPelajaran> removeSiswaFromJadwalPelajaran(
            @PathVariable("jadwalPelajaranId") int jadwalPelajaranId,
            @PathVariable("siswaId") int siswaId) {
        JadwalPelajaran updatedJadwalPelajaran = jadwalPelajaranService.removeSiswaFromJadwalPelajaran(jadwalPelajaranId, siswaId);
        return new ResponseEntity<>(updatedJadwalPelajaran, HttpStatus.OK);
    }
}
