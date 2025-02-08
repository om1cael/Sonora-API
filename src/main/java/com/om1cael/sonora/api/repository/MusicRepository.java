package com.om1cael.sonora.api.repository;

import com.om1cael.sonora.api.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByName(String name);
}
