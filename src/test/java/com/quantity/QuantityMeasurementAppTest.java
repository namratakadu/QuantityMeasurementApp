package com.quantity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import com.quantity.QuantityMeasurement.Feet;
import com.quantity.QuantityMeasurement.Inches;

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

	// --- Type safety (different class) ---
	@Test
	void testEquality_TypeSafety() {
		Inches one = new Inches(1.0);
		Object other = new Object();
		assertNotEquals(one, other, "Different types must not be equal");
	}

	// --- Optional: Guard invalid numeric inputs (only if your domain forbids them)
	// ---
	@Test
	void testEquality_NonNumericInput() {
		// If your Inches constructor rejects NaN/Infinity, then expect exception.
		// Otherwise, remove or adapt this test.
		assertThrows(IllegalArgumentException.class, () -> new Inches(Double.NaN));
		assertThrows(IllegalArgumentException.class, () -> new Inches(Double.POSITIVE_INFINITY));
		assertThrows(IllegalArgumentException.class, () -> new Inches(Double.NEGATIVE_INFINITY));
	}

	// --- Optional: Floating-point precision tolerance ---
	@Test
	void testEquality_FloatingPointPrecision() {
		Inches base = new Inches(1.0);
		Inches nearly = new Inches(1.0 + 1e-15);
		// If your equals uses Double.compare, these should be equal for
		// representationally equal values.
		// If you implement tolerance, adapt equals accordingly and expect equality
		// within epsilon.
		assertEquals(base, nearly, "Values equal within floating precision should be equal");
	}

}
