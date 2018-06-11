package io.zipcoder.Console;
import java.util.Scanner;

public class MemoryConsole {
    public void print(String output, Object...args){
        System.out.printf(output, args);
    }

public void println(String output, Object...args){
    print(output + "\n", args);
}

    public String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        println(prompt);
        String userInput = scanner.nextLine();
        String userInput2 = userInput.toLowerCase();

        return userInput2;
    }




    public Integer getIntInput(String prompt){
        Scanner scanner = new Scanner(System.in);
        println(prompt);
        int userInput = scanner.nextInt();
        return userInput;
    }
}
