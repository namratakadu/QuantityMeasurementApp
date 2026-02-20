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

	//Added for UC6 addition of different lengths and result unit should in the form of 1st Length - start//
	public Length add(Length thatLength) {
		if (thatLength == null) {
			throw new IllegalArgumentException("thatLength should not be null");
		}

		double thisInches = this.toBase();
		double thatInches = thatLength.toBase();
		double sumInInches = thisInches + thatInches;

		double resultValue = convertFromBaseToTargetUnit(sumInInches, this.getUnit());
		return new Length(resultValue, this.getUnit());

	}
	//Added for UC6 addition of different lengths and result unit should in the form of 1st Length - end//
	//Added for UC7 addition of different lengths and result unit should in the form of given targetUnit form - start//
	public Length add(Length length, LengthUnit targetUnit) {
		if (length == null) {
			throw new IllegalArgumentException("length should not be null");
		}
		if (length.unit == null || targetUnit == null) {
			throw new IllegalArgumentException("units should not be null");
		}
		if (!Double.isFinite(this.value) || !Double.isFinite(length.value)) {
			throw new IllegalArgumentException("values should be finite numbers");
		}

		return addAndConvert(length, targetUnit);
	}
	//Added for UC7 addition of different lengths and result unit should in the form of given targetUnit form - end//
	//Added for UC7 addition of different lengths and result unit should in the form of given targetUnit form - start//
	private Length addAndConvert(Length length, LengthUnit targetUnit) {
		double thisInInches = this.value * this.unit.getConversionFactor();
		double otherInInches = length.value * length.unit.getConversionFactor();

		double sumInInches = thisInInches + otherInInches;

		double sumInTarget = convertFromBaseToTargetUnit(sumInInches, targetUnit);

		return new Length(sumInTarget, targetUnit);
	}
	//Added for UC7 addition of different lengths and result unit should in the form of given targetUnit form - end//
	
	//Added for UC7 addition of different lengths and result unit should in the form of 1st Length - start//
	private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
		if (targetUnit == null) {
			throw new IllegalArgumentException("targetUnit should not be null");
		}
		return lengthInInches / targetUnit.getConversionFactor();
	}
	//Added for UC7 addition of different lengths and result unit should in the form of 1st Length - end//
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

		System.out.println(new Length(30.0, LengthUnit.CENTIMETERS).convertTo(LengthUnit.FEET));

		Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
		Length twoInches = new Length(2.0, Length.LengthUnit.INCH);
		Length sumInFeet = oneFoot.add(twoInches);
		System.out.println(sumInFeet);


	}

}
