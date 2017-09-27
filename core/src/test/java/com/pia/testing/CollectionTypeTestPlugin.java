package com.pia.testing;

import com.pia.plugin.PiaPlugin;
import com.pia.plugin.annotations.Property;
import com.pia.testing.beans.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class CollectionTypeTestPlugin extends PiaPlugin{
    @Property(name = "List Parameter")
    public List plainList;

    @Property(name = "List<Integer> Parameter")
    public List<Integer> integerList;

    @Property(name = "LinkedList Parameter")
    public LinkedList plainLinkedList;

    @Property(name = "LinkedList<Integer> Parameter")
    public LinkedList<Integer> integerLinkedList;

    @Property(name = "Collection Parameter")
    public Collection collection;

    @Property(name = "HashSet Parameter")
    public HashSet plainHashSet;

    @Property(name = "HashSet<String> Parameter")
    public HashSet<String> stringHashSet;

    @Property(name = "HashSet<User> Parameter")
    public HashSet<User> userHashSet;

    @Property(name = "MyFancyStringList Parameter")
    public MyFancyStringList myFancyStringList;

    @Override
    public String getName () {
        return "Collection Type Test Plugin";
    }

    @Override
    public void start () {

    }

    class MyFancyStringList extends LinkedList<String> {
        private int fancynessLevel = 9001;

        public int getFancynessLevel() {
            return fancynessLevel;
        }
    }
}
