package model.services;

public class BrazilTaxService {
	
	public double tax(double amount) { //colocamos o double em minisculo porque se sabe que no parametro sempre ter� o double amount
		if(amount <= 100.0) {
			return amount * 0.2;
		}else {
			return amount * 0.15;
		}
	}

}
