package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrdersDAO;
import dao.PostgreSQLDbDAOFactory;
import domain.Orders;
import domain.OrdersEntity;
import sessionbean.OrdersEntitySessionbeanLocal;

/**
 * Servlet implementation class PaginationServlet
 */
@WebServlet("/PaginationServlet")
public class PaginationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//CDI concept
	@EJB
	private OrdersEntitySessionbeanLocal ordersbean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaginationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		//Write some codes here…
		int nOfPages= 0;
		int currentPage = Integer.valueOf(request.getParameter("currentPage"));
		int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
		
		//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
		try {
		
		//List<Orders> lists = ordersdao.readStaff(currentPage, recordsPerPage);
		List<OrdersEntity> lists = ordersbean.readStaff(currentPage, recordsPerPage);
		
		request.setAttribute("orders", lists);
		//int rows = ordersdao.getNumberOfRows();
		int rows = ordersbean.getNumberOfRows();
		
		nOfPages = rows / recordsPerPage;
		System.out.println("At servlet" + nOfPages);
		if (rows % recordsPerPage != 0) {nOfPages++;
		}
		} //catch (SQLException ex) 
		catch (EJBException ex)
		{
		}
		request.setAttribute("noOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Pagination.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
