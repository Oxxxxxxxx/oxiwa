package domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the orderdetails database table.
 * 
 */
@Entity
@Table(name="orderdetails", schema="classicmodels")
@NamedQuery(name="Orderdetail.findAll", query="SELECT o FROM Orderdetail o WHERE o.order = :ordernumber")
@NamedQuery(name = "Orderdetail.findbyId", query = "SELECT o FROM Orderdetail o WHERE o.order.ordernumber = :ordernumber AND o.id.productcode = :productcode")
public class Orderdetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderdetailPK id;

	private Integer orderlinenumber;

	private BigDecimal priceeach;

	private Integer quantityordered;

	//bi-directional many-to-one association to OrdersEntity
	@ManyToOne
	@JoinColumn(name="ordernumber",insertable=false, updatable=false)
	private OrdersEntity order;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="productcode",insertable=false, updatable=false)
	private Product product;

	public Orderdetail() {
	}

	public OrderdetailPK getId() {
		return this.id;
	}

	public void setId(OrderdetailPK id) {
		this.id = id;
	}

	public Integer getOrderlinenumber() {
		return this.orderlinenumber;
	}

	public void setOrderlinenumber(Integer orderlinenumber) {
		this.orderlinenumber = orderlinenumber;
	}

	public BigDecimal getPriceeach() {
		return this.priceeach;
	}

	public void setPriceeach(BigDecimal priceeach) {
		this.priceeach = priceeach;
	}

	public Integer getQuantityordered() {
		return this.quantityordered;
	}

	public void setQuantityordered(Integer quantityordered) {
		this.quantityordered = quantityordered;
	}

	public OrdersEntity getOrder() {
		return this.order;
	}

	public void setOrder(OrdersEntity order) {
		this.order = order;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}