package com.nhnacademy.java.poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

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

    public int valueCheck(String a, String[] list){
        if(a == "Ace") return 13;
        return Arrays.asList(list).indexOf(a);
    }

    public String judgement(ArrayList<Card> hand){
        String result = "";
        int pairCount = 0;
        // hashmap에 <카드 넘버, 중복 횟수>를 저장
        Map<String, Integer> pairMap = new HashMap<>();
        for (Card card : hand) {
            if(pairMap.containsKey(card.getNum())){
                pairMap.computeIfPresent(card.getNum(), (k, v) -> v + 1);
            } else{
                pairMap.put(card.getNum(), 1);
            }
        }
        int maxCount = Collections.max(pairMap.values());
        // maxCount인 key중에서 key값이 가장 큰 것을 출력
        Map<String, Integer> filterd = new HashMap<>();
        for (Map.Entry<String, Integer> entry : pairMap.entrySet()) {
            if(entry.getValue() == maxCount){
                filterd.put(entry.getKey(), valueCheck(entry.getKey(), numlist));
            }
        }

        String maxNum = "";
        int maxIndex = -1;
        for (Map.Entry<String, Integer> entry : filterd.entrySet()) {
            if(entry.getValue() > maxIndex){
                maxIndex = entry.getValue();
                maxNum = entry.getKey();
            }
        }
        // hashmap 의 value가 2인 key가 2개일 경우 , Two Pair
        for(int count : pairMap.values()){
            if(count == 2) pairCount++;
        }
        if(pairCount == 2) return maxNum + " Two Pair";
        // 중복 횟수 중 가장 큰 값을 비교하여 High, One pair, Triple, Foker를 판별
        if(maxCount == 1){
            result = maxNum + " High";
        } else if (maxCount == 2){
            result = maxNum + " One Pair";
        } else if (maxCount == 3){
            result = maxNum + " Triple";
        } else if (maxCount == 4){
            result = maxNum + " Foker";
        }
        
        return result;
    }

    public boolean compare(String a, String b){
        StringTokenizer strA = new StringTokenizer(a);
        StringTokenizer strB = new StringTokenizer(b);
        String numA = strA.nextToken();
        String pedA = strA.nextToken();
        String numB = strB.nextToken();
        String pedB = strB.nextToken();

        if(valueCheck(pedA, pedigree) == valueCheck(pedB, pedigree)){
            // 2. 같은 족보일 경우, 족보 카드 숫자 밸류 비교 -> 숫자도 같을 경우, 카드 문양의 밸류의 값 비교
            return valueCheck(numA, numlist) > valueCheck(numB, numlist);
        } else return valueCheck(pedA, pedigree) > valueCheck(pedB, pedigree);
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