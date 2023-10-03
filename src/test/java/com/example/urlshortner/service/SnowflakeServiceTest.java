package com.example.urlshortner.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SnowflakeServiceTest {

    @Autowired
    SnowflakeService snowflakeService;

    @Test
    void snowflake() {
        int testCase = 10;

        while(testCase < 0) {
            Long l1 = snowflakeService.getSnowflakeId();
            Long l2 = snowflakeService.getSnowflakeId();
            Assertions.assertThat(l1).isNotEqualTo(l2);
            --testCase;
        }
    }
}