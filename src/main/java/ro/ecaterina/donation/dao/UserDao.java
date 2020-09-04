package ro.ecaterina.donation.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ro.ecaterina.donation.model.User;

public class UserDao {
	public static final String CONNECTION_STRING="jdbc:mysql://localhost:3306/mydatabase";
	private Connection conn=null;
	private PreparedStatement stpReadUser;//pe baza lui se executa interogarile in BD->pentru citire utilizator
	private PreparedStatement stpReadAllUser;//pentru operatia de citire a tuturor utilizatorilor
	private PreparedStatement stpCreateUser;//pentru operatia de creare utilizator
	private PreparedStatement stpUpdateUser;//pentru operatia de modificare utilizator
	private PreparedStatement stpDeleteUser;//pentru operatia de stergere utilizator

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
			stpReadUser=conn.prepareStatement("SELECT * FROM users WHERE id= " + uid);//creare conexiune pt fiecare statement corespunzator metodelor CRUD
			ResultSet rs=stpReadUser.executeQuery();
			if(rs.next()) {
				u1.setId(rs.getInt("id"));
				u1.setFirstName(rs.getString("firstName"));
				u1.setLastName(rs.getString("lastName"));
				u1.setPhone(rs.getString("phone"));
				u1.setEmail(rs.getString("email"));
				u1.setAdressId(rs.getInt("adressId"));
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
			stpReadAllUser=conn.prepareStatement("SELECT * FROM users");//creez conexiune si atribui interogarea, prepareStatementurile se creeaza cu interogari in ele
			ResultSet rs = stpReadAllUser.executeQuery();//execut interogarea de sus
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
		return listUser;
	}


	//metoda pentru operatia "UPDATE"
	public void updateUser(int uid) {

	}

	//metoda pentru operatia "DELETE"
	public boolean deleteUser(int uid) {
		boolean flag= false;
		try {
			String sql="DELETE FROM users WHERE id="+uid;
			stpDeleteUser=conn.prepareStatement(sql);
			stpDeleteUser.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			System.out.println("Utilizatorul cu id.ul "+uid+" nu poate fi sters./n"+e.getMessage());
		}
		return flag;
	}

	//metoda pentru operatia "CREATE"
	public boolean createUser(User user) {
		boolean flag=false;//nu s-a executat crearea
		try{
			String sql="INSERT INTO users (firstName,lastName,password,phone,email)"
					+ "VALUES('" +user.getFirstName()+"','" + user.getLastName() +"','"+user.getPassword()+"','"+
					user.getPhone()+"','"+user.getEmail()+"')";
			stpCreateUser=conn.prepareStatement(sql);
			stpCreateUser.executeLargeUpdate();
			flag=true;
		}catch(SQLException e) {
			System.out.println("Nu s-a putut crea utilizator"+e.getMessage());
		}
		return flag;
	}

	//metoda pentru a deschide conexiunea cu baza de date
	public boolean openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn= DriverManager.getConnection(CONNECTION_STRING,"root","");//pentru a deschide conexiunea
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
		try {
			if(stpReadUser!=null) {
				stpReadUser.close();
			}
			if(stpReadAllUser!=null) {
				stpReadAllUser.close();
			}
			if(stpCreateUser!=null) {
				stpCreateUser.close();
			}
			if(stpUpdateUser!=null) {
				stpUpdateUser.close();
			}
			if(stpDeleteUser!=null) {
				stpDeleteUser.close();
			}
			if(conn!=null) {
				conn.close();
			}
		}catch (SQLException e) {
			System.out.println("Nu s-a putut inchide baza de date. ");
			e.printStackTrace();
		}
	}
}


