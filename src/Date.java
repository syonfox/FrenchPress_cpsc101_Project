/**
* @author Jiangtao Qiu
* @author Rodrigo Nicoletti
* @author Kier Lindsay
**/

public class Date {
	int day;
	int month;
	int year;
	
	public Date(){
		setDay(1);
		setMonth(1);
		setYear(1000);
	}
	
	public Date(int d, int m, int y){
		setDay(d);
		setMonth(m);
		setYear(y);
	}
	
	public boolean equals(Date other){
		if(getDay() == other.getDay() && getMonth() == other.getMonth() && getYear() == other.getYear())
			return true;
		return false;
	}
	
	public String toString(){
		return getYear() + "-" + getMonth() + "-" + getDay();
	}
	
	public int getYear(){
		return year;
	}

	public int getMonth(){
		return month;
	}
	
	public int getDay(){
		return day;
	}
	
   /**
	 * Sets the year
	 * @param year
	 */
	private void setYear(int y) {
		this.year = y;
	}

   /**
	 * Sets the month
	 * @param month
	 */
	private void setMonth(int m) {
		this.month = m;
	}

   /**
	 * Sets the day
	 * @param day
	 */
	private void setDay(int d) {
		this.day = d;
	}
}
