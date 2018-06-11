package io.zipcoder.casino;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import io.zipcoder.AbstractClasses.Game;

public class MemoryGame extends Game  {

    public void exit(){


    }

    public boolean winCondition(){

        return true;
    }
    Scanner sc = new Scanner(System.in);
    private int maxTry;

    ArrayList<Card> cardList = new ArrayList<Card>();
    String[] valuelist = { "1","2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    String[] suitlist = {"diamond","spade","heart","club"};

    public MemoryGame(int maxTry,Player[] players,int numOfCards) {

        super(players);
        this.maxTry = maxTry;


        for (int i = 0; i < numOfCards / 2; i++) {
            int index = ((int)(Math.random() * 13) + 0);
            String tempValue = valuelist[index];
            int index1 = ((int)(Math.random() * 3) + 0);
            String tempSuit = suitlist[index1];
            Card n1 = new Card(tempSuit,tempValue);
            Card n2 = new Card(tempSuit,tempValue);
            cardList.add(n1);
            cardList.add(n2);

        }
        Collections.shuffle(cardList);


    }
    public void play() {
        int count = 0;
        while (true) {
            System.out.println("Enter the  position to be opened");
            int pos1 = sc.nextInt();
            Card n1 = cardList.get(pos1);
            if(n1 == null) {
                System.out.println("Your position is already taken,please enter a new postion");
                continue;
            }

            System.out.println("Your card is: " + "value is " + n1.getValue() + " suit is " + n1.getSuit());

            System.out.println("Enter the  guess position");
            int pos2 = sc.nextInt();
            Card n2 = cardList.get(pos2);
            if(n2== null) {
                System.out.println("Your position is already taken,please enter a new postion");
                continue;
            }

            System.out.println("Your guess is: " + " value is " + n2.getValue() + " suit is " + n2.getSuit());
            if (isMatch(n1, n2)) {
                System.out.println("Congratulations, your cards are matching");
                cardList.set(pos1, null);
                cardList.set(pos2,null);


            }
            if(iscardListNull()){
                System.out.println("Congratulations,the game is over");
                break;
            }
            count ++;
            if(count == maxTry)
            {
                System.out.println("You have reached the maximum try");
                break;
            }


        }

    }
    public  boolean isMatch(Card n1,Card n2)
    {
        String suit1 = n1.getSuit();
        String value1 = n1.getValue();

        String suit2 = n2.getSuit();
        String value2 = n2.getValue();

        if(suit1.equals(suit2) && (value1.equals(value2)))
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public  boolean iscardListNull()
    {
        for(Card i: cardList)
        {
            if(i == null)
            {
                continue;
            }
            else
            {
                return false;
            }


        }
        return true;
    }



}
