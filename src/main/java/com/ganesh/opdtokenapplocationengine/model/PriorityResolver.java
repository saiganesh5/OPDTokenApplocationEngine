package com.ganesh.opdtokenapplocationengine.model;

public class PriorityResolver {
    public static int resolvePriority(BookingType type) {
        return switch (type) {
            case EMERGENCY -> 5;
            case PAID -> 4;
            case FOLLOW_UP -> 3;
            case ONLINE -> 2;
            case WALK_IN -> 1;
        };
    }
}
