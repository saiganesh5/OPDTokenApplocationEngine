package com.ganesh.opdtokenapplocationengine.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Token {

    private String tokenId;
    private String patientId;
    private BookingType bookingType;
    private int priority;
    private TokenStatus status;
    private LocalDateTime createdAt;

    public Token(String patientId, BookingType bookingType) {
        this.tokenId = UUID.randomUUID().toString();
        this.patientId = patientId;
        this.bookingType = bookingType;
        this.priority = PriorityResolver.resolvePriority(bookingType);
        this.status = TokenStatus.WAITLISTED;
        this.createdAt = LocalDateTime.now();
    }

    public int getPriority() {
        return priority;
    }

    public void setStatus(TokenStatus status) {
        this.status = status;
    }

    public String getTokenId() {
        return tokenId;
    }

    public TokenStatus getStatus() {
        return status;
    }
}
