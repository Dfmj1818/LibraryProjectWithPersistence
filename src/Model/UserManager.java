package Model;

import java.util.ArrayList;
import java.util.List;

import Exceptions.IncorrectFormatException;
import Exceptions.UserNotFoundException;

public class UserManager {
	private List<User>usersDataBase;


	public UserManager() {
		usersDataBase=new ArrayList<User>();
	}

	public void addUserToDataBase(User user){
		if(user!=null){
			usersDataBase.add(user);
		}
		else {
			throw new NullPointerException();
		}
	}

	public User createUser(long code,String name,String lastName){
		User user=new User();
		user.setCode(code);
		user.setName(name);
		user.setLastName(lastName);
		return user;
	}

	public User foundUserInDataBase(long code){
		return usersDataBase.stream()
			   .filter(user->user.getCode()==code)
			   .findFirst()
			   .orElseThrow(UserNotFoundException::new);
	}

	public long checkStudentCode(long code) {
		String codeAsString=String.valueOf(code);
		if(!codeAsString.isEmpty()&&codeAsString.matches("^[0-9]{10}$")){
			return code;
		}
		else {
			throw new IncorrectFormatException();
		}
	}
	

	public void setUserDataBase(List<User>usersDataBase){
		this.usersDataBase=usersDataBase;
	}

	public List<User>getUserDataBase(){
		return usersDataBase;
	}

}
