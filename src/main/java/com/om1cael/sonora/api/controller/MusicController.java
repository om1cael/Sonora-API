package com.om1cael.sonora.api.controller;

import com.om1cael.sonora.api.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
}
