package by.itacademy.training.yoshop.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.itacademy.training.yoshop.dao.IDaoFactory;
import by.itacademy.training.yoshop.dao.IUserDao;
import by.itacademy.training.yoshop.dao.impl.DaoFactory;
import by.itacademy.training.yoshop.entity.User;

public class AdminController extends HttpServlet {

	private static final long serialVersionUID = -8474881949625904578L;

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

		IDaoFactory factory = DaoFactory.getInstance();
		IUserDao userDao = factory.getUserDao();

		List<User> userList = userDao.userList();
		if (!userList.isEmpty()) {
			request.setAttribute("userList", userList);
			getServletContext().getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
			return;
		} else {
			request.setAttribute("noUsers", "No users. You are loser, mwahahaha");
			getServletContext().getRequestDispatcher("/WEB-INF/userList.jsp").forward(request, response);
			return;
		}
	}

}
