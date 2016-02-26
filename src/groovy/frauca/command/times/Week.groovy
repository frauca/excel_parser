package frauca.command.times

import groovy.transform.InheritConstructors;


@InheritConstructors
class Week {

	int year;
	int week;
	
	

	public Week(String sweek) {
		super();
		def (syear,swk)=sweek.tokenize( '_' );
		year=syear.toInteger();
		week=swk.toInteger();
	}

	Date getFirstDay(){
		def mydate = new GregorianCalendar(year, Calendar.JANUARY, 1, 0, 0, 0).time
		mydate[Calendar.WEEK_OF_YEAR]=week;
		mydate[Calendar.DAY_OF_WEEK]=Calendar.MONDAY;
		return mydate;
	}
	
	Date getLastDay(){
		return getFirstDay()+7;
	}
	
	def String toString() {
		return "${year}_${week}"
	};
	
	public boolean equals(java.lang.Object other){
		if (other == null) return false
		if (this.is(other)) return true
		if (!other.canEqual(this)) return false
		if (year != other.year) return false
		if (week != other.week) return false
		return true
	}

	public boolean canEqual(java.lang.Object other) {
		return other instanceof Week
	}
}
