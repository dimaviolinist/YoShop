package by.itacademy.training.yoshop.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.User;

public class RegistrationController extends HttpServlet {

	private static final long serialVersionUID = -4006561145676424435L;
	private static final String ERROR_ATTRIBUTE = "error";
	private static final String ERROR_NO_LOGIN = "No login";
	private static final String ERROR_LOGIN_ALREADY_EXIST = "Login is already exist";
	private static final String ERROR_NO_EMAIL = "No email";
	private static final String ERROR_EMAIL_ALREADY_USED = "Email is already used";
	private static final String ERROR_NO_NAME = "No name";
	private static final String ERROR_NO_PASSWORD = "No password";
	private static final String ERROR_INTERNAL_SERVER = "Internal Server Error, Please try again later.";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		IDaoFactory factory = DaoFactory.getInstance();
		IUserDao userDAO = factory.getUserDao();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registration.jsp");

		String login = request.getParameter("login");
		if (login == null || login == "" || login == "myusername") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_LOGIN);
			dispatcher.forward(request, response);
			return;
		}
		if (userDAO.checkUserByLogin(login)) {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_LOGIN_ALREADY_EXIST);
			dispatcher.forward(request, response);
			return;
		}

		String email = request.getParameter("email");
		if (email == "myemail") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_EMAIL);
			dispatcher.forward(request, response);
			return;
		}
		if (userDAO.checkUserByEmail(email)) {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_EMAIL_ALREADY_USED);
			dispatcher.forward(request, response);
			return;
		}

		String name = request.getParameter("name");
		if (name == null || name == "" || name == "myname") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_NAME);
			dispatcher.forward(request, response);
			return;
		}

		// Not required
		String surname = request.getParameter("surname");
		if (surname == "mysurname") {
			surname = "";
		}

		String password = request.getParameter("password");
		if (password == null || password == "" || password == "mypassword") {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_NO_PASSWORD);
			dispatcher.forward(request, response);
			return;
		}

		User user = new User();
		user.setEmail(email);
		user.setLogin(login);
		user.setPassword(password);
		user.setName(name);
		user.setSurname(surname);

		boolean result = userDAO.addUser(user);
		if (result == true) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", login);
			dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute(ERROR_ATTRIBUTE, ERROR_INTERNAL_SERVER);
			dispatcher.forward(request, response);
		}
	}

}