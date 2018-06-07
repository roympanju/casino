package io.zipcoder.casino;

public class Player {
    private int cash;
    private String name;
    //ArrayList<Card> hand;


    public Player(String name,int cash) {
           this.name = name;
           this.cash =  cash;
    }

    public String getName() {
        return name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void addCardToHand(){

    }
}

