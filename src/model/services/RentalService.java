package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {
	private Double pricePerDay;
	private Double pricePerHour;
	
	private TaxService taxService;//Association
	
	public RentalService() {
		
	}

	public RentalService(Double pricePerDay, Double pricePerHour, TaxService taxService) {
		super();
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}
	
	public void processInvoice(CarRental carRental) {
		// Em JAVA ,a date é armazenada em milisegundos
		//getTime() => transforma em milisegundos
		long t1 = carRental.getStartDate().getTime();
		long t2 = carRental.getFinishDate().getTime();
		//1000 para receber em segundos. 60 para receber em minutos. E 60 para receber em horas.
		//casting pra double pra garantir as horas quebradas e arredondar conforme o enunciado
		double hours = (double)(t2 - t1) / 1000 / 60 / 60; 
		double basicPayment;
		if(hours <= 12.0) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		}else {
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}
		
		double tax = taxService.tax(basicPayment);
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

}
