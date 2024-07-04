package com.example.pusingpak.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.pusingpak.demo.Kehadiran;

@Repository
public interface KehadiranRepository extends JpaRepository<Kehadiran, Integer> {
    // Custom query methods can be added here if needed
}
