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
import ro.ecaterina.donation.service.UserServiceImplementation;

/**
 * Servlet implementation class DonationController
 */
public class DonationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserServiceImplementation usi=new UserServiceImplementation();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet()");
		int uid=Integer.parseInt(request.getParameter("uid"));

		String actiune=request.getParameter("action");//Preiau action din index.jsp la apasarea butonului
		if(actiune == null) {
			actiune = "afisare";
		}
		switch(actiune) {
		case "afisare":
			
			//Afisare utilizator
			User u1 = usi.read(uid);//Ce se citeste din BD salvez intr-un obiect de tip user pentru a-l trimite in view 
			request.setAttribute("user", u1);//trimit request-ul catre pagina users
			
			//Afisare lista utilizatori
			afisare(request);
			break;
			
		case "stergere":
			//Stergere utiliztor
			usi.delete(uid);	
			afisare(request);
			break;
		
		default:
			break;
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("users.jsp");//redirectionare spre pagina user
		rd.forward(request, response);
	}

	private void afisare(HttpServletRequest request) {
		List<User> userList=usi.readAll();
		request.setAttribute("listUser", userList);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nume=req.getParameter("lastName");
		String prenume=req.getParameter("firstName");
		String parola=req.getParameter("password");
		String telefon=req.getParameter("phone");
		String email=req.getParameter("email");
		User u3=new User(nume,prenume,parola,telefon,email);
		usi.create(u3);
		
		RequestDispatcher rd=req.getRequestDispatcher("index.jsp");//redirectionare spre pagina index
		rd.forward(req, resp);


	}
}
