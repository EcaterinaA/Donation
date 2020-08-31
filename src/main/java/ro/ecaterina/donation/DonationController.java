package ro.ecaterina.donation;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.ecaterina.donation.dao.UserDao;
import ro.ecaterina.donation.model.User;

/**
 * Servlet implementation class DonationController
 */
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet()");
		int uid=Integer.parseInt(request.getParameter("uid"));
		System.out.println(uid);
		
		UserDao ud  = UserDao.getUserDao();//pot scrie asa pentru ca metoda e statica
		
		ud.openConnection();
		User u1 = ud.readUser(uid);//Ce se citeste din BD salvez intr-un obiect de tip user pentru a-l trimite in view 
		request.setAttribute("user", u1);//trimit request-ul catre pagina users
		
		List<User> userList=ud.readAllUser();
		request.setAttribute("listUser", userList);
		ud.closeConnection();
		
		RequestDispatcher rd=request.getRequestDispatcher("users.jsp");//redirectionare spre pagina user
		rd.forward(request, response);
	}
	

}
