package com.example.urlshortner.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Url {
    @Id
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String shortUrl;

    @Column(nullable = false, length = 2048, unique = true)
    private String longUrl;

    protected Url() {}

    public Url(Long id, String shortUrl, String longUrl) {
        this.id = id;
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
    }
}
