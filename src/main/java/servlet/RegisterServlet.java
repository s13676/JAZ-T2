package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.rights.UserManager;

import dao.UserRepository;
import domain.User;
import mapper.UserMapper;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        resp.sendRedirect("register_form.jsp");
	    }
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("password_confirm");
		String email = req.getParameter("email");
		
		if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("") 
				|| confirmPassword.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
			resp.getWriter().println("Formularz wypelniony niepoprawnie");
			return;
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		
		if (!user.confirmPassword(confirmPassword)) {
			resp.getWriter().println("Hasla nie pasuja");
			return;
		};
		
		try {
			Connection connection;				
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository userRepo = new UserRepository(connection, new UserMapper());
			
			if (userRepo.getUserWithLogin(username) != null) {
				resp.getWriter().println("Login zostal juz wykorzystany");
				return;
			}
				
			if (userRepo.getAll().size() == 0) {
				// first user has always admin and premium privileges
				user.setAdmin(true);
				user.setPremium(true);
			}
			else {
				user.setAdmin(false);
				user.setPremium(false);
			}
			
			userRepo.add(user);
			resp.sendRedirect("/login");
			
			
		} catch (SQLException e) {
			resp.getWriter().println("Blad polaczenia z baza");
		} 
	}
}