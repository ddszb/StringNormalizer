package org.ddszb.jobnormalizer.distancecalculator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that implements the calculateDistance method using the Jaccard similarity coefficient
 */
public class JaccardDistanceCalculator implements IDistanceCalculator {

    private int setSize;
    private List<String> uniqueSets;


    /**
     * Default constructor initializes a set of size 1
     */
    public JaccardDistanceCalculator() {
        this.setSize = 1;
    }

    /**
     * Constructor that allows for custom set sizes
     * @param setSize The set size
     */
    public JaccardDistanceCalculator(int setSize) {
        this.setSize = setSize;
    }

    /**
     * Initializes the uniqueSets list by adding sets from both source and target strings
     * @param source The source string
     * @param target The target string
     */
    private void fillUniqueSets(String source, String target) {
        Set<String> newSet = new HashSet<>();
        addToSet(newSet, source);
        addToSet(newSet, target);
        this.uniqueSets = new ArrayList<>(newSet);
    }

    /**
     * Adds to a Set<String> based on the input and set size
     * @param nSizeSet The set being filled
     * @param input The string to be split into sets
     */
    private void addToSet(Set<String> nSizeSet, String input) {
        for (int i = 0; i <= input.length() - setSize; i++) {
            String set = input.substring(i, i + setSize);
            nSizeSet.add(set.toLowerCase());
        }
    }

    /**
     * This method defines a list of sets from both strings and compares the
     * size of the intersection between these sets by the size of the union
     * of these two sets.
     * @param source The source string
     * @param target The target string
     * @return The distance score calculated by the algorithm
     */
    @Override
    public double calculateDistance(String source, String target) {
        // Populates the set for both words
        fillUniqueSets(source, target);

        // Counts the number of sets present in both strings
        long matchingSets = (int) uniqueSets
                 .stream()
                 .filter(set ->source.toLowerCase().contains(set) && target.toLowerCase().contains(set))
                 .count();
        double coefficient = (double) matchingSets / uniqueSets.size();
        // Jaccard coeffienct calculates the similarity, so we return the complement for distance
        return 1 - coefficient;

    }

    public int getSetSize() {
        return setSize;
    }

    public void setSetSize(int setSize) {
        this.setSize = setSize;
    }
}
