package sessionbean;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.OrdersEntity;

/**
 * Session Bean implementation class OrdersEntitySessionbean
 */
@Stateless
@LocalBean
public class OrdersEntitySessionbean implements OrdersEntitySessionbeanLocal {
	
	@PersistenceContext(unitName = "Assigment")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public OrdersEntitySessionbean() {
        // TODO Auto-generated constructor stub
    }
    
    public List<OrdersEntity> getAllOrders() throws EJBException {
    	//Write some codes here…
    	return em.createNamedQuery("OrdersEntity.findAll").getResultList();
    	}
    
    public List<OrdersEntity> readStaff(int currentPage, int recordsPerPage) throws EJBException {
    	//Write some codes here…
    	int start = currentPage * recordsPerPage - recordsPerPage;
    	Query q = em.createNativeQuery("SELECT * FROM classicmodels.orders ORDER BY ordernumber OFFSET ? LIMIT ?",
    	OrdersEntity.class);
    	q.setParameter(1, start);
    	q.setParameter(2, recordsPerPage);
    	List<OrdersEntity> results = q.getResultList();
    	return results;
    	}
    
    public int getNumberOfRows() throws EJBException {
    	//Write some codes here…
    	Query q = em.createNativeQuery("SELECT COUNT(*) FROM classicmodels.orders");
    	BigInteger results = (BigInteger) q.getSingleResult();
    	int i = results.intValue();
    	return i;
    	}
    
    public OrdersEntity findOrder(String id) throws EJBException {
    	//Write some codes here…
    	Query q = em.createNamedQuery("OrdersEntity.findbyId");
    	q.setParameter("ordernumber", Integer.valueOf(id));
    	return (OrdersEntity) q.getSingleResult();
    	}
    
    public void updateOrder(String[] s) throws EJBException {
    	//Write some codes here…
    	//Date dob = null;
    	//Date hd = null;
    	OrdersEntity e = findOrder(s[0]);
    	try {
    	//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
    	//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
    	} catch (Exception ex) {
    	}
    	//java.sql.Date DOB = new java.sql.Date(dob.getTime());
    	//java.sql.Date HD = new java.sql.Date(hd.getTime());
    	e.setOrderdate(s[1]);
    	e.setRequireddate(s[2]);
    	e.setShippeddate(s[3]);
    	e.setStatus(s[4]);
    	e.setComments(s[5]);
    	em.merge(e);
    	}
    
    public void deleteOrder(String id) throws EJBException {
    	//Write some codes here…
    	OrdersEntity e = findOrder(id);
    	em.remove(e);
    	}
    
    
    public void addOrder(String[] s) throws EJBException {
    	//Write some codes here…
    	//Date dob = null;
    	//Date hd = null;
    	//System.out.println("JJJJJJJJJJJJJJJ" + ordernumber);
    	int ordernumber = Integer.parseInt(s[0]);
    	int customernumber = Integer.parseInt(s[6]);
    	
    	try {
    	//dob = new SimpleDateFormat("yyyy-MM-dd").parse(s[4]);
    	//hd = new SimpleDateFormat("yyyy-MM-dd").parse(s[5]);
    	} catch (Exception ex) {
    	}
    	OrdersEntity e = new OrdersEntity();
    	//java.sql.Date DOB = new java.sql.Date(dob.getTime());
    	//java.sql.Date HD = new java.sql.Date(hd.getTime());
    	 
    	e.setOrdernumber(ordernumber);
    	e.setOrderdate(s[1]);
    	e.setRequireddate(s[2]);
    	e.setShippeddate(s[3]);
    	e.setStatus(s[4]);
    	e.setComments(s[5]);
    	e.setCustomernumber(customernumber);
    	em.persist(e);
    	}
}
