package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tahunajaran")
@CrossOrigin(origins = "*")
public class TahunAjaranController {

    @Autowired
    private TahunAjaranService tahunAjaranService;

    @GetMapping
    public List<TahunAjaran> semuaTahunAjaran() {
        return tahunAjaranService.semuaTahunAjaran();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TahunAjaran> tahunAjaranById(@PathVariable("id") int id) {
        Optional<TahunAjaran> optionalTahunAjaran = tahunAjaranService.tahunAjaranById(id);
        return optionalTahunAjaran.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TahunAjaran> simpanTahunAjaran(@RequestBody TahunAjaran tahunAjaran) {
        TahunAjaran savedTahunAjaran = tahunAjaranService.simpanTahunAjaran(tahunAjaran);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTahunAjaran);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TahunAjaran> ubahTahunAjaran(@PathVariable("id") int id, @RequestBody TahunAjaran tahunAjaranBaru) {
        try {
            TahunAjaran updatedTahunAjaran = tahunAjaranService.ubahTahunAjaran(id, tahunAjaranBaru);
            return ResponseEntity.ok(updatedTahunAjaran);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hapusTahunAjaran(@PathVariable("id") int id) {
        tahunAjaranService.hapusTahunAjaran(id);
        return ResponseEntity.noContent().build();
    }
}
