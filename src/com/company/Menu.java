package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import java.sql.*;

public class Menu {
    private String dish_Name;
    private int price;
    private String category;
    private String dietary_preference;

    String url = "jdbc:mysql://localhost:3306/customer_data";
    String sqlName = "root";
    String sqlPass = "SQLPassword1*";

    Scanner scanner = new Scanner(System.in);

    private void setDish_Name() {
        System.out.println("Enter Recipe Name:");
        this.dish_Name = scanner.nextLine();
    }

    private void setPrice() {
        System.out.println("Enter Price:");
        this.price = scanner.nextInt();
        scanner.nextLine();
    }

    private void setCategory() {
        System.out.println("Enter Category:");
        this.category = scanner.nextLine();
    }

    private void setDietary_preference() {
        System.out.println("Enter dietary_Preference:");
        this.dietary_preference = scanner.nextLine();
    }

    public String getDish_Name() {
        return dish_Name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDietary_preference() {
        return dietary_preference;
    }

    public void updateMenu() throws ClassNotFoundException {


        System.out.println("*******Update Menu*******");
        setDish_Name();
        setPrice();
        setCategory();
        setDietary_preference();

        String insertQuery = "INSERT INTO menu (dish_Name, Price, category, dietary_Preference) VALUES (?, ?, ?, ?)";

        String dish = getDish_Name();
        int price = getPrice();
        String category = getCategory();
        String dietary_Preference = getDietary_preference();

        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(url, sqlName, sqlPass);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, dish);
            preparedStatement.setInt(2, price);
            preparedStatement.setString(3, category);
            preparedStatement.setString(4, dietary_Preference);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Menu Updated successfully");
    }

    public void viewMenu() throws ClassNotFoundException {
        System.out.println("Enter Choice:");
        System.out.println("1. Starter");
        System.out.println("2. Main Course");
        System.out.println("3. Dessert");

        int userChoice = scanner.nextInt();



        if(userChoice == 1) {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String selectQuery = "SELECT * FROM menu where category = 'Starter'";

            try(Connection connection = DriverManager.getConnection(url, sqlName, sqlPass);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery)) {

                while (resultSet.next()) {
                    String dishName = resultSet.getString("dish_Name");
                    int price = resultSet.getInt("Price");
                    String category = resultSet.getString("category");
                    String dietary_preference = resultSet.getString("dietary_Preference");

                    System.out.println("Name: " + dishName);
                    System.out.println("Price: " + price);
                    System.out.println("category: " + category);
                    System.out.println("Dietary Preference: " + dietary_preference);
                    System.out.println();

                }

            } catch (SQLException e) {
                  e.printStackTrace();
            }

            }

    }
}
