package com.example.urlshortner.service;

import com.example.urlshortner.domain.Url;
import com.example.urlshortner.dto.ShortUrlDto;
import com.example.urlshortner.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortnerService {

    private static final String HOST_URL = "http://localhost:8080";

    private final UrlRepository urlRepository;

    private final SnowflakeService snowflakeService;

    private final Base62Service base62Service;

    public Url shortenUrl(String longUrl) {
        Long id = snowflakeService.getSnowflakeId();

        Optional<Url> savedUrl = urlRepository.findByLongUrl(longUrl);

        if (savedUrl.isPresent()) {
            return savedUrl.get();
        }

        String shortUrl = base62Service.encode(id);

        Url url = new Url(id, shortUrl, longUrl);

        return urlRepository.save(url);
    }

    public ShortUrlDto getShortUrl(Long id) {
        Url url = urlRepository.findById(id).orElseThrow(() -> { throw new IllegalStateException(); });

        return new ShortUrlDto(url.getShortUrl());
    }

    public String findLongUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl).orElseThrow(() -> { throw new IllegalStateException(); });

        return url.getLongUrl();
    }
}
