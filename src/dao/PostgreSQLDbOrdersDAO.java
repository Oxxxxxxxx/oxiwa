package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Orders;

public class PostgreSQLDbOrdersDAO implements OrdersDAO {

	@Override
	public List<Orders> getAllOrders() throws SQLException {
		// Write some codes here…
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		List<Orders> listoforders = new ArrayList();
		try {
			conn = PostgreSQLDbDAOFactory.createConnection();
			stmt = conn.prepareStatement("select * from classicmodels.orders order by ordernumber");
			rst = stmt.executeQuery();
			while (rst.next()) {
				Orders o = new Orders();
				o.setOrder_number(rst.getInt("ordernumber"));
				o.setOrder_date(rst.getString("orderdate"));
				o.setRequire_date(rst.getString("requireddate"));
				o.setShipped_date(rst.getString("shippeddate"));
				o.setStatus(rst.getString("status"));
				o.setComments(rst.getString("comments"));
				o.setCustomer_number(rst.getInt("customernumber"));
				listoforders.add(o);
			}
		} finally {
			conn.close();
		}
		return listoforders;
	}

	public Orders findOrders(String ordernumber) throws SQLException { 
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		Orders o = null;
		try {
			conn = PostgreSQLDbDAOFactory.createConnection();
			stmt = conn.prepareStatement("select * from classicmodels.orders where ordernumber =?");
			stmt.setInt(1, Integer.valueOf(ordernumber));
			rst = stmt.executeQuery();
			if (!rst.next()) {
				return null;
			} else {
				o = new Orders();
				o.setOrder_number(rst.getInt("ordernumber"));
				o.setOrder_date(rst.getString("orderdate"));
				o.setRequire_date(rst.getString("requireddate"));
				o.setShipped_date(rst.getString("shippeddate"));
				o.setStatus(rst.getString("status"));
				o.setComments(rst.getString("comments"));
				o.setCustomer_number(rst.getInt("customernumber"));
				return o;
				}
			} finally {
				conn.close();
			}
		}
	
	public List<Orders> readStaff(int currentPage, int recordsPerPage) throws SQLException {
		//Write some codes here…
		List<Orders> listoforders = new ArrayList();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
		conn = PostgreSQLDbDAOFactory.createConnection();
		stmt = conn.prepareStatement("select * from classicmodels.orders order by ordernumber OFFSET ? LIMIT ?");
		int start = currentPage * recordsPerPage - recordsPerPage;
		stmt.setInt(1, Integer.valueOf(start));
		stmt.setInt(2, Integer.valueOf(recordsPerPage));
		rst = stmt.executeQuery();
		while (rst.next()) {
			Orders o = new Orders();
			o.setOrder_number(rst.getInt("ordernumber"));
			o.setOrder_date(rst.getString("orderdate"));
			o.setRequire_date(rst.getString("requireddate"));
			o.setShipped_date(rst.getString("shippeddate"));
			o.setStatus(rst.getString("status"));
			o.setComments(rst.getString("comments"));
			o.setCustomer_number(rst.getInt("customernumber"));
			listoforders.add(o);
		}
		} finally {
		conn.close();
		}
		return listoforders;
		}
	
	public int getNumberOfRows() throws SQLException { 
		int i = 0;
		String s = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rst = null;
		try {
		conn = PostgreSQLDbDAOFactory.createConnection();
		stmt = conn.prepareStatement("SELECT COUNT(*) AS totalrow FROM classicmodels.orders");
		rst = stmt.executeQuery();
		while (rst.next()) {
		s = rst.getString("totalrow");
		}
		i = Integer.valueOf(s);
		} finally {
		conn.close();
		}
		return i;
		}
	
	public void updateOrders(String[] s) throws SQLException{ //Write some codes here…
		Connection conn = null;
		//Date dob = null;
		//Date hd = null;
		conn = PostgreSQLDbDAOFactory.createConnection();
		try {
		//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
		//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
		} catch (Exception ex) {
		}
		//java.sql.Date DOB = new java.sql.Date(dob.getTime());
		//java.sql.Date HD = new java.sql.Date(hd.getTime());
		try {
		String sql= "UPDATE classicmodels.orders SET orderdate = ?, requireddate =?, shippeddate = ?, status =?, comments =? WHERE ordernumber =?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, s[1]);
		stmt.setString(2, s[2]);
		stmt.setString(3, s[3]);
		stmt.setString(4, s[4]);
		stmt.setString(5, s[5]);
		stmt.setInt(6, Integer.valueOf(s[0]));
		stmt.executeUpdate();
		}finally {
		conn.close();
		}
		}
	
	public void deleteOrders(String id) throws SQLException
	{//Write some codes here…
	Connection conn = null;
	try {
	conn = PostgreSQLDbDAOFactory.createConnection();
	String sql= "DELETE FROM classicmodels.orders WHERE ordernumber =?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setInt(1, Integer.valueOf(id));
	stmt.executeUpdate();
	}finally {
	conn.close();
	}
	}
	
	public void addOrders(String[] s) throws SQLException
	{//Write some codes here…
	Connection conn = null;
	//Date dob = null;
	//Date hd = null;
	conn = PostgreSQLDbDAOFactory.createConnection();
	try {
	//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
	//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
	} catch (Exception ex) {
	}
	//java.sql.Date DOB = new java.sql.Date(dob.getTime());
	//java.sql.Date HD = new java.sql.Date(hd.getTime());
	try {
	PreparedStatement stmt = conn.prepareStatement("INSERT INTO classicmodels.orders VALUES(?,?,?,?,?,?,?)");
	stmt.setString(1, s[0]);
	stmt.setString(2, s[1]);
	stmt.setString(3, s[2]);
	stmt.setString(4, s[3]);
	stmt.setString(5, s[4]);
	stmt.setString(6, s[5]);
	stmt.setString(7, s[6]);
	stmt.executeUpdate();
	}finally {
	conn.close();
	}
	}

}
