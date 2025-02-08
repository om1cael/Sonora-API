package com.om1cael.sonora.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Entity
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private String name;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User artist;

    @JsonIgnore
    private byte[] audio;
}
