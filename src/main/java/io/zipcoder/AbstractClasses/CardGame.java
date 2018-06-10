package io.zipcoder.AbstractClasses;

import io.zipcoder.casino.Deck;
import io.zipcoder.casino.Card;
import io.zipcoder.Interfaces.Gamble;
import io.zipcoder.casino.Player;
import java.util.*;

public abstract class CardGame extends Game implements Gamble {
    public Deck[] deck;
    public ArrayList<Card> discard = new ArrayList<Card>();
    private int pot;

    public CardGame(Player[] players, int deckNumber) {
        super(players);
        deck = new Deck[deckNumber];
        for (int i = 0; i < deckNumber; i++) {
            deck[i] = new Deck(1);
        }
    }

    public void deal(int numOfCards, Player house) {
        System.out.println("Dealing Cards.");
        for(Player player : players){
            for (int i = 0; i < numOfCards; i++) {
                player.addCardToHand(deck[0].draw());
            }
        }
        for (int i = 0; i < numOfCards; i++) {
            house.addCardToHand(deck[0].draw());
        }
    }

    public void setPot(int bet) {
        pot += bet;
    }
    public void dumpPot() {
        pot = 0;
    }
    public int getPot() {
        return pot;
    }

    public int getHandValue(ArrayList<Card> hand){
        int result = 0;
        boolean hasAce = false;
        for (Card card : hand) {
            result += card.getIntValue();
            if (card.getValue().equals("ACE")) hasAce = true;
        }
        if (hasAce && result > 21) {
            System.out.println("ACE automatically valued at 1 to avoid bust.");
            result -= 10;
        }
        return result;
    }

    public String displayHands() {
        String result = "";
        for(Player player : players) {
            result += player.getName() + ": \n";
            for (int i  = 0; i < player.getHandSize(); i++) {
                result += player.getCard(i, false).getValue() + " of ";
                result += player.getCard(i, false).getSuit() + " ";
            }
            result += "\n";
        }
        return result;
    }

    public void discardHand (Player player) {
        for (int i = 0; i < player.getHandSize(); i++) {
            discard.add(player.getCard(0, true));
        }
    }
}

