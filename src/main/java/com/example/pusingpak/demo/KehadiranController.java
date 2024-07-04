package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/kehadiran")
public class KehadiranController {

    private final KehadiranService kehadiranService;

    @Autowired
    public KehadiranController(KehadiranService kehadiranService) {
        this.kehadiranService = kehadiranService;
    }

    @GetMapping
    public List<Kehadiran> getAllKehadiran() {
        return kehadiranService.semuaKehadiran();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kehadiran> getKehadiranById(@PathVariable int id) {
        Optional<Kehadiran> kehadiran = kehadiranService.getKehadiranById(id);
        return kehadiran.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kehadiran createKehadiran(@RequestBody Kehadiran kehadiran) {
        return kehadiranService.simpanKehadiran(kehadiran);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kehadiran> updateKehadiran(@PathVariable int id, @RequestBody Kehadiran kehadiranBaru) {
        Optional<Kehadiran> updatedKehadiran = Optional.ofNullable(kehadiranService.updateKehadiran(id, kehadiranBaru));
        return updatedKehadiran.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKehadiran(@PathVariable int id) {
        kehadiranService.hapusKehadiran(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/siswa")
    public ResponseEntity<Siswa> getSiswaKehadiran(@PathVariable int id) {
        Optional<Siswa> siswa = kehadiranService.getSiswaKehadiran(id);
        return siswa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/siswa")
    public ResponseEntity<Kehadiran> setSiswaKehadiran(@PathVariable int id, @RequestBody Siswa siswa) {
        try {
            Kehadiran updatedKehadiran = kehadiranService.simpanSiswaKehadiran(id, siswa);
            return ResponseEntity.ok(updatedKehadiran);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/siswa")
    public ResponseEntity<Kehadiran> removeSiswaKehadiran(@PathVariable int id) {
        try {
            Kehadiran updatedKehadiran = kehadiranService.hapusSiswaKehadiran(id);
            return ResponseEntity.ok(updatedKehadiran);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
