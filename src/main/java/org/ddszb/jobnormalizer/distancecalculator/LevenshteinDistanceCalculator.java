package org.ddszb.jobnormalizer.distancecalculator;

import org.apache.commons.text.similarity.LevenshteinDistance;

/**
 * Class that implements the calculateDistance method using the LevenshteinDistance algorithm
 */
public class LevenshteinDistanceCalculator implements IDistanceCalculator {

    @Override
    public double calculateDistance(String source, String target) {
        return new LevenshteinDistance().apply(source, target).doubleValue();
    }
}
