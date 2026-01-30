package com.ganesh.opdtokenapplocationengine.service;
import com.ganesh.opdtokenapplocationengine.model.BookingType;
import com.ganesh.opdtokenapplocationengine.model.Slot;
import com.ganesh.opdtokenapplocationengine.model.Token;
import com.ganesh.opdtokenapplocationengine.model.TokenStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TokenAllocationService {

    // In-memory store for demo (replace with DB later)
    private final Map<String, Slot> slotStore = new HashMap<>();

    public TokenAllocationService() {
        // Sample slot
        slotStore.put("S1", new Slot("S1", "D1", 5));
    }

    public Token allocateToken(String patientId, BookingType bookingType, String slotId) {

        Slot slot = slotStore.get(slotId);
        Token token = new Token(patientId, bookingType);

        if (slot.hasCapacity()) {
            token.setStatus(TokenStatus.ALLOCATED);
            slot.getAllocatedTokens().add(token);
            return token;
        }

        Token lowestPriorityToken = slot.getAllocatedTokens().peek();

        if (lowestPriorityToken != null &&
                token.getPriority() > lowestPriorityToken.getPriority()) {

            // Evict low priority token
            slot.getAllocatedTokens().poll();
            lowestPriorityToken.setStatus(TokenStatus.WAITLISTED);
            slot.getWaitQueue().add(lowestPriorityToken);

            // Allocate new token
            token.setStatus(TokenStatus.ALLOCATED);
            slot.getAllocatedTokens().add(token);

            return token;
        }

        // Add to waitlist
        token.setStatus(TokenStatus.WAITLISTED);
        slot.getWaitQueue().add(token);
        return token;
    }

    public void cancelToken(String slotId) {
        Slot slot = slotStore.get(slotId);

        if (!slot.getWaitQueue().isEmpty()) {
            Token next = slot.getWaitQueue().poll();
            next.setStatus(TokenStatus.ALLOCATED);
            slot.getAllocatedTokens().add(next);
        }
    }
}
