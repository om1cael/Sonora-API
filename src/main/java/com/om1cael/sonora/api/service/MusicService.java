package com.om1cael.sonora.api.service;

import com.om1cael.sonora.api.model.Music;
import com.om1cael.sonora.api.model.User;
import com.om1cael.sonora.api.repository.MusicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    public boolean upload(String name, MultipartFile audio) throws IOException {
        if(audio.isEmpty()) {
            return false;
        }

        Music music = new Music();
        music.setName(name);
        music.setArtist((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        music.setAudio(audio.getBytes());
        musicRepository.save(music);
        return true;
    }

    public Music get(Long id) {
        return this.musicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Music not found"));
    }
}
