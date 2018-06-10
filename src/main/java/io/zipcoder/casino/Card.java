package io.zipcoder.casino;

public class Card {
    private String suit;
    private String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }
    public String getSuit() {
        return suit;
    }
    public String getValue() {
        return value;
    }

    public int getIntValue() {
        return value.equals("ACE") ? 11
             : value.equals("TWO") ? 2
             : value.equals("THREE") ? 3
             : value.equals("FOUR") ? 4
             : value.equals("FIVE") ? 5
             : value.equals("SIX") ? 6
             : value.equals("SEVEN") ? 7
             : value.equals("EIGHT") ? 8
             : value.equals("NINE") ? 9
             : 10;
     }

}
