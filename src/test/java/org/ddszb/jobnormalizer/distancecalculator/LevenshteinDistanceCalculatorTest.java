package org.ddszb.jobnormalizer.distancecalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LevenshteinDistanceCalculatorTest {

     @Test
    void equalStringsShouldHaveNoDistance() {
        var calculator = new LevenshteinDistanceCalculator();
        assertEquals(0, calculator.calculateDistance("Source String", "Source String"));
    }

    @Test
    void equalStringsDifferentCaseWillHaveDifferentDistances() {
        var calculator = new LevenshteinDistanceCalculator();
        assertNotEquals(0, calculator.calculateDistance("source string", "SOURCE STRING"));
    }

    @Test
    void tenCharacterDifferentStringsShouldHaveDifferenceOfTen() {
        var calculator = new LevenshteinDistanceCalculator();
        assertEquals(10.0, calculator.calculateDistance("aaaaaaaaaa", "zzzzzzzzzz"));
    }

        @Test
    void tenCharacterDifferentStringsOneMatchShouldHaveDifferenceOfNine() {
        var calculator = new LevenshteinDistanceCalculator();
        assertEquals(9.0, calculator.calculateDistance("xaaaaaaaaa", "xzzzzzzzzz"));
    }

}