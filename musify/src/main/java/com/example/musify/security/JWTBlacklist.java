package com.example.musify.security;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JWTBlacklist {
    private Set<String> blacklistedTokens;

    public JWTBlacklist() {
        this.blacklistedTokens = new HashSet<>();
    }

    public boolean isBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public void addToken(String token) {
        blacklistedTokens.add(token);
    }
}
