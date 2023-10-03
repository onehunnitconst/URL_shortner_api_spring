package com.example.urlshortner.service;

import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SnowflakeService {
    private static final long DATA_CENTER_ID = 1L;
    private static final int DATA_CENTER_BIT_OFFSET = 12;

    private static final long SERVER_ID = 1L;
    private static final int SERVER_ID_BIT_OFFSET = 17;
    private static final long BASE_EPOCH = 1696240762092L;
    private static final int EPOCH_OFFSET = 22;

    private static long LATEST_OFFSET = Instant.now().toEpochMilli();
    private static long SEQUENCE = 0;

    public synchronized Long getSnowflakeId() {
        final long nowEpoch = Instant.now().toEpochMilli();
        final long timestampEpoch = nowEpoch - BASE_EPOCH;

        if (nowEpoch != LATEST_OFFSET) {
            LATEST_OFFSET = nowEpoch;
            SEQUENCE = 0;
        }

        final long timestamp = timestampEpoch << EPOCH_OFFSET;
        final long machineKey = (DATA_CENTER_ID << DATA_CENTER_BIT_OFFSET) +
                (SERVER_ID << SERVER_ID_BIT_OFFSET);
        final long primaryKey = SEQUENCE++;

        return timestamp + machineKey + primaryKey;
    }
}
