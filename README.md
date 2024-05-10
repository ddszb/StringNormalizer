# String Normalizer

Provided with a list of normalized names, 
the normalizer can use two different methods to
calculate the best match for a given input string.

The resource folder contains both an input file and normalizedNames files that can be used to test the normalizer.

The two methods implemented are the Levenshtein Distance and the Jaccard Index.

The main method contains the running and printing to the console
the use of both methods, with the Jaccard index using n=1 and n=2 for the set sizes.
In it, it's possible to notice the difference in quality between the two methods