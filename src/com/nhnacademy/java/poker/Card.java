package com.nhnacademy.java.poker;

public class Card {
    
    private final Pattern pattern;
    private final String num;
    
    public Card(Pattern pattern, String num) {
        this.pattern = pattern;
        this.num = num;
    }
    public Pattern getPattern() {
        return pattern;
    }

    public String getNum() {
        return num;
    }
    
    @Override
    public String toString() {
        return pattern + " " + num;
    }
    
}
