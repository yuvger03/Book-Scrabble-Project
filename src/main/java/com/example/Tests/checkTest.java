package com.example.Tests;


//import org.junit.jupiter.api.*;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class checkTest {


    private String firstName;
    private String lastName;

    public checkTest() {
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getFullName(){
        return firstName.toUpperCase()+" "+lastName.toUpperCase();
    }

}