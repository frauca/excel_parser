package frauca.command.times

class Month {

	int year;
	int month;

	def String toString() {
		return "${year}_${month}"
	};
	
	public boolean equals(java.lang.Object other){
		if (other == null) return false
		if (this.is(other)) return true
		if (!other.canEqual(this)) return false
		if (year != other.year) return false
		if (month != other.month) return false
		return true
	}

	public boolean canEqual(java.lang.Object other) {
		return other instanceof Month
	}
}
