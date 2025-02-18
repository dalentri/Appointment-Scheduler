package com.meisen;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, would you like to set an appointment?");
        String answer = scanner.nextLine();
        answer = answer.toLowerCase();

        if (answer.equals("yes") || answer.equals("y")) {

            appointmentScheduler scheduler = new appointmentScheduler();

            scheduler.appointmentMenu();

        } else if (answer.equals("no") || answer.equals("n")) {
            System.out.println("Thank you for using our service!");
        }

        scanner.close();

    }
}