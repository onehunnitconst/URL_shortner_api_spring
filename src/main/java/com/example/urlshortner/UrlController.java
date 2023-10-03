package com.example.urlshortner;

import com.example.urlshortner.domain.Url;
import com.example.urlshortner.dto.ShortUrlDto;
import com.example.urlshortner.dto.ShortenUrlDto;
import com.example.urlshortner.service.UrlShortnerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UrlController {

    private final UrlShortnerService urlShortnerService;

    @PostMapping("/data/shorten")
    public String shorten(@RequestBody ShortenUrlDto body) {
        Url url = urlShortnerService.shortenUrl(body.getLongUrl());
        return "redirect:/api/data/" + url.getId();
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<ShortUrlDto> getShortUrl(@PathVariable Long id) {
        ShortUrlDto dto = urlShortnerService.getShortUrl(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{shortUrl}")
    public String redirectLongUrl(@PathVariable String shortUrl) {
        String url = urlShortnerService.findLongUrl(shortUrl);
        return "redirect:" + url;
    }

}
