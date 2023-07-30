package com.company;

import java.util.Scanner;

public class Registration {

    //Attributes
    private String cusName;
    private String cusPhone;
    private String cusEmail;
    private String cusPassword;

    Scanner scanner = new Scanner(System.in);

    private void setCusName() {
        System.out.println("Enter Name:");
        this.cusName = scanner.nextLine();
    }

    private void setCusPhone() {
        System.out.println("Enter Phone:");
        this.cusPhone = scanner.nextLine();
        scanner.nextLine();
    }

    private void setCusEmail() {
        System.out.println("Enter Email:");
        this.cusEmail = scanner.nextLine();
    }

    private void setCusPassword() {
        System.out.println("Enter Password:");
        this.cusPassword = scanner.nextLine();
    }

    public String getCusName() {
        return cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public String getCusEmail() {
        return cusEmail;
    }

    public String getCusPassword() {
        return cusPassword;
    }

    public void registerUser() {
        System.out.println("**********Register**********");
        setCusName();
        setCusPhone();
        setCusEmail();
        setCusPassword();
        System.out.println("Thank you!");
    }

}
