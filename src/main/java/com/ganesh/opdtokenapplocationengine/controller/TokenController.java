package com.ganesh.opdtokenapplocationengine.controller;

import com.ganesh.opdtokenapplocationengine.model.BookingType;
import com.ganesh.opdtokenapplocationengine.model.Token;
import com.ganesh.opdtokenapplocationengine.model.TokenRequest;
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
    public Token createToken(@RequestBody TokenRequest request ) {

        return tokenService.allocateToken(
                request.getPatientId(),
                request.getBookingType(),
                request.getSlotId()
        );
    }

    @PostMapping("/{slotId}/cancel")
    public String cancelToken(@PathVariable String slotId) {
        try {
            tokenService.cancelToken(slotId);
            return "Token cancelled and reallocated if possible";
        }catch (NullPointerException e){
            return "Slot is Empty/null";
        }

    }
}
