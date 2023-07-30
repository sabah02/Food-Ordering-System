package com.company;

import java.sql.*;
import java.util.Scanner;

public class start {

    //sql connection
    static String url = "jdbc:mysql://localhost:3306/Customer_data";
    static String sqlName = "root";
    static String sqlPass = "SQLPassword1*";

    public static void main(String[] args) throws ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("**********Welcome**********");

        System.out.println("---------------------------");

        int roleChoice;
        System.out.println("Select Role:");
        System.out.println("1. User");
        System.out.println("2. Owner");

        roleChoice = scanner.nextInt();
        if(roleChoice == 1) {
            displayOptions();
        } else if(roleChoice == 2) {
            Menu newMenu = new Menu();
            newMenu.updateMenu();
        }

    }

    public static void register() throws ClassNotFoundException {

        Registration newUser = new Registration();
        newUser.registerUser();

        String cusName = newUser.getCusName();
        String cusEmail = newUser.getCusEmail();
        String cusPhone = newUser.getCusPhone();
        String cusPassword = newUser.getCusPassword();

        String insertQuery = "INSERT INTO customer_details (Name, Phone, Email, Password) VALUES (?, ?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection con = DriverManager.getConnection(url, sqlName, sqlPass);
            PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, cusName);
            preparedStatement.setString(2, cusPhone);
            preparedStatement.setString(3, cusEmail);
            preparedStatement.setString(4, cusPassword);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void login() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*******Login*******");
        System.out.println("Enter Name:");
        String userName = scanner.nextLine();
        System.out.println("Enter Password");
        String userPassword = scanner.nextLine();

        String searchQuery = "SELECT * FROM customer_details WHERE Name = ? AND Password = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url,sqlName, sqlPass);
             PreparedStatement preparedStatement = connection.prepareStatement(searchQuery)) {

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();
            boolean credentialsMatch = resultSet.next();

            if(credentialsMatch) {
                System.out.println("Login Successful!");
            } else {
                System.out.println("Incorrect Name or Password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void displayOptions() throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int userChoice = 0;

        while(userChoice != 5) {

            System.out.println("Enter Choice:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Menu");
            System.out.println("4. View Order");
            System.out.println("5. Exit");

            userChoice = scanner.nextInt();
            if(userChoice == 1) {
                register();
                System.out.println("---------------------------");
            } else if(userChoice == 2) {
                login();
                System.out.println("---------------------------");
            } else if(userChoice == 3) {
                Menu newMenu = new Menu();
                newMenu.viewMenu();
                System.out.println("---------------------------");
            } else if(userChoice == 4) {
                //viewOrder();
                System.out.println("---------------------------");
            } else if(userChoice == 5) {
                break;
            }
        }
    }
}
