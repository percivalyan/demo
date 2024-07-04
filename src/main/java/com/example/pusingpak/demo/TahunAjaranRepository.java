package com.example.pusingpak.demo;

import com.example.pusingpak.demo.TahunAjaran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TahunAjaranRepository extends JpaRepository<TahunAjaran, Integer> {
    // Jika diperlukan, Anda bisa menambahkan method kustom di sini
}
