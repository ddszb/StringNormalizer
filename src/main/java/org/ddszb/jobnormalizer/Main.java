package org.ddszb.jobnormalizer;


import org.ddszb.jobnormalizer.distancecalculator.JaccardDistanceCalculator;
import org.ddszb.jobnormalizer.distancecalculator.LevenshteinDistanceCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;


public class Main {

    /**
     * Read lines from a file and returns as List<String>
     * @param fileName Name of the file in the resource folder
     * @return The lines from the folder as list or empty list if the file is not found
     */
    private static List<String> getListFromFile(String fileName) {
        try {
            URL url = ClassLoader.getSystemResource(fileName);
            Scanner s = new Scanner(new File(url.getPath()));
            ArrayList<String> inputList = new ArrayList<>();
            while (s.hasNextLine()) {
                inputList.add(s.nextLine());
            }
            s.close();
            return inputList;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }

    }

    private static void printResult(String input, String result) {
        System.out.printf("%-30s => %s%n", input, result);
    }

    public static void main(String[] args) {
        // Load input file and normalized names file
        List<String> normalizedTitles = getListFromFile("normalizedNames");
        List<String> inputList = getListFromFile("input");

        // Instantiates two different normalizers
        Normalizer<LevenshteinDistanceCalculator> levenshteinNormalizer = new Normalizer<>(normalizedTitles, new LevenshteinDistanceCalculator());
        Normalizer<JaccardDistanceCalculator> jaccardNormalizer = new Normalizer<>(normalizedTitles, new JaccardDistanceCalculator());

        System.out.println("Using Levenshtein Distance\n");
        inputList.forEach(input -> printResult(input, levenshteinNormalizer.normalize(input)));

        System.out.println("\nUsing Jaccard Index with n=1\n");
        inputList.forEach(input -> printResult(input, jaccardNormalizer.normalize(input)));

        // Changing set size to n=2
        jaccardNormalizer.getCalculator().setSetSize(2);
        System.out.println("\nUsing Jaccard Index with n=2\n");
        inputList.forEach(input -> printResult(input, jaccardNormalizer.normalize(input)));
    }
}