package com.example.urlshortner.service;

import com.example.urlshortner.domain.Url;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class UrlShortnerServiceTest {

    @Autowired
    UrlShortnerService service;

    @Test
    @Transactional
    void shorten() {
        String longUrl = "https://www.naver.com/";

        Url url = service.shortenUrl(longUrl);

        String findLongUrl = service.findLongUrl(url.getShortUrl());

        Assertions.assertThat(longUrl).isEqualTo(findLongUrl);
    }
}