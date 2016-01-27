package by.itacademy.training.yoshop.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.OnlineUsers;
import by.itacademy.training.yoshop.entity.User;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -4602272917509602701L;
	private static final String ERROR_ATTRIBUTE = "error";
	private static final String ERROR_NO_LOGIN = "No login";
	private static final String ERROR_LOGIN_DOES_NOT_EXIST = "Login does not exist";
	private static final String ERROR_NO_PASSWORD = "No password";
	private static final String ERROR_INCORRECT_PASSWORD = "Incorrect password";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");

		IDaoFactory factory = DaoFactory.getInstance();
		IUserDao userDao = factory.getUserDao();

		String login = request.getParameter("login");
		if (login == null || login == "" || login == "myusername") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_LOGIN);
			dispatcher.forward(request, response);
			return;
		}
		if (!userDao.checkUserByLogin(login)) {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_LOGIN_DOES_NOT_EXIST);
			dispatcher.forward(request, response);
			return;
		}

		String password = request.getParameter("password");
		if (password == null || password == "" || password == "mypassword") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_PASSWORD);
			dispatcher.forward(request, response);
			return;
		}

		User user = userDao.loginUser(login, password);
		if (user == null) {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_INCORRECT_PASSWORD);
			dispatcher.forward(request, response);
			return;
		}
		if (user.getIsBannedUntil() != null) {
			if (user.getIsBannedUntil().after(new Date())) {
				request.setAttribute("error", "You are BANNED until" + user.getIsBannedUntil() + ". \n\t SHAME.");
				getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
				return;
			}
		}

		if (OnlineUsers.addUser(login) == false) {
			request.setAttribute("error", "You are already logged. Or not you. :)");
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession(true);
		session.setAttribute("user", login);

		// TODO
		// change last two if their places for admin can not enter automatically
		System.out.println(request.getParameter("rememberMe"));
		if (request.getParameter("rememberMe") != null) {

			Cookie cookieLogin = new Cookie("login", user.getLogin());
			Cookie cookieHash = new Cookie("userHash", Integer.toString(user.hashCode()));
			cookieLogin.setMaxAge(168 * 3600); // a week
			cookieHash.setMaxAge(168 * 3600); // a week
			response.addCookie(cookieLogin);
			response.addCookie(cookieHash);
		}
		if ("admin".equals(user.getRole())) {
			session.setAttribute("userRole", "admin");
			dispatcher = getServletContext().getRequestDispatcher("/AdminPage");
			dispatcher.forward(request, response);
			return;
		}
		// Successfully passed
		dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		return;
	}
}