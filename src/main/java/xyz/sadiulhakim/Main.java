package xyz.sadiulhakim;


import xyz.sadiulhakim.process.ProcessAccessor;
import xyz.sadiulhakim.security.Identifier;

import java.util.Scanner;

public class Main {

    private static final Scanner INPUT = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("+----Please Identify Yourself----+");
        System.out.print("Enter your code: ");

        String pin = INPUT.next();
        if (!Identifier.validatePin(pin)) {
            System.out.println("Invalid Pin!");
            return;
        }

        ProcessAccessor.clear();
        System.out.println("+-------------------+Welcome To Hakim Platform+-------------------+");
    }
}