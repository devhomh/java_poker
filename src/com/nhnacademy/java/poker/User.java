package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.Random;

public class User {

    private String name;
    public String getName() {
        return name;
    }

    private ArrayList<Card> hand = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void pickCard(ArrayList<Card> cardSet){
        Random random = new Random();
        int randomNum = random.nextInt(cardSet.size());
        Card pickCard = cardSet.get(randomNum);
        this.hand.add(pickCard);
        cardSet.remove(pickCard);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    
}
