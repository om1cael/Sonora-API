package com.om1cael.sonora.api.controller;

import com.om1cael.sonora.api.model.Music;
import com.om1cael.sonora.api.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/user/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    private ResponseEntity<Void> upload(@RequestParam("name") String name, @RequestPart("audio") MultipartFile audio) throws IOException {
        if(musicService.upload(name, audio)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    private ResponseEntity<Music> get(@PathVariable Long id) {
        Music music = this.musicService.get(id);
        return ResponseEntity.ok(music);
    }

    @GetMapping(value = "/listen/{id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    private ResponseEntity<Resource> getContent(@PathVariable Long id) throws IOException {
        Music music = this.musicService.get(id);
        Resource resource = new ByteArrayResource(music.getAudio());

        return ResponseEntity.ok()
                .header("Content-Disposition", "inline")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .body(resource);
    }
}
