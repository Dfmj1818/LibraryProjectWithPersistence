package Model;

public class Book {

	private int id;
	private String author;
	private String title;
	private String genre;
	private int pages;
	private String language;

	public Book(String author,String title,String genre,int pages,String language){
		this.author=author;
		this.title=title;
		this.genre=genre;
		this.pages=pages;
		this.language=language;
	}

	public void setId(int id){
		this.id=id;
	}

	public int getID() {
		return id;
	}

	public void setAuthor(String author) {
		this.author=author;
	}

	public String getAuthor() {
		return author;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle() {
		return title;
	}

	public void setGenre(String genre) {
		this.genre=genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setPages(int pages) {
		this.pages=pages;
	}

	public int getPages() {
		return pages;
	}

	public void setLanguage(String language){
		this.language=language;
	}

	public String getLanguage() {
		return language;
	}

	@Override

	public String toString() {
		StringBuilder bookInformation=new StringBuilder();
		
		bookInformation.append("ID: ").append(getID()).append(" ")
		.append("Titulo:").append(getTitle()).append(" ")
		.append("Autor: ").append(getAuthor()).append(" ")
		.append("Genero: ").append(getGenre()).append(" ")
		.append("Paginas ").append(getPages()).append(" ")
		.append("Idioma: ").append(getLanguage());

		return bookInformation.toString();

	}

}
