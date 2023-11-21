package Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.BookNotFoundException;

public class User {

	private String name;
	private String lastName;
	private long code;
	private List<Book>borrowedBooksList;

	public User(){
		borrowedBooksList=new ArrayList<Book>();
	}

	public void setBorrowedBooksList(List<Book>borrowedBooksList) {
		this.borrowedBooksList=borrowedBooksList;
	}

	public List<Book>getBorrowedBooksList(){
		return borrowedBooksList;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setLastName(String lastName) {
		this.lastName=lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCode(long code) {
		this.code=code;
	}

	public long getCode() {
		return code;
	}


	public void addBookToList(Book book){
		if(book!=null){
			borrowedBooksList.add(book);
		}
		else {
			throw new NullPointerException();
		}

	}

	public Book selectBook(int selectedBook){
		return  borrowedBooksList.stream()
				.filter(book->book.getID()==selectedBook)
				.findFirst()
				.orElseThrow(BookNotFoundException::new);
	}






}
