package sessionbean;

import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.OrdersEntity;

@Local
public interface OrdersEntitySessionbeanLocal {
	
	public List<OrdersEntity> getAllOrders() throws EJBException;
	public OrdersEntity findOrder(String ordernumber) throws EJBException;
	public List<OrdersEntity> readStaff(int currentPage, int recordsPerPage) throws EJBException;
	public int getNumberOfRows() throws EJBException ;
	public void updateOrder(String[] s) throws EJBException;
	public void deleteOrder(String id) throws EJBException;
	public void addOrder(String[] s) throws EJBException;

}
