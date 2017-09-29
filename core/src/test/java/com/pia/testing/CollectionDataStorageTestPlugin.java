package com.pia.testing;


import com.pia.core.annotations.Property;
import com.pia.core.plugin.PiaPlugin;

import java.util.*;

public class CollectionDataStorageTestPlugin extends PiaPlugin {
	@Property(name = "StringLinkedList Object")
    public LinkedList<String> stringLinkedList;

	@Property(name = "StringArrayList Object")
    public ArrayList<String> stringArrayList;

	@Property(name = "StringList Object")
    private List<String> stringList;
    
	@Property(name = "IntegerHashSet Object")
    public HashSet<int[]> integerHashSet;

	@Property(name = "StringLinkedListSet Object")
    public Set<LinkedList<String>> stringLinkedListSet;

	@Property(name = "StringListSet Object")
    public Set<List<String>> stringListSet;
    
    
    @Override
    public String getName () {
        return "Collection Data Storage Test Plugin";
    }

    @Override
    public void start () {

    }
}
