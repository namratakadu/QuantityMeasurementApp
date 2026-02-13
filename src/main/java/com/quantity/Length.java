package com.quantity;

import java.math.RoundingMode;

public class Length {

	private double value;
	private LengthUnit unit;

	public enum LengthUnit {
		FEET(12.0), INCH(1.0), YARD(36.0), CENTIMETERS(0.3937007874);

		private final double conversionFactor;

		LengthUnit(double conversionFactor) {
			this.conversionFactor = conversionFactor;
		}

		public double getConversionFactor() {
			return conversionFactor;
		}

	}

	public Length(double value, LengthUnit unit) {
		if (unit == null) {
			throw new NullPointerException("Unit must not be null");
		}

		if (Double.isNaN(value) || Double.isInfinite(value)) {
			throw new IllegalArgumentException("Value must be finite number");
		}

		this.value = value;
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	// ---------- Convert Length to Base Unit (INCH) ----------
	public double toBase() {
		return unit.getConversionFactor() * value;
	}

	public boolean compare(Length thatLength) {
		if (thatLength == null)
			return false;
		final double EPSILON = 1e-6;
		return Math.abs(this.toBase() - thatLength.toBase()) < EPSILON;
	}

	public Length convertTo(LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("targetUnit should not be null");
		}
		if (this.unit == targetUnit) {
			return this;
		}

		double inInches = this.value * this.unit.getConversionFactor();

		double targetValue = inInches / targetUnit.getConversionFactor();

		return new Length(targetValue, targetUnit);
	}

	@Override
	public String toString() {
		return value + " " + unit.name().toLowerCase();
	}

	// --- Equality in base unit (INCH) ---
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Length))
			return false;
		Length other = (Length) obj;
		final double EPSILON = 1e-6;
		return Math.abs(this.toBase() - other.toBase()) < EPSILON;
	}

	@Override
	public int hashCode() {
		final double EPSILON = 1e-6;
		long bucket = Math.round(this.toBase() / EPSILON);
		return Long.hashCode(bucket);
	}

	public static void main(String[] args) {
		Length length1 = new Length(1.0, LengthUnit.FEET);
		Length length2 = new Length(12.0, LengthUnit.INCH);
		System.out.println("Are lenths equal? " + length1.equals(length2));

		Length length3 = new Length(1.0, LengthUnit.YARD);
		Length length4 = new Length(36.0, LengthUnit.INCH);
		System.out.println("Are lenths equal? " + length1.equals(length2));

		Length length5 = new Length(100.0, LengthUnit.CENTIMETERS);
		Length length6 = new Length(39.3701, LengthUnit.INCH);
		System.out.println("Are lenths equal? " + length1.equals(length2));

		System.out
				.println(new Length(30.0, LengthUnit.CENTIMETERS).convertTo(LengthUnit.FEET)); // 0.984
																														// feet

	}

}
