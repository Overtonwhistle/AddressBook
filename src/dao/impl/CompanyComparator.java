package dao.impl;

import java.util.Comparator;

import bean.Record;

public class CompanyComparator implements Comparator<Record>{
	@Override 
    public int compare(Record a, Record b){
     
        return a.getCompany().compareTo(b.getCompany());
    }
}
