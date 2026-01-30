package com.ganesh.opdtokenapplocationengine.model;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Slot {

    private String slotId;
    private String doctorId;
    private int maxCapacity;

    // Lowest priority token at head
    private PriorityQueue<Token> allocatedTokens =
            new PriorityQueue<>(Comparator.comparingInt(Token::getPriority));

    // Highest priority token at head
    private PriorityQueue<Token> waitQueue =
            new PriorityQueue<>((a, b) -> Integer.compare(b.getPriority(), a.getPriority()));

    public Slot(String slotId, String doctorId, int maxCapacity) {
        this.slotId = slotId;
        this.doctorId = doctorId;
        this.maxCapacity = maxCapacity;
    }

    public boolean hasCapacity() {
        return allocatedTokens.size() < maxCapacity;
    }

    public PriorityQueue<Token> getAllocatedTokens() {
        return allocatedTokens;
    }

    public PriorityQueue<Token> getWaitQueue() {
        return waitQueue;
    }
}
