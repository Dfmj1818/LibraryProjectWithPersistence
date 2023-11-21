package View;

import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import Exceptions.EmptyInputException;
import Exceptions.EmptyListException;
import Model.Book;


public class View {
	private Scanner scanner;

	public View() {
		scanner=new Scanner(System.in);
	}

	public void showMessage(String message){
		System.out.println(message);
	}

	public void showBooksList(List<Book>booksList){
		if(!booksList.isEmpty()){
			booksList.forEach(book->System.out.println(book));
		}
		else {
			throw new EmptyListException();
		}

	}
	public void showList(Stack<Book>booksList){
		if(!booksList.isEmpty()){
			booksList.forEach(System.out::println);
		}
		else{
			throw new EmptyListException();
		}
	}
	
	public void showList(List<?>list){
		if(!list.isEmpty()){
			list.forEach(System.out::println);
		}
		else {
			throw new EmptyListException();
		}
		
	}

	public boolean readBoolean() {
		String inputAsString=scanner.nextLine().trim();
		if(!inputAsString.isEmpty()){
			boolean input=Boolean.parseBoolean(inputAsString);
			return input;
		}
		else {
			throw new EmptyInputException();
		}

	}

	public Long readLong() {
		long input=0;
		String inputAsString=scanner.nextLine().trim();
		if(!inputAsString.isEmpty()&&inputAsString.matches("^[0-9]+$")){
			try {
				input=Long.parseLong(inputAsString);
			}catch(NumberFormatException e) {
				showMessage("Ingresa un numero valido");
			}
			return input;
		}
		else {
			throw new EmptyInputException();
		}
	}

	public int readInt(){
		String inputAsString=scanner.nextLine().trim();
		int input = 0;
		if(!inputAsString.isEmpty()&&inputAsString.matches("^[0-9]+$")){
			try{
				input=Integer.parseInt(inputAsString);			
			}catch(NumberFormatException e){
				showMessage(e.getMessage());
			}
			return input;
		}
		else {
			throw new EmptyInputException();
		}
	}

	public String readString(){
		String input=scanner.nextLine().trim();
		if(!input.isEmpty()){
			return input;
		}
		else {
			throw new EmptyInputException();
		}
	}

}
