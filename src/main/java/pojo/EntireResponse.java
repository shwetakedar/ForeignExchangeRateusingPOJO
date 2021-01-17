package pojo;

public class EntireResponse {

	String base;
	String date;
	Rates rates;
	
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Rates getRates() {
		return rates;
	}
	public void setRates(Rates rates) {
		this.rates = rates;
	}
	
//	@Override
//	public String toString() {
//		return "EntireResponse [base=" + base + ", date=" + date + ", rates=" + rates + "]";
//	}
}
