package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = Utils.getUserFromSession(req);
        
		resp.getWriter().println("<b>Nazwa uzytkownika: </b>" + user.getUsername() + "<br>");
        resp.getWriter().println("<b>Haslo: </b>" + user.getPassword() + "<br>");
        resp.getWriter().println("<b>Email: </b>" + user.getEmail() + "<br>");
        
        Utils.genereteNavLinks(user, resp);
    }
}
