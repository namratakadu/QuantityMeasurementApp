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

	// 1) testAddition_SameUnit_FeetPlusFeet()
	@Test
	void testAddition_SameUnit_FeetPlusFeet() {
		Length a = new Length(1.0, Length.LengthUnit.FEET);
		Length b = new Length(2.0, Length.LengthUnit.FEET);

		Length sum = a.add(b);

		assertEquals(3.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	// 2) testAddition_SameUnit_InchPlusInch()
	@Test
	void testAddition_SameUnit_InchPlusInch() {
		Length a = new Length(6.0, Length.LengthUnit.INCH);
		Length b = new Length(6.0, Length.LengthUnit.INCH);

		Length sum = a.add(b);

		assertEquals(12.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.INCH, sum.getUnit());
	}

	// 3) testAddition_CrossUnit_FeetPlusInches()
	@Test
	void testAddition_CrossUnit_FeetPlusInches() {
		Length a = new Length(1.0, Length.LengthUnit.FEET); // 12 in
		Length b = new Length(12.0, Length.LengthUnit.INCH); // 12 in

		Length sum = a.add(b); // (12 + 12) in = 24 in = 2 ft

		assertEquals(2.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	// 4) testAddition_CrossUnit_InchPlusFeet()
	@Test
	void testAddition_CrossUnit_InchPlusFeet() {
		Length a = new Length(12.0, Length.LengthUnit.INCH);
		Length b = new Length(1.0, Length.LengthUnit.FEET);

		Length sum = a.add(b); // 12 in + 12 in = 24 in

		assertEquals(24.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.INCH, sum.getUnit());
	}

	// 5) testAddition_CrossUnit_YardPlusFeet()
	@Test
	void testAddition_CrossUnit_YardPlusFeet() {
		Length a = new Length(1.0, Length.LengthUnit.YARD); // 36 in
		Length b = new Length(3.0, Length.LengthUnit.FEET); // 36 in

		Length sum = a.add(b); // 72 in = 2 yd (result in yards)

		assertEquals(2.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.YARD, sum.getUnit());
	}

	// 6) testAddition_CrossUnit_CentimeterPlusInch()
	@Test
	void testAddition_CrossUnit_CentimeterPlusInch() {
		Length a = new Length(2.54, Length.LengthUnit.CENTIMETERS); // 1.0 in
		Length b = new Length(1.0, Length.LengthUnit.INCH); // 1.0 in

		Length sum = a.add(b); // 1 in + 1 in = 2 in; in cm ≈ 5.08 (depending on rounding rule)

		// If your add(...) rounds to 2 decimals, 5.08 is exact; otherwise allow tiny
		// epsilon:
		assertEquals(5.08, sum.getValue(), 1e-2);
		assertEquals(Length.LengthUnit.CENTIMETERS, sum.getUnit());
	}

	// 7) testAddition_Commutativity()
	@Test
	void testAddition_Commutativity() {
		Length a = new Length(1.0, Length.LengthUnit.FEET); // 12 in
		Length b = new Length(12.0, Length.LengthUnit.INCH); // 12 in

		Length sum1 = a.add(b); // result in FEET
		Length sum2 = b.add(a); // result in INCH

		// Compare in base (inches) for commutativity
		assertEquals(sum1.toBase(), sum2.toBase(), 1e-9);
	}

	// 8) testAddition_WithZero()
	@Test
	void testAddition_WithZero() {
		Length a = new Length(5.0, Length.LengthUnit.FEET);
		Length zero = new Length(0.0, Length.LengthUnit.INCH);

		Length sum = a.add(zero); // 5 ft + 0 in = 5 ft

		assertEquals(5.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	// 9) testAddition_NegativeValues()
	@Test
	void testAddition_NegativeValues() {
		Length a = new Length(5.0, Length.LengthUnit.FEET);
		Length b = new Length(-2.0, Length.LengthUnit.FEET);

		Length sum = a.add(b);

		assertEquals(3.0, sum.getValue(), EPS);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	// 10) testAddition_NullSecondOperand()
	@Test
	void testAddition_NullSecondOperand() {
		Length a = new Length(1.0, Length.LengthUnit.FEET);
		assertThrows(IllegalArgumentException.class, () -> a.add(null));
	}

	// 11) testAddition_LargeValues()
	@Test
	void testAddition_LargeValues() {
		Length a = new Length(1_000_000.0, Length.LengthUnit.FEET);
		Length b = new Length(1_000_000.0, Length.LengthUnit.FEET);

		Length sum = a.add(b);

		assertEquals(2_000_000.0, sum.getValue(), 1e-3);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	// 12) testAddition_SmallValues()
	@Test
	void testAddition_SmallValues() {
		Length a = new Length(0.001, Length.LengthUnit.FEET);
		Length b = new Length(0.002, Length.LengthUnit.FEET);

		Length sum = a.add(b);

		assertEquals(0.003, sum.getValue(), 1e-9);
		assertEquals(Length.LengthUnit.FEET, sum.getUnit());
	}

	private static Length L(double value, LengthUnit unit) {
		return new Length(value, unit);
	}

	private static void assertLength(Length actual, double expectedValue, LengthUnit expectedUnit) {
		assertNotNull(actual, "Length result must not be null");
		assertEquals(expectedUnit, actual.getUnit(), "Result unit mismatch");
		assertEquals(expectedValue, actual.getValue(), EPS, "Result value differs beyond tolerance");
	}

	// 1. Explicit target: FEET
	@Test
	void testAddition_ExplicitTargetUnit_Feet() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.FEET), L(12.0, LengthUnit.INCH),
				LengthUnit.FEET);
		assertLength(res, 2.0, LengthUnit.FEET);
	}

	// 2. Explicit target: INCHES
	@Test
	void testAddition_ExplicitTargetUnit_Inches() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.FEET), L(12.0, LengthUnit.INCH),
				LengthUnit.INCH);
		// 1 ft = 12 in; 12 in + 12 in = 24 in
		assertLength(res, 24.0, LengthUnit.INCH);
	}

	// 3. Explicit target: YARDS
	@Test
	void testAddition_ExplicitTargetUnit_Yards() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.FEET), L(12.0, LengthUnit.INCH),
				LengthUnit.YARD);
		// total = 24 in = 2 ft = 2/3 yd ≈ 0.6667
		assertLength(res, 2.0 / 3.0, LengthUnit.YARD);
	}

	// 4. Explicit target: CENTIMETERS
	@Test
	void testAddition_ExplicitTargetUnit_Centimeters() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.INCH), L(1.0, LengthUnit.INCH),
				LengthUnit.CENTIMETERS);
		// 2 inches = 5.08 cm
		assertLength(res, 5.08, LengthUnit.CENTIMETERS);
	}

	// 5. Target unit same as first operand unit
	@Test
	void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(2.0, LengthUnit.YARD), // 2 yd
				L(3.0, LengthUnit.FEET), // 1 yd
				LengthUnit.YARD);
		assertLength(res, 3.0, LengthUnit.YARD);
	}

	// 6. Target unit same as second operand unit
	@Test
	void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(2.0, LengthUnit.YARD), // 6 ft
				L(3.0, LengthUnit.FEET), // 3 ft
				LengthUnit.FEET);
		assertLength(res, 9.0, LengthUnit.FEET);
	}

	// 7. Commutativity with explicit target unit
	@Test
	void testAddition_ExplicitTargetUnit_Commutativity() {
		Length aThenB = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.FEET),
				L(12.0, LengthUnit.INCH), LengthUnit.YARD);
		Length bThenA = QuantityMeasurementApp.demonstrateLengthAddition(L(12.0, LengthUnit.INCH),
				L(1.0, LengthUnit.FEET), LengthUnit.YARD);

		assertEquals(LengthUnit.YARD, aThenB.getUnit());
		assertEquals(LengthUnit.YARD, bThenA.getUnit());
		assertEquals(aThenB.getValue(), bThenA.getValue(), EPS);
	}

	// 8. Zero operand
	@Test
	void testAddition_ExplicitTargetUnit_WithZero() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(5.0, LengthUnit.FEET), L(0.0, LengthUnit.INCH),
				LengthUnit.YARD);
		// 5 ft = 1.6667 yd
		assertLength(res, 5.0 / 3.0, LengthUnit.YARD);
	}

	// 9. Negative values
	@Test
	void testAddition_ExplicitTargetUnit_NegativeValues() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(5.0, LengthUnit.FEET), // 60 in
				L(-2.0, LengthUnit.FEET), // -24 in
				LengthUnit.INCH);
		// 36 inches
		assertLength(res, 36.0, LengthUnit.INCH);
	}

	// 10. Null target unit -> IllegalArgumentException
	@Test
	void testAddition_ExplicitTargetUnit_NullTargetUnit() {
		assertThrows(IllegalArgumentException.class, () -> QuantityMeasurementApp
				.demonstrateLengthAddition(L(1.0, LengthUnit.FEET), L(12.0, LengthUnit.INCH), null));
	}

	// 11. Large -> Small scale (result in INCHES)
	@Test
	void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(1000.0, LengthUnit.FEET),
				L(500.0, LengthUnit.FEET), LengthUnit.INCH);
		// 1500 ft = 1500 * 12 = 18000 inches
		assertLength(res, 18000.0, LengthUnit.INCH);
	}

	// 12. Small -> Large scale (result in YARDS)
	@Test
	void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
		Length res = QuantityMeasurementApp.demonstrateLengthAddition(L(12.0, LengthUnit.INCH),
				L(12.0, LengthUnit.INCH), LengthUnit.YARD);
		// 24 in = 2/3 yd
		assertLength(res, 2.0 / 3.0, LengthUnit.YARD);
	}

	// 13. All unit-pair combinations against multiple target units
	@Test
	void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
		double[] magnitudes = { 0.0, 1.0, 2.5, 12.0, 100.0 };

		for (LengthUnit u1 : LengthUnit.values()) {
			for (LengthUnit u2 : LengthUnit.values()) {
				for (double v1 : magnitudes) {
					for (double v2 : magnitudes) {
						for (LengthUnit target : LengthUnit.values()) {
							Length l1 = L(v1, u1);
							Length l2 = L(v2, u2);

							// Expected via base (inches)
							double sumInInches = v1 * u1.getConversionFactor() + v2 * u2.getConversionFactor();
							double expectedInTarget = sumInInches / target.getConversionFactor();

							Length res = QuantityMeasurementApp.demonstrateLengthAddition(l1, l2, target);
							assertEquals(target, res.getUnit(), "Target unit must be preserved");
							assertEquals(expectedInTarget, res.getValue(), 1e-6,
									String.format("Mismatch for %s + %s in %s", u1, u2, target));
						}
					}
				}
			}
		}
	}

	// 14. Precision tolerance checks (epsilon-based)
	@Test
	void testAddition_ExplicitTargetUnit_PrecisionTolerance() {

		Length a = QuantityMeasurementApp.demonstrateLengthAddition(L(1.0, LengthUnit.YARD), // 36 in
				L(5.0, LengthUnit.CENTIMETERS), // 1.9685 in
				LengthUnit.INCH);
		Length b = QuantityMeasurementApp.demonstrateLengthAddition(a, L(0.5, LengthUnit.FEET), // 6 in
				LengthUnit.FEET);

		// Expected: ((36 + 1.9685) + 6) inches = 43.9685 in -> feet = /12
		double expectedFeet = (36.0 + 5.0 * LengthUnit.CENTIMETERS.getConversionFactor() + 6.0) / 12.0;

		assertEquals(LengthUnit.FEET, b.getUnit());
		assertEquals(expectedFeet, b.getValue(), 1e-6);
	}

}
