package com.example.urlshortner.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Service {
    private static final String LETTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int RADIX = 62;

    public String encode(Long source) {
        StringBuffer sb = new StringBuffer();

        Long temp = source;
        while (temp > 0) {
            int remain = (int) (temp % RADIX);
            temp /= RADIX;
            sb.append(LETTERS.charAt(remain));
        }

        return sb.toString();
    }

    public Long decode(String source) {
        Long result = 0L;
        int power = 0;

        for (char ch: source.toCharArray()) {
            Long term = LETTERS.indexOf(ch) * (long) Math.pow(RADIX, power++);
            result += term;
        }

        return result;
    }
}
