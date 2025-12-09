package models;

public abstract class Person {
    protected String firstName;
    protected String lastName;

    public Person(String fn, String ln) {
        this.firstName = fn;
        this.lastName = ln;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

