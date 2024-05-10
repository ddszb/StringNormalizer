package org.ddszb.jobnormalizer;

import org.ddszb.jobnormalizer.distancecalculator.JaccardDistanceCalculator;
import org.ddszb.jobnormalizer.distancecalculator.LevenshteinDistanceCalculator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NormalizerTest {

    private static List<String> normalizedNames;
    private static Normalizer<JaccardDistanceCalculator> jaccardNormalizer;
    private static  Normalizer<LevenshteinDistanceCalculator> levenshteinNormalizer;

    @BeforeAll
    static void init(){
        normalizedNames =  Arrays.asList("Architect", "Software engineer", "Quantity surveyor", "Accountant");
        jaccardNormalizer = new Normalizer<>(normalizedNames);
        levenshteinNormalizer = new Normalizer<>(normalizedNames);
        jaccardNormalizer.setCalculator(new JaccardDistanceCalculator());
        levenshteinNormalizer.setCalculator(new LevenshteinDistanceCalculator());
    }

    @Test
    void jaccardNormalizerWithEmptyListShouldReturnOriginalInput() {
        var normalizer = new Normalizer<>(Collections.emptyList(), new JaccardDistanceCalculator());
        var originalInput = "My Original String";
        assertEquals(originalInput, normalizer.normalize(originalInput));
    }

    @Test
    void levenshteinNormalizerWithEmptyListShouldReturnOriginalInput() {
        var normalizer = new Normalizer<>(Collections.emptyList(), new LevenshteinDistanceCalculator());
        var originalInput = "My Original String";
        assertEquals(originalInput, normalizer.normalize(originalInput));
    }

    @Test
    void normalizerShouldIgnoreCaseForSimilarity(){
        var normalizer = new Normalizer<>(Arrays. asList("ACCOUNTANT", "accountanty"), new LevenshteinDistanceCalculator());
        assertEquals("ACCOUNTANT", normalizer.normalize("chief accountant"));
    }

    @Test
    void TestNormalizationsWithLevenshteinMethod() {
        assertEquals("Accountant", levenshteinNormalizer.normalize("Forensic Accountant"));
        assertEquals("Software engineer", levenshteinNormalizer.normalize("Java engineer"));
        assertEquals("Software engineer", levenshteinNormalizer.normalize("C# engineer"));
        assertEquals("Accountant", levenshteinNormalizer.normalize("Accountant"));
        assertEquals("Accountant", levenshteinNormalizer.normalize("Chief Accountant"));
        assertEquals("Architect", levenshteinNormalizer.normalize("Cloud Architect"));
    }

    @Test
    void TestNormalizationsWithJaccardinMethod() {
        assertEquals("Software engineer", jaccardNormalizer.normalize("Java engineer"));
        assertEquals("Software engineer", jaccardNormalizer.normalize("C# engineer"));
        assertEquals("Accountant", jaccardNormalizer.normalize("Accountant"));
        assertEquals("Accountant", jaccardNormalizer.normalize("Chief Accountant"));
        assertEquals("Architect", jaccardNormalizer.normalize("Cloud Architech"));
        // error left behind on purpose to show difference in performance between two methods
        assertEquals("Accountant", jaccardNormalizer.normalize("Forensic Accountant"));
    }

}