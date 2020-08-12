package controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Orderdetail;
import domain.OrdersEntity;
import sessionbean.OrderdetailSessionbeanLocal;
import sessionbean.OrdersEntitySessionbeanLocal;


/**
 * Servlet implementation class OrderdetailPagination
 */
@WebServlet("/OrderdetailPagination")
public class OrderdetailPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private OrderdetailSessionbeanLocal orderdetailbean;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderdetailPagination() {
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
		int currentPage = 1;
		int recordsPerPage = 70;
		String Sordernumber = request.getParameter("ordernumber");
		int ordernumber = Integer.parseInt(Sordernumber);
		//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
		try {
		
		//List<Orders> lists = ordersdao.readStaff(currentPage, recordsPerPage);
		//List<OrdersEntity> lists = ordersbean.readStaff(currentPage, recordsPerPage);
			List<Orderdetail> lists = orderdetailbean.readOrderdetail(currentPage, recordsPerPage, ordernumber);
			request.setAttribute("orderdetails", lists);
	
		//int rows = ordersdao.getNumberOfRows();
		int rows = orderdetailbean.getNumberOfRows(ordernumber);
		
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("OrderDetails.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
