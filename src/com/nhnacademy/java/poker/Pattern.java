package com.nhnacademy.java.poker;

public enum Pattern {
    S("Space", 4),
    D("Diamond", 3),
    H("Heart", 2),
    C("Clover", 1);

    public final String pattern;
    public final int value;

    public int getValue() {
        return value;
    }

    Pattern(String pattern, int value){
        this.pattern = pattern;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.pattern;
    }
}
