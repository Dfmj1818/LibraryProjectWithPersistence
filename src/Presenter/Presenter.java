package Presenter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import com.google.gson.internal.LinkedTreeMap;

import Exceptions.BookNotFoundException;
import Exceptions.IncorrectFormatException;
import Exceptions.UserNotFoundException;
import Model.Book;
import Model.UserManager;
import Persistence.FileApi;
import Model.LibraryManager;
import Model.User;
import Model.UserManager;
import View.View;

public class Presenter {

	private final LibraryManager libraryManager;
	private final View view;
	private final UserManager userManager;
	private final FileApi fileApi;

	public Presenter() {
		libraryManager=new LibraryManager();
		view=new View();
		userManager=new UserManager();
		fileApi=new FileApi();

	}

	public static void main(String[]args) throws IOException{
		Presenter presenter=new Presenter();
		presenter.createBooks();
		presenter.loginUserMenu();

	} 


	public void loginUserMenu() {
		int selectedOption;
		boolean exit=false;

		while(!exit) {
			view.showMessage("Bienvenido Estudiante,Que desea Hacer Hoy?\n1.Registrarse\n2.Iniciar Sesion\n3.Salir");
			selectedOption=view.readInt();

			switch(selectedOption){
			case 1:
				User createdUser=registerUser();
				if(createdUser!=null){
					userManager.addUserToDataBase(createdUser);
					entryMenu(createdUser);
				}
				else {
					throw new NullPointerException();
				}
				break;
			case 2:
				User user=loginUser();
				entryMenu(user);
				break;
			case 3:
				exit=true;
				break;
			default:
				view.showMessage("La opcion Digitada No Existe");
				break;
			}
		}

	}

	public User loginUser() {
		long cardCode;
		User foundUser = null;
		view.showMessage("INICIAR SESION");
		view.showMessage("Digita el numero Del Carnet Estudiantil");
		cardCode=view.readLong();
		try {
			userManager.checkStudentCode(cardCode);
			foundUser=userManager.foundUserInDataBase(cardCode);
		}catch(UserNotFoundException e){
			view.showMessage(e.getMessage());
		}catch(IncorrectFormatException e){
			view.showMessage(e.getMessage());
		}
		return foundUser;
	}

	public User registerUser() {
		long code = 0;
		String name;
		String lastName;
		boolean correctFormat=false;

		while(!correctFormat){
			view.showMessage("Ingresa Tu codigo estudiantil");
			code=view.readLong();
			try {
				userManager.checkStudentCode(code);
				correctFormat=true;
			}catch(IncorrectFormatException e){
				view.showMessage(e.getMessage());
			}	
		}
		view.showMessage("Ingresa Tu Nombre");
		name=view.readString();
		view.showMessage("Ingresa Tu apellido");
		lastName=view.readString();
		User createdUser=userManager.createUser(code, name, lastName);
		return createdUser;

	}

	public void entryMenu(User user){
		int digitedOption;
		boolean exit=false;

		while(!exit) {
			view.showMessage("Bienvenido a la Biblioteca\nA Continuacion escoge la opcion que desees");
			view.showMessage("1.Ver Libros disponibles\n2.Devolver Libro.\n3.Ver Tus Libros Prestados");
			digitedOption=view.readInt();
			switch(digitedOption){
			case 1:
				viewAvaiablesBook();
				borrowBook(user);
				break;
			case 2: 
				returnBook(user);
				break;
			case 3:
				viewUserBorrowedBooks(user);
				exportUserBorrowedBooksToJson(user);
				break;

			default:
				view.showMessage("La Opcion digitada No Existe");
				break;
			}
		}


	}



	public void returnBook(User user){
		int selectedId;
		view.showMessage("Ingresa el ID del libro que desees devolver");
		selectedId=view.readInt();
		Book selectedBook=user.selectBook(selectedId);
		libraryManager.addBookToStack(selectedBook);
	}

	public void borrowBook(User user){
		int digitedId = 0;
		String userAnswer;
		Book bookToBorrow = null;
		boolean exit=false;
     
		view.showMessage("¿Deseas Pedir Algun libro Prestado?");
		userAnswer=view.readString();
		if(userAnswer.toLowerCase().equals("yes")){
			while(!exit){
				view.showMessage("Ingresa el ID del libro que deseas pedir prestado");
				digitedId=view.readInt();
				try {
					bookToBorrow=libraryManager.selectBook(user, digitedId);
					exit=true;
				}catch(BookNotFoundException e){
					view.showMessage(e.getMessage());
				}
			}	
			libraryManager.removeBook(digitedId);
			view.showMessage("El libro "+bookToBorrow.getTitle()+" Fue Solicitado Correctamente");
			user.addBookToList(bookToBorrow);

		}

	}

	public void exportUserBorrowedBooksToJson(User user){
		List<Book>userBorrowedsBooksList=user.getBorrowedBooksList();
		fileApi.convertListToJson(userBorrowedsBooksList, user.getName()+" "+user.getLastName()+"Borrowed Books");
	}

	public void viewAvaiablesBook(){
		List<Book>booksList=libraryManager.getBooksList();
		view.showMessage("Estos Son nuestros Libros Disponibles");
		view.showBooksList(booksList);
	}

	public void createBooks() throws IOException {
		List<Book> booksList=fileApi.readJson("salida.json");
		libraryManager.setBooksList(booksList);
		libraryManager.setBooksId();
	}

	public void viewUserBorrowedBooks(User user) {
		view.showMessage("Estos Son Tus Libros Prestados Hasta el Momento\n");
		view.showList(user.getBorrowedBooksList());
	}

	//public void viewBooksInJsonFormat(User user) {
	//List<Book>borrowedsBooksList=user.getBorrowedBooksList();
	//String listAsJson=fileApi.convertListToJson(borrowedsBooksList);
	//view.showMessage(listAsJson);
	//}




}
