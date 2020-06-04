package com.cardinalhealth.audition.entities;

import java.util.UUID;

public class Patient implements Comparable<Patient> {

    private final UUID id;
    private final String firstName;
    private final String lastName;
    private final EmailAddress emailAddress = new EmailAddress();


    public Patient(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Patient(UUID id, String firstName, String lastName, String emailAddress, int emailType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress.address = emailAddress;
        this.emailAddress.type = emailType;
    }

    public static Patient named(String firstName, String lastName) {
        return new Patient(UUID.randomUUID(), firstName, lastName);
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Patient patient = (Patient) o;

        if (firstName != null ? !firstName.equals(patient.firstName) : patient.firstName != null) return false;
        if (id != null ? !id.equals(patient.id) : patient.id != null) return false;
        if (lastName != null ? !lastName.equals(patient.lastName) : patient.lastName != null) return false;

        return true;
    }

    @Override
    public int compareTo(Patient other) {
        String thisName = this.getLastName() + this.getFirstName();
        String thatName = other.getLastName() + other.getFirstName();
        System.out.println("this one: " + thisName + ", that one: " + thatName);
        return thisName.compareTo(thatName);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
