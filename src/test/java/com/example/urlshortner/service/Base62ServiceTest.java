package com.example.urlshortner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class Base62ServiceTest {

    @Autowired
    Base62Service base62Service;

    @Test
    void encodeAndDecode() {
        int testCase = 10;

        while(testCase > 0) {
            Long random = new Random().nextLong(899_999_999) + 100_000_000;
            String encoded = base62Service.encode(random);
            Long decoded = base62Service.decode(encoded);
            Assertions.assertThat(random).isEqualTo(decoded);
            testCase--;
        }
    }
}