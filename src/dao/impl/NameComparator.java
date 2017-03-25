package dao.impl;

import java.util.Comparator;

import bean.Record;

public class NameComparator implements Comparator<Record>{
	@Override 
    public int compare(Record a, Record b){
     
        return a.getName().compareTo(b.getName());
    }
}
