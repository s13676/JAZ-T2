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

import dao.UserRepository;
import domain.User;
import mapper.UserMapper;

@WebServlet({"/", "/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        resp.sendRedirect("login.jsp");
	    }
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		
		if (username.equalsIgnoreCase("") || password.equalsIgnoreCase("")) {
			resp.getWriter().println("Formularz wypelniony niepoprawnie");
			return;
		}
		
		try {
			Connection connection;				
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository userRepo = new UserRepository(connection, new UserMapper());
			
			User user = userRepo.getUserWithLoginAndPass(username, password);
			
			if (user == null) {
				resp.getWriter().println("Bledny login lub haslo.");
				return;
			}
			
			session.setAttribute("user", user);
			session.setMaxInactiveInterval(2000000);
			resp.sendRedirect("/profile");
		} catch (SQLException e) {
			resp.getWriter().println("Blad polaczenia z baza");
		}
    }
	
}
