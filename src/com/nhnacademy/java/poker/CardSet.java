package com.nhnacademy.java.poker;

import java.util.ArrayList;

public class CardSet {
    private final String[] numList = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eigth", "Nine", "Ten", "Jack", "Queen", "King"};
    
    public String[] getNumList() {
        return numList;
    }

    private ArrayList<Card> cardSet = new ArrayList<>();

    public ArrayList<Card> getCardSet() {
        return this.cardSet;
    }
    
    public void fillCardSet() {
        for (int i = 0; i < numList.length; i++) {
            String num = numList[i];
            Card space = new Card(Pattern.S, num);
            cardSet.add(space);
        }

        for (int i = 0; i < numList.length; i++) {
            String num = numList[i];
            Card heart = new Card(Pattern.H, num);
            cardSet.add(heart);
        }

        for (int i = 0; i < numList.length; i++) {
            String num = numList[i];
            Card diamond = new Card(Pattern.D, num);
            cardSet.add(diamond);
        }

        for (int i = 0; i < numList.length; i++) {
            String num = numList[i];
            Card clover = new Card(Pattern.C, num);
            cardSet.add(clover);
        }
    }
}
