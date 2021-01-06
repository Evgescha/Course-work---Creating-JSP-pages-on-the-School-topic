package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.usermanagement.dao.SchoolDAO;
import net.javaguides.usermanagement.model.School;

@WebServlet("/")
public class SchoolServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SchoolDAO DAO;
	
	public void init() {
		DAO = new SchoolDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insert(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				update(request, response);
				break;
			default:
				list(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<School> listUser = DAO.selectAll();
		request.setAttribute("list", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		School existingUser = DAO.select(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("form.jsp");
		request.setAttribute("entity", existingUser);
		dispatcher.forward(request, response);

	}

	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int yearOfFoundation = Integer.parseInt(request.getParameter("yearOfFoundation"));
		String name = request.getParameter("name");
		String adres = request.getParameter("adres");
		School entity = new School(yearOfFoundation, name, adres);
		DAO.insert(entity);
		response.sendRedirect("list");
	}

	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int yearOfFoundation = Integer.parseInt(request.getParameter("yearOfFoundation"));
		String name = request.getParameter("name");
		String adres = request.getParameter("adres");
		School entity = new School(id,yearOfFoundation, name, adres);

		DAO.update(entity );
		response.sendRedirect("list");
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		DAO.delete(id);
		response.sendRedirect("list");

	}

}
