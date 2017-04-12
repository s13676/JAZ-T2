package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;

public class Utils {
	public static void genereteNavLinks(User user, HttpServletResponse resp) throws IOException {
		resp.getWriter().println("<a href='/profile'>PROFIL</a> ");
		if (user.getPremium() == true)
        	resp.getWriter().println("<a href='/premium'>PREMIUM</a> ");
        if (user.getAdmin() == true)
        	resp.getWriter().println("<a href='/admin'>USTAW PREMIUM</a> ");
        resp.getWriter().println("<a href='/logout'>WYLOGUJ</a>");
        resp.setContentType("text/html");
	}
	
	public static User getUserFromSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		return user;
	}
}
