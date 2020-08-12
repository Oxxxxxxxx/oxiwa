package sessionbean;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Local;

import domain.Orderdetail;

@Local
public interface OrderdetailSessionbeanLocal {
	
	public List<Orderdetail> getAllOrderdetail() throws EJBException;
	public Orderdetail findOrderdetail(int ordernumber, String productcode) throws EJBException;
	public List<Orderdetail> readOrderdetail(int currentPage, int recordsPerPage, int ordernumber) throws EJBException;
	public List<Orderdetail> getUpdateOrderdetail(int ordernumber, String productcode) throws EJBException;
	public int getNumberOfRows(int ordernumber) throws EJBException ;
	public void updateOrderdetail(String s[]) throws EJBException;
	public void deleteOrderdetail(String id) throws EJBException;
	public void addOrderdetail(String[] s) throws EJBException;

}
