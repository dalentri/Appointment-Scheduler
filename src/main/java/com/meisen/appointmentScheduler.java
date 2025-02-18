package com.meisen;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;


public class appointmentScheduler {

    private ArrayList<String> appointments = new ArrayList<String>();

    public void appointmentMenu() {
        Scanner scanner = new Scanner(System.in);

        int menuChoice = 0;

        System.out.println("\nAppointment Scheduler Commands:");
        System.out.println("----------------------------");
        System.out.println("1. View Business Hours");
        System.out.println("2. Schedule an Appointment");
        System.out.println("3. Manage Appointments");
        System.out.println("4. Exit");
        System.out.println("----------------------------");

        System.out.print("Please make a selection (1-3): ");
        menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                appointmentTimes();
                appointmentMenu();
                break;
            case 2:
                scheduleAppointment();
                appointmentMenu();
                break;
            case 3:
                manageAppointmentsMenu();
                appointmentMenu();
                break;

            case 4:
                System.out.println("Thank you for using our service!");
                break;

        }
    }

    public void appointmentTimes() {
        System.out.println("\nLooks like you want to view our business hours!");
        System.out.println("----------------------------\n");
        System.out.println("Business Hours:");
        System.out.println("----------------------------");
        System.out.println("Monday: 10:00 AM - 5:00 PM");
        System.out.println("Tuesday: 10:00 AM - 5:00 PM");
        System.out.println("Wednesday: 10:00 AM - 5:00 PM");
        System.out.println("Thursday: 10:00 AM - 5:00 PM");
        System.out.println("Friday: 10:00 AM - 5:00 PM");
        System.out.println("Saturday & : 10:00 AM - 5:00 PM");
        System.out.println("----------------------------");
    }

    public void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nYou have selected to schedule an appointment.");
        System.out.println("----------------------------\n");
        System.out.println("\nWhat month would you like to schedule your appointment for?");
        String month = getMonth();
        System.out.println("\nWhat day would you like to schedule your appointment for?");
        int day = getDay();
        System.out.println("What time would you like to schedule your appointment for? (Format: HH:mm AM/PM)");
        String appointmentTime = getAppointmentTime();

        appointments.add(month + " " + day + " " + appointmentTime);

        parseAppointmentTime(appointmentTime);
    }


    public String getMonth() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getDay() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    //gets user input for the time
    public String getAppointmentTime() {
        Scanner scanner = new Scanner(System.in);
        String appointmentTime = scanner.nextLine();
        if (isValidTime(appointmentTime)) {
            return appointmentTime;
        } else {
            System.out.println("Invalid time format. Please try again.");
            getAppointmentTime();
        }
        return appointmentTime;
    }

    public static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {

            return false;
        }

    }

    //gets every element of the user input
    public void parseAppointmentTime(String appointmentTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime time = LocalTime.parse(appointmentTime, formatter);
            int hour = time.getHour() % 12;
            System.out.println(hour);
            int minute = time.getMinute();
            String amPm = time.getHour() < 12 ? "AM" : "PM";

            System.out.println("Hour: " + hour);
            System.out.println("Minute: " + minute);
            System.out.println("AM/PM: " + amPm);

            System.out.println(hour + ":" + minute + " " + amPm);

        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

    }

    //menu for managing appointments
    public void manageAppointmentsMenu() {
        Scanner scanner = new Scanner(System.in);

        int manageAppointmentsMenuChoice = 0;


        System.out.println("Mange Appointments Commands:");
        System.out.println("----------------------------");
        System.out.println("1. View Appointments");
        System.out.println("2. Cancel an Appointment");
        System.out.println("3. Back to Main Menu");
        System.out.println("----------------------------");

        System.out.println("Please make a selection (1-3): ");
        manageAppointmentsMenuChoice = scanner.nextInt();

        switch (manageAppointmentsMenuChoice) {
            case 1:
                viewAppointments(appointments);
                manageAppointmentsMenu();
                break;
            default:
                System.out.println("Invalid selection. Please try again.");
                manageAppointmentsMenu();
                break;

        }
    }

    //lists all appointments
    public void viewAppointments(ArrayList<String> appointments) {
        System.out.println("\nYou have selected to view your appointments.\n");
        System.out.println("Your Appointments:");
        System.out.println("----------------------------");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println((i + 1) + ". " + appointments.get(i));
        }
        System.out.println("----------------------------\n");

    }

    public void deleteAppointment(ArrayList<String> appointments) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nYou have selected to cancel an appointment.");
        System.out.println("----------------------------");
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println((i + 1) + ". " + appointments.get(i));
        }

        System.out.println("\nWhat is the appointment you would like to cancel? (Type the number from the list above)");
        appointments.remove(scanner.nextInt() - 1);

    }

}
