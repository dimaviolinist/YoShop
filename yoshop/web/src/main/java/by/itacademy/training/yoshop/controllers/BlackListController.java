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

public class BlackListController extends HttpServlet {

	private static final long serialVersionUID = 7044971684596547945L;

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

		System.out.println("BlackListController");

		IDaoFactory factory = DaoFactory.getInstance();
		IUserDao userDao = factory.getUserDao();

		List<User> blackList = userDao.blackList();

		request.setAttribute("blackList", blackList);
		getServletContext().getRequestDispatcher("/WEB-INF/blackList.jsp").forward(request, response);
		return;
	}
}
