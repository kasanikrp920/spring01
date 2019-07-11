package com.trimind.restdemo1.entities;

import javax.validation.constraints.*;

public class Employee {


    private int id;

    @NotEmpty(message="first name should not be empty ") @NotBlank(message="first name should not be blank ")
    private  String firstName;
    @NotEmpty(message="last name should not be empty ") @NotBlank(message="last name should not be blank ")
    private String lastName;

    @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$",message = "You have given Wrong email Id pattern(recommended:abc123@xyz.com)")
    private String emailId;

    @Size(min=10,max=10,message="phone number must and  should contain 10 digits")
    private String phoneNumber;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, String emailId, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object obj) {

        Employee e=(Employee)obj;
        return this.getId()==e.getId()&&this.getFirstName().equals(e.getFirstName())&&this.getLastName().equals(e.getLastName());
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
