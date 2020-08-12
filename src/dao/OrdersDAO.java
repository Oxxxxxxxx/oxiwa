package dao;

import java.sql.SQLException;
import java.util.List;

import domain.Orders;

//interface CRUD function for orders table

public interface OrdersDAO {
	
	public List<Orders> getAllOrders() throws SQLException;
	public Orders findOrders(String ordernumber) throws SQLException;
	public List<Orders> readStaff(int currentPage, int recordsPerPage) throws SQLException;
	public int getNumberOfRows() throws SQLException ;
	public void updateOrders(String[] s) throws SQLException;
	public void deleteOrders(String id) throws SQLException;
	public void addOrders(String[] s) throws SQLException;

}
