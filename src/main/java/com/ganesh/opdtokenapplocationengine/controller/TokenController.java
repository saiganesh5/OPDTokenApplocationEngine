package com.ganesh.opdtokenapplocationengine.controller;

import com.ganesh.opdtokenapplocationengine.model.BookingType;
import com.ganesh.opdtokenapplocationengine.model.Token;
import com.ganesh.opdtokenapplocationengine.service.TokenAllocationService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenAllocationService tokenService;

    public TokenController(TokenAllocationService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public Token createToken(
            @RequestParam String patientId,
            @RequestParam BookingType bookingType,
            @RequestParam String slotId) {

        return tokenService.allocateToken(patientId, bookingType, slotId);
    }

    @PostMapping("/{slotId}/cancel")
    public String cancelToken(@PathVariable String slotId) {
        tokenService.cancelToken(slotId);
        return "Token cancelled and reallocated if possible";
    }
}
