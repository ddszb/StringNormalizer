package org.ddszb.jobnormalizer.distancecalculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JaccardDistanceCalculatorTest {

    @Test
    void equalStringsShouldHaveNoDistance() {
        var calculator = new JaccardDistanceCalculator(1);
        assertEquals(0, calculator.calculateDistance("Source String", "Source String"));

    }

    @Test
    void equalStringsDifferentCaseShouldHaveNoDistance() {
        var calculator = new JaccardDistanceCalculator(1);
        assertEquals(0, calculator.calculateDistance("source string", "SOURCE STRING"));

    }

    @Test
    void completelyDifferentStringsShouldHaveMaxDistance() {
        var calculator = new JaccardDistanceCalculator(1);
        assertEquals(1, calculator.calculateDistance("ABCDEF", "UVWXYZ"));

    }

    @Test
    void equallyDistantStringsShouldResultInZeroFive() {
        var calculator = new JaccardDistanceCalculator(2);
        assertEquals(0.5, calculator.calculateDistance("abcde", "abdcde"));
    }

    @Test
    void differentStringsDifferentSetSizesCanResultInDifferentDistances(){
        String source = "Ball Park";
        String target = "Back Pain";
        var calculator = new JaccardDistanceCalculator(1);
        var distance1 = calculator.calculateDistance(source, target);
        calculator.setSetSize(2);;
        var distance2 = calculator.calculateDistance(source, target);
        assertNotEquals(distance1, distance2);
    }
}