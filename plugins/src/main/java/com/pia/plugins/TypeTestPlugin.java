package com.pia.plugins;

import com.pia.core.annotation.PluginMetadata;
import com.pia.core.plugin.Plugin;
import com.pia.core.annotation.Property;

import java.util.Arrays;
import java.util.List;

@PluginMetadata(name = "Type Test")
public class TypeTestPlugin extends Plugin {
    @Property(name = "String Parameter")
    public String stringParam;

    @Property(name = "int Parameter")
    public int intParam;

    @Property(name = "double Parameter")
    public double doubleParam;

    @Property(name = "List<String[]> Parameter")
    public List<String[]> listOfStringArraysParam;

    @Property(name = "User")
    public User user;

    @Property(name = "float[] Parameter")
    public float[] floatArrayParam;

    @Property(name = "String[] Parameter")
    public String[] stringArrayParam;

    @Property(name = "Object[] Parameter")
    public Object[] objectArrayParam;

    @Property(name = "float[][] Parameter")
    public float[][] floatArrayArrayParam;

    @Override
    public void start () {
        System.out.println("User is: " + (user == null ? "null" : ("\n" + user.toString())));
    }

    public static class User {
        private final String name;
        private final int age;
        private final char[] initials;
        private final User parent;

        public User(String name, int age, char[] initials, User parent) {
            this.name = name;
            this.age = age;
            this.initials = initials;
            this.parent = parent;
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
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", initials=" + Arrays.toString(initials) +
                    ", parent=" + parent +
                    '}';
        }
    }
}
