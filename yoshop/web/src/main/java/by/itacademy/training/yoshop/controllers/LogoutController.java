package by.itacademy.training.yoshop.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.training.yoshop.entity.OnlineUsers;

public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = -705898501510094189L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	private void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RequestDispatcher dispatcher;

		HttpSession session = request.getSession(true);
		String user = (String) session.getAttribute("user");
		if (user == null || "".equals(user)) {
			dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
			request.setAttribute("error", "You are not logged already");
			dispatcher.forward(request, response);
			return;
		}
		// TODO
		// Here need save inf from session

		OnlineUsers.removeUser(session.getAttribute("user"));

		session.removeAttribute("user");
		session.removeAttribute("userRole");

		Cookie cookieLogin = new Cookie("login", "");
		Cookie cookieHash = new Cookie("userHash", "");
		cookieLogin.setMaxAge(0);
		cookieHash.setMaxAge(0);
		response.addCookie(cookieLogin);
		response.addCookie(cookieHash);

		dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}
}
