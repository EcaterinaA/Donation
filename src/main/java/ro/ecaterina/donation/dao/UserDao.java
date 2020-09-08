package ro.ecaterina.donation.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import ro.ecaterina.donation.model.User;

public class UserDao {
	
	private static UserDao instance= new UserDao();//se creaza doar o singura instanta la incarcarea clasei in memorie

	private UserDao() { // constructor privat pentru a-l face Singleton		
	}

	public static UserDao getUserDao() {// pentru a putea totusi crea un obiect de tip UserDao
		return instance;//vreau sa-mi returneze instanta creata o singura data mai sus
	}

	//metoda pentru operatia "READ"-by id
	public User readUser(int uid,Connection conn){
		User u1=new User();
		try(PreparedStatement stpReadUser=conn.prepareStatement("SELECT * FROM users WHERE id= " + uid);
				ResultSet rs=stpReadUser.executeQuery()){
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
	public List<User> readAllUser(Connection conn){
		List<User> listUser=new ArrayList<User>();
		try(PreparedStatement stpReadAllUser=conn.prepareStatement("SELECT * FROM users");
				ResultSet rs = stpReadAllUser.executeQuery()) {			
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
	public boolean updateUser(int uid,Connection conn) {
		try(PreparedStatement stpUpdateUser=conn.prepareStatement(" ")){	
		} catch(SQLException e){
			System.out.println("");
		}
		return false;
	}

	//metoda pentru operatia "DELETE"
	public boolean deleteUser(int uid,Connection conn) {
		boolean flag= false;
		String sql="DELETE FROM users WHERE id="+uid;
		try(PreparedStatement stpDeleteUser=conn.prepareStatement(sql);) {
			stpDeleteUser.executeUpdate();
			flag=true;
		}catch(SQLException e) {
			System.out.println("Utilizatorul cu id.ul "+uid+" nu poate fi sters./n"+e.getMessage());
		}
		return flag;
	}

	//metoda pentru operatia "CREATE"
	public boolean createUser(User user,Connection conn) {
		boolean flag=false;//nu s-a executat crearea
		String sql="INSERT INTO users (firstName,lastName,password,phone,email)"
				+ "VALUES('" +user.getFirstName()+"','" + user.getLastName() +"','"+user.getPassword()+"','"+
				user.getPhone()+"','"+user.getEmail()+"')";
		try(PreparedStatement stpCreateUser=conn.prepareStatement(sql)){	
			stpCreateUser.executeLargeUpdate();
			flag=true;
		}catch(SQLException e) {
			System.out.println("Nu s-a putut crea utilizator"+e.getMessage());
		}
		return flag;
	}

	

}


