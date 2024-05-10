package org.ddszb.jobnormalizer.distancecalculator;

/**
 * Interface that defines the method for the string distance calculation
 */
public interface IDistanceCalculator {

    /**
     * Calculates the distance score between two strings.
     * The lower the distance, the closer the strings match eachother
     * @param source The source string
     * @param target The target string
     * @return The calculated distance score between the strings
     */
    double calculateDistance(String source, String target);
}
