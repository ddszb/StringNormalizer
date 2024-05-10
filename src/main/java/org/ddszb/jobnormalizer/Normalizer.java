package org.ddszb.jobnormalizer;

import org.ddszb.jobnormalizer.distancecalculator.IDistanceCalculator;

import java.util.List;

/**
 * Class that implements functionality to a string normalizer
 * It accepts any list of strings and any calculator that implements
 * the IDistanceCalculator interface, and uses this calculator to
 * define the closest match between the input string and the list of
 * normalized names.
 * @param <T> The calculator implementing IDistanceCalculator
 */
public class Normalizer<T extends IDistanceCalculator> {

    private List<String> normalizedNames;
    private T calculator;

    /**
     * Constructor for the Normalizer
     * @param normalizedNames The list of normalized names
     */
    public Normalizer(List<String> normalizedNames) {
        this.normalizedNames = normalizedNames;
    }

    /**
     * Constructor for the Normalizer
     * @param normalizedNames The list of normalized names
     * @param calculator The calculator to use the score the difference between strings
     */
    public Normalizer(List<String> normalizedNames, T calculator) {
        this.normalizedNames = normalizedNames;
        this.calculator = calculator;
    }

    /**
     * Given an input, compares against all the normalizedNames to see the closest match.
     * @param input The input to be compared
     * @return The String in normalizedNames that closest matches with input
     */
    private String closestMatch(String input) {
        double maxScore = Integer.MAX_VALUE;
        String closesMatch = input;
        for (String normalizedName : normalizedNames) {
            double currentScore = calculator.calculateDistance(input.toLowerCase(), normalizedName.toLowerCase());
            if (maxScore > currentScore) {
                maxScore = currentScore;
                closesMatch = normalizedName;
            }
        }
        return closesMatch;
    }

    /**
     * Calls the normalizer to normalize the given input
     * @param input The input to be normalized
     * @return The normalized string calculated with the specified calculator
     */
    public String normalize(String input) {
        return closestMatch(input);
    };

    public T getCalculator() {
        return calculator;
    }

    public void setCalculator(T calculator) {
        this.calculator = calculator;
    }
}
