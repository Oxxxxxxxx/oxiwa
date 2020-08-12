package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import utilities.RedirectOrderdetailsUpdate;
import utilities.ValidateManageLogic;

/**
 * Servlet implementation class OrderdetailController
 */
@WebServlet("/OrderdetailController")
public class OrderdetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private OrderdetailSessionbeanLocal orderdetailbean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderdetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
				String sordernumber = request.getParameter("ordernumber");
				String productcode = request.getParameter("productcode");
				
				int ordernumber = Integer.parseInt(sordernumber);
				//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
				
				
				try {
				//Orders orders = ordersdao.findOrders(ordernumber);
					System.out.println("SSSSSSSSSSSSSSSS");
					Orderdetail orderdetail = orderdetailbean.findOrderdetail(ordernumber, productcode);
					System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA");
				request.setAttribute("orderdetails", orderdetail);
				RequestDispatcher req = request.getRequestDispatcher("OrderDetailUpdate.jsp");
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
				String orderid = request.getParameter("productcode");
				String quantityordered = request.getParameter("quantityordered");
				String priceeach = request.getParameter("priceeach");
				String orderlinenumber = request.getParameter("orderlinenumber");
				PrintWriter out = response.getWriter();
				
				
//				int ordernumber = Integer.parseInt(getordernumber);
//				Integer quantityordered = Integer.parseInt(getquantityordered);
//				Double doublepriceeach = Double.parseDouble(getpriceeach);
//				BigDecimal priceeach = BigDecimal.valueOf(doublepriceeach);
//				Integer orderlinenumber = Integer.parseInt(getorderlinenumber);
				
				
				// this line is to package the whole values into one array string variable -
				// easier just pass one parameter object
				String[] s = { ordernumber, orderid, quantityordered, priceeach, orderlinenumber};
				//OrdersDAO ordersdao = new PostgreSQLDbDAOFactory().getOrdersDAO();
				
				try {
				if (ValidateManageLogic.validateManageTrainer(request).equals("UPDATE")) {
				// call session bean updateEmployee method
				//ordersdao.updateOrders(s);
					orderdetailbean.updateOrderdetail(s);
				}
				else if (ValidateManageLogic.validateManageTrainer(request).equals("DELETE")) {
				// call session bean deleteEmployee method
				//ordersdao.deleteOrders(ordernumber);
					orderdetailbean.deleteOrderdetail(orderid);
				// if ADD button is clicked
				} else {
				// call session bean addEmployee method
				//ordersdao.addOrders(s);
					orderdetailbean.addOrderdetail(s);
				}
				// this line is to redirect to notify record has been updated and redirect to
				// another page
				RedirectOrderdetailsUpdate.navigateJS(out);
				} catch (EJBException ex) {
				}
	}


}
