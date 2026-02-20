package com.quantity;

import java.util.Objects;

public class QuantityMeasurement {

	public static class Feet {
		private final double feetValue;

		public Feet(double feetValue) {
			this.feetValue = feetValue;
		}

		double toInches() {

			return feetValue * 12;
		}

		@Override
		public boolean equals(Object obj) {
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
		public int hashCode() {
			return Objects.hash(feetValue);
		}

	}

	public static class Inches {

		private final double inchValue;

		public Inches(double inchValue) {
			this.inchValue = inchValue;
		}

		double toInches() {
			return inchValue;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (obj instanceof Inches) {
				Inches inch = (Inches) obj;
				return Double.compare(this.inchValue, inch.inchValue) == 0;
			}
			if (obj instanceof Feet) {
				Feet feet = (Feet) obj;
				return Double.compare(this.toInches(), feet.toInches()) == 0;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return Double.valueOf(inchValue).hashCode();
		}

	}

	public static void demonstrateFeetEquality() {
		Feet feet1 = new Feet(0.0);
		Feet feet2 = new Feet(0.0);
		Feet feet3 = new Feet(1.0);

		System.out.println(feet1.equals(feet2));
		System.out.println(feet1.equals(feet3));
		System.out.println(feet1.equals(null));
	}

	public static void demonstrateInchesEquality() {

		System.out.println(new Inches(2).equals(new Inches(2)));
		System.out.println(new Inches(2).equals(new Inches(3)));
		System.out.println(new Feet(1).equals(new Inches(12)));
		System.out.println(new Inches(12).equals(new Feet(1)));

	}

	public static void main(String[] args) {

		demonstrateFeetEquality();
		demonstrateInchesEquality();
	}

}
