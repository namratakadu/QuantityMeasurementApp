package com.quantity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.quantity.Length.LengthUnit;
import com.quantity.QuantityMeasurementApp.Feet;
import com.quantity.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {

	@Test
	void testEquality_SameValue() {
		Feet oneFootA = new Feet(1.0);
		Feet oneFootB = new Feet(1.0);

		assertEquals(oneFootA, oneFootB, "1.0 ft should equal 1.0 ft");
		assertTrue(oneFootA.equals(oneFootB), "equals() must return true for equal values");
		assertEquals(oneFootA.hashCode(), oneFootB.hashCode(), "Equal objects must have equal hashCodes");
	}

	@Test
	void testEquality_DifferentValue1() {
		Feet oneFoot = new Feet(1.0);
		Feet twoFeet = new Feet(2.0);

		assertNotEquals(oneFoot, twoFeet, "1.0 ft should not equal 2.0 ft");
		assertFalse(oneFoot.equals(twoFeet), "equals() must return false for different values");
	}

	@Test
	void testEquality_NullComparison1() {
		Feet oneFoot = new Feet(1.0);

		assertFalse(oneFoot.equals(null), "Any object must not equal null");
	}

	@Test
	void testEquality_NonNumericInput1() {
		Feet oneFoot = new Feet(1.0);

		assertFalse(oneFoot.equals("1.0"), "equals() must return false when comparing to a non-Quantity type");
		assertFalse(oneFoot.equals(new Object()), "equals() must return false for unrelated types");
	}

	@Test
	void testEquality_SameReference1() {
		Feet oneFoot = new Feet(1.0);

		assertEquals(oneFoot, oneFoot, "An object must equal itself (reflexive property)");
	}

	@Test
	void testEquality_SameValue1() {
		Inches inch1 = new Inches(1.0);
		Inches inch2 = new Inches(1.0);
		assertEquals(inch1, inch2, "Inches with same value must be equal");
		assertEquals(inch1.hashCode(), inch2.hashCode(), "Equal objects must have same hashCode");
	}

	// --- Different numeric values ---
	@Test
	void testEquality_DifferentValue() {
		Inches one = new Inches(1.0);
		Inches two = new Inches(2.0);
		assertNotEquals(one, two, "Different inch values must not be equal");
	}

	// --- Null safety ---
	@Test
	void testEquality_NullComparison() {
		Inches one = new Inches(1.0);
		assertNotEquals(one, null, "Any object compared to null must be not equal");
	}

	// --- Same reference (reflexive) ---
	@Test
	void testEquality_SameReference() {
		Inches one = new Inches(1.0);
		assertEquals(one, one, "Object must be equal to itself (reflexive)");
	}

	@Test
	void testEquality_NonNumericInput() {
		assertThrows(IllegalArgumentException.class, () -> new Length(Double.NaN, Length.LengthUnit.INCH));
		assertThrows(IllegalArgumentException.class,
				() -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET));
	}

	@Test
	void testEquality_FeetToFeet_SameValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(1.0, Length.LengthUnit.FEET);
		assertEquals(l1, l2);
	}

	@Test
	void testEquality_InchToInch_SameValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.INCH);
		Length l2 = new Length(1.0, Length.LengthUnit.INCH);
		assertEquals(l1, l2);
	}

	@Test
	void testEquality_FeetToInch_EquivalentValue() {
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		Length inches = new Length(12.0, Length.LengthUnit.INCH);
		assertEquals(feet, inches);
	}

	@Test
	void testEquality_InchToFeet_EquivalentValue() {
		Length inches = new Length(12.0, Length.LengthUnit.INCH);
		Length feet = new Length(1.0, Length.LengthUnit.FEET);
		assertEquals(inches, feet);
	}

	@Test
	void testEquality_FeetToFeet_DifferentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.FEET);
		Length l2 = new Length(2.0, Length.LengthUnit.FEET);
		assertNotEquals(l1, l2);
	}

	@Test
	void testEquality_InchToInch_DifferentValue() {
		Length l1 = new Length(1.0, Length.LengthUnit.INCH);
		Length l2 = new Length(2.0, Length.LengthUnit.INCH);
		assertNotEquals(l1, l2);
	}

	@Test
	void testEquality_InvalidUnit() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Length(Double.NaN, Length.LengthUnit.FEET);
		});
	}

	@Test
	void testEquality_NullUnit() {
		assertThrows(NullPointerException.class, () -> new Length(1.0, null));
	}

	@Test
	void testEquality_SameReference_Feet_Length() {
		Length l = new Length(1.0, Length.LengthUnit.FEET);
		assertEquals(l, l); // reflexive property
	}

	@Test
	void testEquality_NullComparison_Feet_Length() {
		Length l = new Length(1.0, Length.LengthUnit.FEET);
		assertNotEquals(l, null);
	}

	// 1. testEquality_YardToYard_SameValue()
	@Test
	void testEquality_YardToYard_SameValue() {
		Length a = new Length(1.0, LengthUnit.YARD);
		Length b = new Length(1.0, LengthUnit.YARD);
		assertEquals(a, b);
	}

	// 2. testEquality_YardToYard_DifferentValue()
	@Test
	void testEquality_YardToYard_DifferentValue() {
		Length a = new Length(1.0, LengthUnit.YARD);
		Length b = new Length(2.0, LengthUnit.YARD);
		assertNotEquals(a, b);
	}

	// 3. testEquality_YardToFeet_EquivalentValue()
	@Test
	void testEquality_YardToFeet_EquivalentValue() {
		Length a = new Length(1.0, LengthUnit.YARD);
		Length b = new Length(3.0, LengthUnit.FEET);
		assertEquals(a, b);
	}

	// 4. testEquality_FeetToYard_EquivalentValue()
	@Test
	void testEquality_FeetToYard_EquivalentValue() {
		Length a = new Length(3.0, LengthUnit.FEET);
		Length b = new Length(1.0, LengthUnit.YARD);
		assertEquals(a, b);
	}

	// 5. testEquality_YardToInches_EquivalentValue()
	@Test
	void testEquality_YardToInches_EquivalentValue() {
		Length a = new Length(1.0, LengthUnit.YARD);
		Length b = new Length(36.0, LengthUnit.INCH);
		assertEquals(a, b);
	}

	// 6. testEquality_InchesToYard_EquivalentValue()
	@Test
	void testEquality_InchesToYard_EquivalentValue() {
		Length a = new Length(36.0, LengthUnit.INCH);
		Length b = new Length(1.0, LengthUnit.YARD);
		assertEquals(a, b);
	}

	// 7. testEquality_YardToFeet_NonEquivalentValue()
	@Test
	void testEquality_YardToFeet_NonEquivalentValue() {
		Length a = new Length(1.0, LengthUnit.YARD);
		Length b = new Length(2.0, LengthUnit.FEET);
		assertNotEquals(a, b);
	}

	// 8. testEquality_CentimetersToInches_EquivalentValue()
	@Test
	void testEquality_CentimetersToInches_EquivalentValue() {
		Length a = new Length(1.0, LengthUnit.FEET);
		Length b = new Length(12.0, LengthUnit.INCH);
		assertEquals(a, b);
	}

	// 9. testEquality_CentimetersToFeet_NonEquivalentValue()
	@Test
	void testEquality_CentimetersToFeet_NonEquivalentValue() {
		Length a = new Length(1.0, LengthUnit.FEET);
		Length b = new Length(5.0, LengthUnit.FEET);
		assertNotEquals(a, b);
	}

	// 10. testEquality_MultiUnit_TransitiveProperty()
	@Test
	void testEquality_MultiUnit_TransitiveProperty() {
		Length yard = new Length(1.0, LengthUnit.YARD);
		Length feet = new Length(3.0, LengthUnit.FEET);
		Length inch = new Length(36.0, LengthUnit.INCH);

		assertEquals(yard, feet);
		assertEquals(feet, inch);
		assertEquals(yard, inch);
	}

	// 11. testEquality_YardWithNullUnit()
	@Test
	void testEquality_YardWithNullUnit() {
		// Constructor should reject null unit
		assertThrows(NullPointerException.class, () -> new Length(1.0, null));
	}

	// 12. testEquality_YardSameReference()
	@Test
	void testEquality_YardSameReference() {
		Length a = new Length(2.0, LengthUnit.YARD);
		assertEquals(a, a); // reflexive
	}

	// 13. testEquality_YardNullComparison()
	@Test
	void testEquality_YardNullComparison() {
		Length a = new Length(2.0, LengthUnit.YARD);
		assertNotEquals(a, null);
	}

	// 14. testEquality_CentimetersWithNullUnit()
	@Test
	void testEquality_CentimetersWithNullUnit() {
		assertThrows(NullPointerException.class, () -> new Length(10.0, null));
	}

	// 15. testEquality_CentimetersSameReference()
	@Test
	void testEquality_CentimetersSameReference() {
		Length a = new Length(10.0, LengthUnit.FEET);
		assertEquals(a, a);
	}

	// 16. testEquality_CentimetersNullComparison()
	@Test
	void testEquality_CentimetersNullComparison() {
		Length a = new Length(10.0, LengthUnit.FEET);
		assertNotEquals(a, null);
	}

	// 17. testEquality_AllUnits_ComplexScenario()
	@Test
	void testEquality_AllUnits_ComplexScenario() {
		// Example: 2.0 YARDS == 6.0 FEET == 72.0 INCHES
		Length twoYards = new Length(2.0, LengthUnit.YARD);
		Length sixFeet = new Length(6.0, LengthUnit.FEET);
		Length seventyTwoInches = new Length(72.0, LengthUnit.INCH);

		assertEquals(twoYards, sixFeet);
		assertEquals(sixFeet, seventyTwoInches);
		assertEquals(twoYards, seventyTwoInches);
	}

	private static final double EPS = 1e-6;
	
	private static double convert(double value, Length.LengthUnit source, Length.LengthUnit target) {
		return new Length(value, source).convertTo(target).getValue();
	}

	// 1. testConversion_FeetToInches()
	@Test
	void testConversion_FeetToInches() {
		double out = convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCH);
		assertEquals(12.0, out, EPS);
	}

	// 2. testConversion_InchesToFeet()
	@Test
	void testConversion_InchesToFeet() {
		double out = convert(24.0, Length.LengthUnit.INCH, Length.LengthUnit.FEET);
		assertEquals(2.0, out, EPS);
	}

	// 3. testConversion_YardsToInches()
	@Test
	void testConversion_YardsToInches() {
		double out = convert(1.0, Length.LengthUnit.YARD, Length.LengthUnit.INCH);
		assertEquals(36.0, out, EPS);
	}

	// 4. testConversion_InchesToYards()
	@Test
	void testConversion_InchesToYards() {
		double out = convert(72.0, Length.LengthUnit.INCH, Length.LengthUnit.YARD);
		assertEquals(2.0, out, EPS);
	}

	// 5. testConversion_CentimetersToInches()
	@Test
	void testConversion_CentimetersToInches() {
		double out = convert(2.54, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.INCH);
		assertEquals(1.0, out, 1e-9);
	}

	// 6. testConversion_FeetToYard()
	@Test
	void testConversion_FeetToYard() {
		double out = convert(6.0, Length.LengthUnit.FEET, Length.LengthUnit.YARD);
		assertEquals(2.0, out, EPS);
	}

	// 7. testConversion_RoundTrip_PreservesValue()
	@Test
	void testConversion_RoundTrip_PreservesValue() {
		double v = 13.75;
		Length.LengthUnit A = Length.LengthUnit.FEET;
		Length.LengthUnit B = Length.LengthUnit.CENTIMETERS;

		Length start = new Length(v, A);
		Length toB = start.convertTo(B);
		Length backToA = toB.convertTo(A);

		assertEquals(v, backToA.getValue(), 1e-9);
	}

	// 8. testConversion_ZeroValue()
	@Test
	void testConversion_ZeroValue() {
		double out = convert(0.0, Length.LengthUnit.FEET, Length.LengthUnit.INCH);
		assertEquals(0.0, out, EPS);
	}

	// 9. testConversion_NegativeValue()
	@Test
	void testConversion_NegativeValue() {
		double out = convert(-1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCH);
		assertEquals(-12.0, out, EPS);
	}

	// 10. testConversion_InvalidUnit_Throws()
	@Test
	void testConversion_InvalidUnit_Throws() {
		Length l = new Length(1.0, Length.LengthUnit.FEET);
		assertThrows(IllegalArgumentException.class, () -> l.convertTo(null));
	}

	// 11. testConversion_NaNOrInfinite_Throws()
	@Test
	void testConversion_NaNOrInfinite_Throws() {
		assertThrows(IllegalArgumentException.class, () -> new Length(Double.NaN, Length.LengthUnit.FEET));
		assertThrows(IllegalArgumentException.class,
				() -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET));
		assertThrows(IllegalArgumentException.class,
				() -> new Length(Double.NEGATIVE_INFINITY, Length.LengthUnit.FEET));
	}

	// 12. testConversion_PrecisionTolerance()
	@Test
	void testConversion_PrecisionTolerance() {
		// Example: 30 cm -> feet ≈ 0.9842519685
		double out = convert(30.0, Length.LengthUnit.CENTIMETERS, Length.LengthUnit.FEET);
		assertEquals(0.9842519685, out, 1e-9);
	}

}
