package au.com.translatorss.bean.dto;

public class Product {

	private String id;
	private String name;
	private Integer quantity;
	private Double price;
	
	public Product(String id, String name,  Double price, Integer quantity) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
}
