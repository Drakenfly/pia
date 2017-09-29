package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.testing.beans.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class ListTypeTestPlugin extends PiaPlugin{
    @Property(name = "Integer object List")
    public LinkedList<Integer> intObjectList;

    @Property(name = "User List")
    public ArrayList<User> userList;

    @Property(name = "User List List")
    public HashSet<LinkedList<User>> userListList;

    @Property(name = "String object List List List List")
    public LinkedHashSet<ArrayList<LinkedList<String>>> stringObjectListListList;

    @Property(name = "String object Li x12 ")
    public Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<Li<String>>>>>>>>>>>> stringMayham;

    @Property(name = "int array List")
    public LinkedList<int[]> intArrayList;

    @Property(name = "int array List List")
    public Li<LinkedList<int[]>> intArrayListList;

    @Property(name = "user array List")
    public LinkedList<User[]> userArrayList;

    @Override
    public String getName () {
        return "Plugin with Base Types";
    }

    @Override
    public void start () {

    }

    public static class Li<T> extends LinkedList<T> {
        public Li (String uselessVariable){

        }
    }
}
