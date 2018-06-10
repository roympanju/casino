package io.zipcoder.AbstractClasses;

import io.zipcoder.casino.Deck;
import io.zipcoder.casino.Card;
import io.zipcoder.Interfaces.Gamble;
import io.zipcoder.casino.Player;
import java.util.*;

import java.util.ArrayList;

public abstract class CardGame extends Game implements Gamble {
    public Deck[] deck;
    private int deckNumber;
    private int pot;

    public CardGame(Player[] players, int deckNumber) {
        super(players);
        this.deckNumber = deckNumber;
        deck = new Deck[deckNumber];
        constructDeck();
    }

    public void constructDeck() {
        for (int i = 0; i < deckNumber; i++) {
            deck[i] = new Deck(1);
            deck[i].shuffle();
        }
    }

    public void deal(int numOfCards, Player house) {
        System.out.println("Dealing Cards. \n");
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
        int aceCount = 0;
        for (Card card : hand) {
            result += card.getIntValue();
            if (card.getValue().equals("ACE")) aceCount += 1;
        }
        if (aceCount > 0 && result > 21) {
            System.out.println("ACE automatically valued at 1 to avoid bust.");
            result -= (10 * aceCount);
        }
        return result;
    }

    public String displayHands() {
        String result = "";
        for(Player player : players) {
            result += player.getName() + ": \n";
            for (int i  = 0; i < player.getHandSize(); i++) {
                result += player.getCard(i).getValue() + " of ";
                result += player.getCard(i).getSuit() + "   ";
            }
            result += "\n";
        }
        return result;
    }

    public String displayHands(Player house) {
        String result = "";
            for (int i  = 0; i < house.getHandSize(); i++) {
                result += house.getCard(i).getValue() + " of ";
                result += house.getCard(i).getSuit() + "   ";
            }
            result += "\n";
        return result;
    }

    public void discardPlayerHand (Player player) {
        player.discardHand();
    }
}

