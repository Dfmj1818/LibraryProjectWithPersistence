package Persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

import Model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Exceptions.EmptyListException;
import View.View;

public class FileApi {
	private final Gson gson;
	private final View view;
	private final String pcRoute;

	public FileApi() {
		gson=new GsonBuilder().setPrettyPrinting().create();
		view=new View();
		pcRoute="C:\\Users\\pepeg\\Downloads\\proyectos\\LibraryProject\\src\\Persistence\\";
	}


	public String convertListToTxt(List<?>booksList,String fileName){
		String listAsTxt=null;

		if(!booksList.isEmpty()){
			try {
				FileWriter fileWriter=new FileWriter(pcRoute+File.separator+fileName+".txt");
				listAsTxt=fileWriter.toString();
				fileWriter.write(listAsTxt);
			}catch(IOException e){
				view.showMessage(e.getMessage());
			}
			return listAsTxt;
		}
		else {
			throw new EmptyListException();
		}
	}

	public String readTxtFormat(String fileName) {
		StringBuilder stringBuilder=new StringBuilder();
		String textLine;
		try {
			BufferedReader bufferedReader=new BufferedReader(new FileReader(pcRoute+File.separator+fileName));
			while((textLine=bufferedReader.readLine())!=null) {
				stringBuilder.append(textLine).append("\n");
			}
		}catch(FileNotFoundException e){
			view.showMessage(e.getMessage());
		}catch(IOException e){
			view.showMessage(e.getMessage());
		}

		return stringBuilder.toString();
	}


	public String convertListToJson(List<?>booksList,String fileName){
		String listAsJson = null;

		if(!booksList.isEmpty()){
			try {
				FileWriter fileWriter=new FileWriter(pcRoute+ File.separator + fileName+".json");
				listAsJson=gson.toJson(booksList);
				fileWriter.write(listAsJson);
				fileWriter.close();
			}catch(IOException e){
				view.showMessage(e.getMessage());
			}
			return listAsJson;
		}
		else {
			throw new EmptyListException();
		}
	}

	public List<Book>readJson(String fileName){
		List<Book>deserializedList = null;
		try { 
			BufferedReader bufferedReader=new BufferedReader(new FileReader(pcRoute+File.separator+fileName));
			deserializedList=gson.fromJson(bufferedReader, new TypeToken<List<Book>>(){}.getType());
		}catch(FileNotFoundException e){
			view.showMessage(e.getMessage());
		}catch(JsonSyntaxException e){
			view.showMessage(e.getMessage());
		}
		return deserializedList;
	}


}