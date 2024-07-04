package com.example.pusingpak.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TahunAjaranService {

    @Autowired
    private TahunAjaranRepository tahunAjaranRepository;

    public List<TahunAjaran> semuaTahunAjaran() {
        return tahunAjaranRepository.findAll();
    }

    public Optional<TahunAjaran> tahunAjaranById(int id) {
        return tahunAjaranRepository.findById(id);
    }

    public TahunAjaran simpanTahunAjaran(TahunAjaran tahunAjaran) {
        return tahunAjaranRepository.save(tahunAjaran);
    }

    public TahunAjaran ubahTahunAjaran(int id, TahunAjaran tahunAjaranBaru) {
        Optional<TahunAjaran> optionalTahunAjaran = tahunAjaranRepository.findById(id);
        if (optionalTahunAjaran.isPresent()) {
            TahunAjaran tahunAjaran = optionalTahunAjaran.get();
            tahunAjaran.setTahun(tahunAjaranBaru.getTahun());
            tahunAjaran.setPeriode(tahunAjaranBaru.getPeriode());
            tahunAjaran.setTglMulai(tahunAjaranBaru.getTglMulai());
            tahunAjaran.setTglAkhir(tahunAjaranBaru.getTglAkhir());
            tahunAjaran.setKurikulum(tahunAjaranBaru.getKurikulum());
            return tahunAjaranRepository.save(tahunAjaran);
        } else {
            throw new IllegalArgumentException("TahunAjaran dengan ID " + id + " tidak ditemukan.");
        }
    }

    public void hapusTahunAjaran(int id) {
        tahunAjaranRepository.deleteById(id);
    }
}
