package ro.ecaterina.donation.service;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ro.ecaterina.donation.dao.UserDao;
import ro.ecaterina.donation.model.User;
import ro.ecaterina.donation.util.ConnectionFactory;

public class UserServiceImplementation implements Service<User> {

	private UserDao ud=UserDao.getUserDao();
	private Connection conn;

	public UserServiceImplementation(){//constructor fara argumente
	}

	@Override
	public User read(int id) {
		User u1=new User();
		try{
			conn=ConnectionFactory.openConnection();
			u1=ud.readUser(id,conn);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return u1;
	}

	@Override
	public List<User> readAll() {
		List<User> userList=new ArrayList<User>();
		try {
			conn=ConnectionFactory.openConnection();
			userList=ud.readAllUser(conn);
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return userList;
	}

	@Override
	public boolean update(int id) {
		boolean flag=false;
		try {
			conn=ConnectionFactory.openConnection();
			flag=ud.updateUser(id, conn);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean delete(int id) {
		boolean flag=false;
		try {
			conn=ConnectionFactory.openConnection();
			flag=ud.deleteUser(id,conn);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean create(User user) {
		boolean flag=false;
		try {
			conn=ConnectionFactory.openConnection();
			flag=ud.createUser(user, conn);
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
}
