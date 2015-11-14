package document;

import java.util.List;

/**
 * A naive implementation of the Document abstract class.
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document {

    /**
     * Create a new BasicDocument object
     *
     * @param text The full text of the Document.
     */
    public BasicDocument(String text) {
        super(text);
    }

    /**
     * Get the number of words in the document. "Words" are defined as
     * contiguous strings of alphabetic characters i.e. any upper or lower case
     * characters a-z or A-Z
     *
     * @return The number of words in the document.
     */
    @Override
    public int getNumWords() {
        List<String> words = getTokens("[a-zA-Z]+");
       // d.getTokens("[a-z()0-9]+");
//Hint: The ( ) will be treated as literal tokens inside the character set.
//
//d.getTokens("[a-z]+|[()0-9]+");
        //  List<String> words = getTokens("[a-z]+|[()0-9]+");
        //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
      //  System.out.println("List of tokens " + words);
        return words.size();
    }

    /**
     * Get the number of sentences in the document. Sentences are defined as
     * contiguous strings of characters ending in an end of sentence punctuation
     * (. ! or ?) or the last contiguous set of characters in the document, even
     * if they don't end with a punctuation mark.
     *
     * @return The number of sentences in the document.
     */
    @Override
    public int getNumSentences() {

        //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.
        List<String> sentences = getTokens("[^.!?]+");
        //List<String> sentences = getTokens("[a-z0-9 ]+");
        // System.out.println("List of sentences " + sentences);
        return sentences.size();
    }

    private static int sumnum() {
        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum = sum + (i * 2);
            } else {
                sum = sum + i;
            }
        }
        return sum;
    }

    /**
     * Get the number of sentences in the document. Words are defined as above.
     * Syllables are defined as: a contiguous sequence of vowels, except for an
     * "e" at the end of a word if the word has another set of contiguous
     * vowels, makes up one syllable. y is considered a vowel.
     *
     * @return The number of syllables in the document.
     */
    @Override
    public int getNumSyllables() {
        //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.

        List<String> words = getTokens("[a-zA-Z]+");
        int count = 0;
        for (String word : words) {
          // System.out.println("\nword: " + word);
            count += countSyllables(word);
          //  System.out.println("\ncount: " + count);
        }
        return count;

    }

    /* The main method for testing this class. 
     * You are encouraged to add your own tests.  */
    public static void main(String[] args) {
        testCase(new BasicDocument("one (1), two (2), three (3)"), 3, 1, 1);
        testCase(new BasicDocument("double"), 1, 1, 1);
        testCase(new BasicDocument("this is a test.23,54,390."), 4, 4, 2);
        testCase(new BasicDocument("This is a test. How many???  "
                + "Senteeeeeeeeeences are here... there should be 5! Right?"),
                16, 13, 5);
        testCase(new BasicDocument(""), 0, 0, 0);
        testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
        testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
        // System.out.println("sum = "+ sumnum());
    }
}
