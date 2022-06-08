package d_method_discovery_and_invocation.exercise6_implement_get_method_checker;

import java.util.Date;

//This class is missing getter for expiration date
public class ProductDataClass_MissingGetterMethod {
	private double price;
	private String name;
	private Date expirationDate;
	
	public double getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	
	
}
