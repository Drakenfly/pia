package com.pia.testing.beans;

import java.util.Arrays;

public class User {
    private final String name;
    private final int age;
    private final char[] initials;

    public User (String name, int age, char[] initials) {
        this.name = name;
        this.age = age;
        this.initials = initials;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (age != user.age) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return Arrays.equals(initials, user.initials);
    }

    @Override
    public int hashCode () {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + Arrays.hashCode(initials);
        return result;
    }

    @Override
    public String toString () {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", initials=" + Arrays.toString(initials) +
                '}';
    }
}
