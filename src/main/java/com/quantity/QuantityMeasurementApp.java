package com.quantity;

import java.util.Objects;

public class QuantityMeasurementApp {

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

	public static void demonstrateFeetInchesComparison() {

		Length oneFoot = new Length(1, Length.LengthUnit.FEET);
		Length twelveInch = new Length(12, Length.LengthUnit.INCH);
		Length tenInch = new Length(10, Length.LengthUnit.INCH);
		Length twoFeet = new Length(2, Length.LengthUnit.FEET);

		System.out.println("1 FEET    -> " + oneFoot.toBase() + " INCH");
		System.out.println("12 INCH   -> " + twelveInch.toBase() + " INCH");
		System.out.println("10 INCH   -> " + tenInch.toBase() + " INCH");
		System.out.println("2 FEET    -> " + twoFeet.toBase() + " INCH");

		// --- Equality (boolean) ---
		System.out.println("\n-- Equality (boolean) --");
		System.out.println("1 FEET == 12 INCH ? " + oneFoot.compare(twelveInch)); // true
		System.out.println("1 FEET == 10 INCH ? " + oneFoot.compare(tenInch)); // false
		System.out.println("1 FEET equals 12 INCH ? " + oneFoot.equals(twelveInch)); // true
	}

	public static double convert(double value, Length.LengthUnit source, Length.LengthUnit target) {
		return new Length(value, source).convertTo(target).getValue();
	}

	public static void demonstrateLengthConversions() {

		double toFeet = convert(30, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.FEET);

		System.out.println("30 CM -> FEET: " + toFeet);

		double toInches = convert(1, Length.LengthUnit.YARD, Length.LengthUnit.INCH);
		System.out.println("1 YARD -> INCH: " + toInches);
	}

	public static void main(String[] args) {

		demonstrateFeetEquality();
		demonstrateInchesEquality();
		demonstrateFeetInchesComparison();
		demonstrateLengthConversions();
	}

}
