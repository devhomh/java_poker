package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class PokerGame {
    private User user1;
    private User user2;
    private String user1Name;
    private String user2Name;
    private ArrayList<Card> hand1;
    private ArrayList<Card> hand2;
    private ArrayList<Card> cardSet;
    private String[] numlist;
    private String[] pedigree = {"High", "One Pair", "Two Pair", "Triple", "Poker"};
    // private String[] Pedigree = {"High", "One Pair", "Two Pair", "Triple", "Straight", "Back Straight", "Mountain", "Flush", "Full House", "Poker", "Straight Flush", "Back Straight Flush", "Royal Straight Flush"};

    public PokerGame(User user1, User user2, CardSet cardSet) {
        this.user1 = user1;
        this.user2 = user2;
        this.user1Name = user1.getName();
        this.user2Name = user2.getName();
        this.hand1 = user1.getHand();
        this.hand2 = user2.getHand();
        this.cardSet = cardSet.getCardSet();
        this.numlist = cardSet.getNumList();
        
    }

    public String judgement(ArrayList<Card> deck){
        String result = "";
        int pairCount = 0;
        // hashmap에 <카드 넘버, 중복 횟수>를 저장
        Map<String, Integer> pairMap = new HashMap<>();
        for (int i = 0; i <numlist.length;  i++) {
            int tmp = i;
            Stream<Card> tmpList = deck.stream().filter(card -> card.getNum().equals(numlist[tmp]));
            int count = (int) tmpList.count();
            pairMap.put(numlist[i], count);
        }
        // hashmap 의 value가 2인 key가 2개일 경우 , Two Pair
        for(int count : pairMap.values()){
            if(count == 2) pairCount++;
        }

        if(pairCount == 2) return "Two Pair";
        // 중복 횟수 중 가장 큰 값을 비교하여 High, One pair, Triple, Foker를 판별
        int maxCount = Collections.max(pairMap.values());
        // maxCount인 key중에서 key값이 가장 큰 것을 출력
        String[] maxCountArr = new String[numlist.length];
        // for (Map.Entry<String, Integer> entry : pairMap.entrySet()) {
        //     if(Objects.equals(maxCount, entry.getValue())) maxCountArr.add(entry.getKey());
        // }

        // maxCount가 같은 것 중 가장 숫자가 높은 것.
        if(maxCount == 1){
            result = "High";
        } else if (maxCount == 2){
            result = "One Pair";
        } else if (maxCount == 3){
            result = "Triple";
        } else if (maxCount == 4){
            result = "Foker";
        }
        
        return result;
    }

    public int valueCheck(String a){
        return Arrays.asList(pedigree).indexOf(a);
    }

    public boolean compare(String a, String b){
        if(valueCheck(a) == valueCheck(b)){
            // 2. 같은 족보일 경우, 족보 카드 숫자 밸류 비교 -> 숫자도 같을 경우, 카드 문양의 밸류의 값 비교
        } else return valueCheck(a) > valueCheck(b);
        return true;
    }

    public void start(){
        System.out.println();
        System.out.println("Start Poker!");
        for (int i = 0; i < 5; i++) {
            user1.pickCard(cardSet);
            user2.pickCard(cardSet);
        }
        System.out.println();
        System.out.println(user1Name + "'s hand : " + hand1);
        System.out.println(user2Name + "'s hand : " + hand2);

        String pedigreeUser1 = judgement(hand1);
        String pedigreeUser2 = judgement(hand2);

        System.out.println();
        System.out.println(user1Name + " is [" + pedigreeUser1 + "]");
        System.out.println(user2Name + " is [" + pedigreeUser2 + "]");
        System.out.println();

        if(compare(pedigreeUser1, pedigreeUser2)){
            System.out.println(user1Name + " Win!");
        } else{
            System.out.println(user2Name + " Win!");
        }
    }
}