package com.pia.testing.beans;

public class Person {
    private final String name;
    private Person[] children;

    public Person (String name, Person[] children) {
        this.name = name;
        this.children = children;
    }
}
