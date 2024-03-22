package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.customerdao;
import model.customer;

/**
 * Servlet implementation class customercontroller
 */
@WebServlet("/customercontroller")
public class customercontroller extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public customercontroller() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if (action.equalsIgnoreCase("register")) {
			customer c = new customer();
			c.setFname(request.getParameter("fname"));
			c.setLname(request.getParameter("lname"));
			c.setEmail(request.getParameter("email"));
			c.setMobile(Long.parseLong(request.getParameter("mobile")));
			c.setGender(request.getParameter("gender"));
			c.setPassword(request.getParameter("password"));
			System.out.println(c);
			customerdao.insertCustomer(c);
			request.setAttribute("msg", "data inserted successfully");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if (action.equalsIgnoreCase("login")) {
			customer c = new customer();
			c.setEmail(request.getParameter("email"));
			c.setPassword(request.getParameter("password"));
			customer c1 = customerdao.dologin(c);
			if (c1 == null) {
				request.setAttribute("login", "email or password is incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("data", c1);
				request.getRequestDispatcher("show.jsp").forward(request, response);
			}
		}
	}
}