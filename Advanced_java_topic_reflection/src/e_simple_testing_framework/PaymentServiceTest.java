package e_simple_testing_framework;

public class PaymentServiceTest {
	
//	private PaymentService service;
	
	public static void beforeClass() {
		System.out.println("before class is executed!!!");
	}
	
	public void setupTest() {
		System.out.println("setup method is executed!!!");
	}
	
	public void testCreateCardPayment() {
		System.out.println("test created card method is executed!!!");
	}
	
	public void testWireTransfer() {
		System.out.println("test wire transfer method is executed!!!");
	}
	
	public static void afterClass() {
		System.out.println("after class method is executed!!!");
	}
}
