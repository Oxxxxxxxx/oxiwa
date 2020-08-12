package sessionbean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.Orderdetail;
import domain.OrderdetailPK;
import domain.OrdersEntity;

/**
 * Session Bean implementation class OrderdetailSessionbean
 */
@Stateless
public class OrderdetailSessionbean implements OrderdetailSessionbeanLocal {

	
	@PersistenceContext(unitName = "Assigment")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public OrderdetailSessionbean() {
        // TODO Auto-generated constructor stub
    }

    public List<Orderdetail> getAllOrderdetail() throws EJBException {
    	//Write some codes here…
    	return em.createNamedQuery("Orderdetail.findAll").getResultList();
    	}
    
    public List<Orderdetail> readOrderdetail(int currentPage, int recordsPerPage, int ordernumber) throws EJBException {
    	//Write some codes here…
    	int start = currentPage * recordsPerPage - recordsPerPage;
    	Query q = em.createNativeQuery("SELECT * FROM classicmodels.orderdetails WHERE ordernumber = ? ",
    	Orderdetail.class);
    	//q.setParameter(1, start);
    	//q.setParameter(2, recordsPerPage);
    	q.setParameter(1, ordernumber);
    	List<Orderdetail> results = q.getResultList();
    	return results;

    	}
    
    public List<Orderdetail> getUpdateOrderdetail(int ordernumber, String productcode) throws EJBException {
    
    	Query q = em.createNativeQuery("SELECT * FROM classicmodels.orderdetails WHERE ordernumber = ? AND productcode = ?",
    	Orderdetail.class);
    	//q.setParameter(1, start);
    	//q.setParameter(2, recordsPerPage);
    	q.setParameter(1, ordernumber);
    	q.setParameter(2, productcode);
    	List<Orderdetail> results = q.getResultList();
    	return results;
    }
    
    public int getNumberOfRows(int ordernumber) throws EJBException {
    	//Write some codes here…
    	Query q = em.createNativeQuery(" SELECT COUNT(*) FROM classicmodels.orderdetails WHERE ordernumber = ? ");
    	q.setParameter(1, ordernumber);
    	BigInteger results = (BigInteger) q.getSingleResult();
    	int i = results.intValue();
    	return i;
    	}
    
    public Orderdetail findOrderdetail(int ordernumber, String productcode) throws EJBException {
    	//Write some codes here…
    	//Query q = em.createNativeQuery("SELECT * FROM classicmodels.orderdetails WHERE ordernumber = ? AND productcode = ?");	
    	//q.setParameter(1, ordernumber);
    	//q.setParameter(2, productcode);
    	Query q = em.createNamedQuery("Orderdetail.findbyId");
    	q.setParameter("ordernumber", ordernumber);
    	q.setParameter("productcode", productcode);
    	return (Orderdetail) q.getSingleResult();
    	}
    
    public void updateOrderdetail(String s[]) throws EJBException {
    	//Write some codes here…
    	//Date dob = null;
    	//Date hd = null;
    	int ordernumber = Integer.parseInt(s[0]);
		Integer quantityordered = Integer.parseInt(s[2]);
		Double doublepriceeach = Double.parseDouble(s[3]);
		BigDecimal priceeach = BigDecimal.valueOf(doublepriceeach);
		Integer orderlinenumber = Integer.parseInt(s[4]); 
    	Orderdetail e = findOrderdetail(ordernumber,s[1]);
    	
    	try {
    	//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
    	//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
    	} catch (Exception ex) {
    	}
    	//java.sql.Date DOB = new java.sql.Date(dob.getTime());
    	//java.sql.Date HD = new java.sql.Date(hd.getTime());
    	e.setQuantityordered(quantityordered);
    	e.setPriceeach(priceeach);
    	e.setOrderlinenumber(orderlinenumber);
    	em.merge(e);
    	}
    
    public void deleteOrderdetail(String id) throws EJBException {
    	//Write some codes here…
    	//Orderdetail e = findOrderdetail(id);
    	//em.remove(e);
    	}
    
    
    public void addOrderdetail(String[] s) throws EJBException {
    	//Write some codes here…
    	//Date dob = null;
    	//Date hd = null;
    	int ordernumber = Integer.parseInt(s[0]);
		Integer quantityordered = Integer.parseInt(s[2]);
		Double doublepriceeach = Double.parseDouble(s[3]);
		BigDecimal priceeach = BigDecimal.valueOf(doublepriceeach);
		Integer orderlinenumber = Integer.parseInt(s[4]); 
		//OrdersEntity order = new OrdersEntity();
		//OrderdetailPK test = new OrderdetailPK();
    	//Orderdetail e = findOrderdetail(ordernumber,s[1]);
    	try {
    	//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
    	//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
    	} catch (Exception ex) {
    	}
    	Orderdetail e = new Orderdetail();
    	//java.sql.Date DOB = new java.sql.Date(dob.getTime());
    	//java.sql.Date HD = new java.sql.Date(hd.getTime());
    	//e.setOrder(OrdersEntity.);
    	//e.setOrder(order);
    	//e.setId(test);
    	e.setQuantityordered(quantityordered);
    	e.setPriceeach(priceeach);
    	e.getOrderlinenumber();
    	em.persist(e);
    	}
}
