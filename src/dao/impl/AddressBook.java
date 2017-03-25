
package dao.impl;

import bean.Record;

import java.util.ArrayList;
import java.io.Serializable;

public class AddressBook implements Serializable {

	
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Record> book = new ArrayList<Record>();

	public void addNewRecord(Record newRecord) {
		book.add(newRecord);
	}
	
	public void removeRecord(Record recordToDelete) {
		book.remove(recordToDelete);
	}

	
	public ArrayList<Record> getBook() {
		return book;
	}

	public void setBook(ArrayList<Record> book) {
		this.book = book;
	}
	
	
}