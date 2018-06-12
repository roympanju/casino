package io.zipcoder.Console;

public class KnockOutConsole {

    public void winnerMessage(int a, String name){
        System.out.println("Congratulations " + name + " you have won " +
                "you have won $" + a );
    }

    public void loseMessageDisplay (String str){
        System.out.println(str + " Lost try harder next time");
    }

    public void rollPrompt(String str){
        System.out.println(str + " press any key to roll");

    }

    public void displayCheekyMessage(){
        System.out.println("You still live");
    }

    public void displayRolledNumber(String str, int num){
        System.out.println(str + " rolled " + num);
    }

    public void displayHouseSetNumber(int number){
        System.out.println("The house set number is "+ number);
    }

    public void promptOverBetting(){
        System.out.println("Sorry you don't have enough money for this bet");
    }

    public void promptBettingAll(){
        System.out.println("you are all in bet carefully");
    }

    public void displayBet(int bet){
        System.out.println("your bet is $" + bet);
    }

    public void promptPlaceBet(String name){
        System.out.println(name + " Please place your bet.");
    }

}
