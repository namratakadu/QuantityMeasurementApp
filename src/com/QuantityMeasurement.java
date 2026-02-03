package com;

public class QuantityMeasurement {

	public static class Feet {
		private final double feetValue;

		public Feet(double feetValue) {
			this.feetValue = feetValue;
		}

		@Override
		public boolean equals(Objects obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			Feet feet = (Feet) obj;
			return Double.compare(feet.feetValue, feetValue) == 0;
		}

		@Override
		public int hashcode() {
			return Objects.hash(feetValue);
		}
	}

	public static void main(String[] args) {
	Feet feet1 = new Feet(0.0);
	Feet feet2 = new Feet(0.0);
	Feet feet3 = new Feet(1.0);
	
	System.out.println(feet1.equals(feet2));
	System.out.println(feet1.equals(feet3));
	//System.out.println(feet1.equals(null));
	}

}
