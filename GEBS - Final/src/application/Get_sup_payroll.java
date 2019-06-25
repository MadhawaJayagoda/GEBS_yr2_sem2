package application;

public class Get_sup_payroll{
	
	private static Payroll sup_payroll = new Payroll();
	
	private Get_sup_payroll() {}
	
	public static Payroll getInstance() {
		return sup_payroll; 
	}
	
	//used for testing purpose
	public static void print() {
		System.out.println("id: " + sup_payroll.getId() );
		System.out.println("Item: " + sup_payroll.getProduct() );
		System.out.println("company: " + sup_payroll.getCompany() );
		System.out.println("Supplied date : " + sup_payroll.getSupplied_date());
		System.out.println("Payment date : " + sup_payroll.getPayment_date() );
		System.out.println("Payment status : " + sup_payroll.getPayment_status());
		System.out.println("Unit Price: " + sup_payroll.getUnitprice());
		System.out.println("Quantity : " + sup_payroll.getQuantity());
		System.out.println("Total Payment : " + sup_payroll.getTotal_payment());
	}
}
