package com.example.pusingpak.demo;

import com.example.pusingpak.demo.Siswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, Integer> {
}
