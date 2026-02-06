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
		// Optional: hashCode consistency for equal objects
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
		Length feet = new Length(1.0, Length.LengthUnit.FEET); // 12 inches
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
			new Length(Double.NaN, Length.LengthUnit.FEET); // or some unsupported unit
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

}
