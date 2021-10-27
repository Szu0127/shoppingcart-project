package tw.billy.model;

public class OrderDetail {

	private int orderdetailid;
	private Order orderNo;
	private int pid;
	private int uid;
	private int orderquentity;
	private String date;

	// private int subtotal;// 小計

	public OrderDetail() {

	}

	public int getOrderdetailid() {
		return orderdetailid;
	}

	public void setOrderdetailid(int orderdetailid) {
		this.orderdetailid = orderdetailid;
	}

	public Order getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Order orderNo) {
		this.orderNo = orderNo;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public int setUid(int uid) {
		return this.uid = uid;
	}

	public int getOrderquentity() {
		return orderquentity;
	}

	public void setOrderquentity(int orderquentity) {
		this.orderquentity = orderquentity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

//	public int getSubtotal() {
//		return subtotal;
//	}
//
//	public void setSubtotal(int subtotal) {
//		this.subtotal = subtotal;
//	}

}
