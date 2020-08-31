package ro.ecaterina.donation.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ro.ecaterina.donation.model.User;

public class UserDao {
	public static final String CONNECTION_STRING="jdbc:mysql://localhost:3306/mydatabase";
	private Connection conn;
	private Statement st;//pe baza lui se executa interogarile in BD
	private static UserDao instance= new UserDao();//se creaza doar o singura instanta la incarcarea clasei in memorie
	
	private UserDao() { // constructor privat pentru a-l face Singleton		
	}
	
	public static UserDao getUserDao() {// pentru a putea totusi crea un obiect de tip UserDao
		return instance;//vreau sa-mi returneze instanta creata o singura data mai sus
	}
	
	//metoda pentru operatia "READ"-by id
	public User readUser(int uid){
		User u1=new User();
		try {
			ResultSet rs=st.executeQuery("SELECT * FROM users WHERE id= " + uid);
			if(rs.next()) {
				u1.setId(rs.getInt("id"));
				u1.setFirstName(rs.getString("firstName"));
				u1.setLastName(rs.getString("lastName"));
				u1.setPhone(rs.getString("phone"));
				u1.setEmail(rs.getString("email"));
				u1.setAdressId(rs.getInt("adressId"));
				System.out.println("Utilizatorul este "+ u1);
			}
			
		} catch (SQLException e) {
			System.out.println("Nu s-a citi user-ul din BD");
			e.printStackTrace();
		}
		return u1;
	}
	
	//metoda pentru operatia "READ"-all
	public List<User> readAllUser(){
		 List<User> listUser=new ArrayList<User>();
		 try {
			 ResultSet rs = st.executeQuery("SELECT * FROM users");
			 while(rs.next()) {
				 	User u=new User();
					u.setId(rs.getInt("id"));
					u.setFirstName(rs.getString("firstName"));
					u.setLastName(rs.getString("lastName"));
					u.setPassword(rs.getString("password"));
					u.setPhone(rs.getString("phone"));
					u.setEmail(rs.getString("email"));
					u.setAdressId(rs.getInt("adressId"));
					listUser.add(u);
					}	
		 }
		 catch(SQLException e){
			 System.out.println("Nu s-au putut citi userii din BD");
			 e.printStackTrace();
		 }
		 System.out.println("Userii sunt ");
		 /*for(int i=0;i<listUser.size();i++) {
			 System.out.println(listUser.get(i));
		 }*/
		 for(User u1 :listUser) {
			 System.out.println(u1);
		 }
		 return listUser;
	}
	
	
	//metoda pentru operatia "UPDATE"
	public void updateUser(int uid) {
		
	}
	
	//metoda pentru operatia "DELETE"
	public void deleteUser(int uid) {
		
	}
	
	//metoda pentru operatia "CREATE"
	public void createUser(User user) {
		
	}
	
	//metoda pentru a deschide conexiunea cu baza de date
	public boolean openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		    conn= DriverManager.getConnection(CONNECTION_STRING,"root","");
			st=conn.createStatement();//creare obiect de tip Statement care imi executa interogarile in BD
			System.out.println("Conectare cu succes la baza de date");
			return true;
		}
		catch(SQLException e){
			System.out.println("Nu se poate realiza conexiunea la baza de date " + e.getMessage());
			return false;
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Nu s-a putut incarca driveru-ul Driver ");
			e.printStackTrace();
			return false;
		}
	}
	public void closeConnection() {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Nu s-a putut inchide baza de date. ");
				e.printStackTrace();
			}
		}
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				System.out.println("Nu s-a putut inchide resursa Statement. ");
				e.printStackTrace();
			}
		}
	}
} 
