package Model;

import java.util.List;
import java.util.Stack;

import Exceptions.BookNotFoundException;
import Exceptions.EmptyListException;

public class LibraryManager {

	private List<Book>booksList;
	private int id;

	public LibraryManager() {
		booksList=new Stack<Book>();
		id=0;
	}

	public void addBookToStack(Book book){
		booksList.add(book);
	}
	
	public void removeBook(int bookToRemove) {
		booksList.removeIf(book->book.getID()==bookToRemove);
	}

	public void setBooksList(List<Book>booksList){
		if(!booksList.isEmpty()){
			this.booksList=booksList;
		}
		else{
			throw new EmptyListException();
		}
		
	}
	
	public List<Book>getBooksList(){
		return booksList;
	}

	public void setBooksId() {
		booksList.forEach(book->{
			id++;
			book.setId(id);
		});
	}

	public Book selectBook(User user,int selectedId){
		return booksList.stream()
				.filter(book->book.getID()==selectedId)
				.findFirst()
				.orElseThrow(BookNotFoundException::new);

	}

}
