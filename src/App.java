import com.nhnacademy.java.poker.CardSet;
import com.nhnacademy.java.poker.PokerGame;
import com.nhnacademy.java.poker.User;

public class App {
    public static void main(String[] args) throws Exception {
        CardSet cardSet = new CardSet();
        cardSet.fillCardSet();

        User oppenheimer = new User("Oppenheimer");
        User einstein = new User("Einstein");
        
        PokerGame pokerGame = new PokerGame(oppenheimer, einstein, cardSet);
        pokerGame.start();

    }
}
 