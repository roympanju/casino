package io.zipcoder.AbstractClasses;

import io.zipcoder.casino.Deck;
import io.zipcoder.Interfaces.Gamble;
import io.zipcoder.casino.Player;

public abstract class CardGame extends Game implements Gamble {
    private Deck[] deck;

    public CardGame(Player[] players){
        super(players);
        this.deck[0] = new Deck(1);
    }

    public String cardValue(int indexOfPlayers, int indexOfHand){
        return players[indexOfPlayers].getPlayersCard(indexOfHand).getValue();
    }

    public void setDeck(int numOfDecks) {
       //put deck object in deck Array.
    }

    public  void deal(int numOfCards){
        for(Player player : players){
            for (int i = 0; i < numOfCards; i++) {
                player.addCardToHand(deck[0].draw());
            }
        }
    }
}

