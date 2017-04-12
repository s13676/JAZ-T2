package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserRepository;
import domain.User;
import mapper.UserMapper;

@WebServlet("/admin")
public class AdminPremiumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utils.genereteNavLinks(Utils.getUserFromSession(req), resp);
        
        resp.getWriter().println("<table><tr><th>Nazwa uzytkownika</th><th>Premium</th></tr>");

		try {
			Connection connection;				
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository userRepo = new UserRepository(connection, new UserMapper());
			
			List<User> users = userRepo.getAll();
			
			if (users == null) {
				resp.getWriter().println("Brak uzytkownikow");
				return;
			}
			
			for (User u: users) {
				if (u.getAdmin() == true)
					continue;
				
				resp.getWriter().println("<tr>");
				resp.getWriter().println("<td>"+ u.getUsername() +"</td>");
				
				resp.getWriter().println("<td><form method='post'><input type='hidden' name='id' value='"+u.getId()+"'>");
				if (u.getPremium() == false) {
					resp.getWriter().println("<input type='hidden' name='premium' value='add'>");
					resp.getWriter().println("<input type='submit' value='DODAJ'>");
				} else {
					resp.getWriter().println("<input type='hidden' name='premium' value='remove'>");
					resp.getWriter().println("<input type='submit' value='USUN'>");
				}
				resp.getWriter().println("</form><td>");
				
				resp.getWriter().println("</tr>");
			}
		} catch (SQLException e) {
			resp.getWriter().println("Blad polaczenia z baza");
		}
    }
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String premium = req.getParameter("premium");
		
		try {
			Connection connection;				
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/workdb");
			UserRepository userRepo = new UserRepository(connection, new UserMapper());
			
			User user = userRepo.get(id);
			
			if (premium.equals("add"))
				user.setPremium(true);
			else
				user.setPremium(false);
			
			userRepo.update(user);
			resp.sendRedirect("/admin");
			
		} catch (SQLException e) {
			resp.getWriter().println("Blad polaczenia z baza");
		}
	}
}
