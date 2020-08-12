package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OrdersController
 */
@WebServlet("/OrdersController")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private OrdersEntitySessionbeanLocal ordersbean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String ordernumber = request.getParameter("ordernumber");
		//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
		
		
		try {
		//Orders orders = ordersdao.findOrders(ordernumber);
			OrdersEntity orders = ordersbean.findOrder(ordernumber);
			
		request.setAttribute("orders", orders);
		RequestDispatcher req = request.getRequestDispatcher("OrdersUpdate.jsp");
		req.forward(request, response);
		} 
		//catch (SQLException ex) 
		catch (EJBException ex)
		{
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String ordernumber = request.getParameter("ordernumber");
		String orderdate = request.getParameter("orderdate");
		String requireddate = request.getParameter("requireddate");
		String shippeddate = request.getParameter("shippeddate");
		String status = request.getParameter("status");
		String comments = request.getParameter("comments");
		String customernumber = request.getParameter("customernumber");
		PrintWriter out = response.getWriter();
		// this line is to package the whole values into one array string variable -
		// easier just pass one parameter object
		String[] s = { ordernumber, orderdate, requireddate, shippeddate, status, comments,customernumber };
		//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
		
		try {
		if (ValidateManageLogic.validateManageTrainer(request).equals("UPDATE")) {
		// call session bean updateEmployee method
		//ordersdao.updateOrders(s);
			ordersbean.updateOrder(s);
		}
		else if (ValidateManageLogic.validateManageTrainer(request).equals("DELETE")) {
		// call session bean deleteEmployee method
		//ordersdao.deleteOrders(ordernumber);
			ordersbean.deleteOrder(ordernumber);
		// if ADD button is clicked
		} else {
		// call session bean addEmployee method
		//ordersdao.addOrders(s);
			ordersbean.addOrder(s);
		}
		// this line is to redirect to notify record has been updated and redirect to
		// another page
		ValidateManageLogic.navigateJS(out);
		} catch (EJBException ex) {
		}
	}

}
