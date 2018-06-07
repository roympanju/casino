package io.zipcoder.casino;

public abstract class CardGame extends Game implements Gamble{
    private Deck[] deck;
    public int cardValue(){
        return 0;
    }

    public void setDeck(int numOfDecks) {

       //put deck object in deck Array.

    }
    public  void deal(int numOfCards){
        //deal numOfCards to players hands from deck
    }



}

