package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/siswa")
public class SiswaController {

    private final SiswaService siswaService;

    @Autowired
    public SiswaController(SiswaService siswaService) {
        this.siswaService = siswaService;
    }

    // Endpoint untuk menyimpan Siswa
    @PostMapping
    public ResponseEntity<Siswa> simpanSiswa(@RequestBody Siswa siswa) {
        Siswa siswaBaru = siswaService.simpanSiswa(siswa);
        return ResponseEntity.status(HttpStatus.CREATED).body(siswaBaru);
    }

    // Endpoint untuk mengambil semua Siswa
    @GetMapping
    public ResponseEntity<List<Siswa>> semuaSiswa() {
        List<Siswa> daftarSiswa = siswaService.semuaSiswa();
        return ResponseEntity.ok(daftarSiswa);
    }

    // Endpoint untuk mengambil Siswa berdasarkan ID
    @GetMapping("/{id}")
    public ResponseEntity<Siswa> getSiswaById(@PathVariable("id") int id) {
        Optional<Siswa> siswa = siswaService.getSiswaById(id);
        return siswa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk menghapus Siswa berdasarkan ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hapusSiswa(@PathVariable("id") int id) {
        siswaService.hapusSiswa(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint untuk update Siswa berdasarkan ID
    @PutMapping("/{id}")
    public ResponseEntity<Siswa> updateSiswa(@PathVariable("id") int id, @RequestBody Siswa siswaBaru) {
        Siswa siswaUpdate = siswaService.updateSiswa(id, siswaBaru);
        return ResponseEntity.ok(siswaUpdate);
    }

    // Endpoint untuk mengambil Tahun Ajaran dari Siswa berdasarkan ID Siswa
    @GetMapping("/{id}/tahun-ajaran")
    public ResponseEntity<TahunAjaran> getTahunAjaranSiswa(@PathVariable("id") int idSiswa) {
        Optional<TahunAjaran> tahunAjaran = siswaService.getTahunAjaranSiswa(idSiswa);
        return tahunAjaran.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint untuk menyimpan Tahun Ajaran untuk Siswa berdasarkan ID Siswa
    @PostMapping("/{id}/tahun-ajaran")
    public ResponseEntity<Siswa> simpanTahunAjaranSiswa(@PathVariable("id") int idSiswa, @RequestBody TahunAjaran tahunAjaran) {
        Siswa siswaUpdate = siswaService.simpanTahunAjaranSiswa(idSiswa, tahunAjaran);
        return ResponseEntity.ok(siswaUpdate);
    }

    // Endpoint untuk menghapus Tahun Ajaran dari Siswa berdasarkan ID Siswa
    @DeleteMapping("/{id}/tahun-ajaran")
    public ResponseEntity<Siswa> hapusTahunAjaranSiswa(@PathVariable("id") int idSiswa) {
        Siswa siswaUpdate = siswaService.hapusTahunAjaranSiswa(idSiswa);
        return ResponseEntity.ok(siswaUpdate);
    }
}
