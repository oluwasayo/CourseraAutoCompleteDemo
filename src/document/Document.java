package document;

/**
 * A class that represents a text document
 *
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

    private String text;

    /**
     * Create a new document from the given text. Because this class is
     * abstract, this is used only from subclasses.
     *
     * @param text The text of the document.
     */
    protected Document(String text) {
        this.text = text;
    }

    /**
     * Returns the tokens that match the regex pattern from the document text
     * string.
     *
     * @param pattern A regular expression string specifying the token pattern
     * desired
     * @return A List of tokens from the document text that match the regex
     * pattern
     */
    protected List<String> getTokens(String pattern) {
        ArrayList<String> tokens = new ArrayList<>();
        Pattern tokSplitter = Pattern.compile(pattern);

        Matcher m = tokSplitter.matcher(text);

        while (m.find()) {
            tokens.add(m.group());
        }

        return tokens;
    }

    // This is a helper function that returns the number of syllables
    // in a word.  You should write this and use it in your 
    // BasicDocument class.
    // You will probably NOT need to add a countWords or a countSentences method
    // here.  The reason we put countSyllables here because we'll use it again
    // next week when we implement the EfficientDocument class.
    protected int countSyllables(String word) {
        // TODO: Implement this method so that you can call it from the 
        // getNumSyllables method in BasicDocument (module 1) and 
        // EfficientDocument (module 2).
        //text = word;
//        List<String> syllables = getTokens("[aeiouy]+");
//        System.out.println("List of syllables " + syllables);

        //return getSyllableCount(word);
        return syllableCountByCoursera(word);
    }

    private int syllableCountByCoursera(String word) {
        int numSyllables = 0;
        boolean newSyllable = true;
        String vowels = "aeiouy";
        char[] cArray = word.toCharArray();
        for (int i = 0; i < cArray.length; i++) {
            if (i == cArray.length - 1 && cArray[i] == 'e' && newSyllable && numSyllables > 0) {
                numSyllables--;
            }
            if (newSyllable && vowels.indexOf(Character.toLowerCase(cArray[i])) >= 0) {
                newSyllable = false;
                numSyllables++;
            } else if (vowels.indexOf(Character.toLowerCase(cArray[i])) < 0) {
                newSyllable = true;
            }
        }
        return numSyllables;
    }

    public int getSyllableCount(String word) {
        int syllableCount = 0;

        Scanner sc = new Scanner(word);
        sc.useDelimiter("[aeiouy]+");

        while (sc.hasNext()) {
            String c = sc.next();
            syllableCount++;
            System.out.println("\nc:" + c + " syllable count " + syllableCount);
        }
//             if (word.startsWith("a") || word.startsWith("i") || word.startsWith("o")
//                || word.startsWith("u") || word.startsWith("y") || word.startsWith("e")) {
//            syllableCount = syllableCount + 1;
//            System.out.println("met one of them");
//        }
        if (!word.endsWith("a") && !word.endsWith("i") && !word.endsWith("o")
                && !word.endsWith("u") && !word.endsWith("y") && !word.endsWith("ae") && !word.endsWith("ie") && !word.endsWith("o")
                && !word.endsWith("ue") && !word.endsWith("ye")) {
            syllableCount = syllableCount - 1;
        }

//        if (word.endsWith("e")) {
//            syllableCount = syllableCount - 1;
//        }
        System.out.println("\nsyllable count " + syllableCount);
        if (syllableCount != 0 && (word.startsWith("a") || word.startsWith("i") || word.startsWith("o")
                || word.startsWith("u") || word.startsWith("y") || word.startsWith("e"))) {
            syllableCount = syllableCount + 1;
            System.out.println("met one of them");
        }

        return syllableCount <= 0 ? 1 : syllableCount;
    }

    /**
     * A method for testing
     *
     * @param doc The Document object to test
     * @param syllables The expected number of syllables
     * @param words The expected number of words
     * @param sentences The expected number of sentences
     * @return true if the test case passed. False otherwise.
     */
    public static boolean testCase(Document doc, int syllables, int words, int sentences) {
        System.out.println("Testing text: ");
        System.out.print(doc.getText() + "\n....");
        boolean passed = true;
        int syllFound = doc.getNumSyllables();
        int wordsFound = doc.getNumWords();
        int sentFound = doc.getNumSentences();
        if (syllFound != syllables) {
            System.out.println("\nIncorrect number of syllables.  Found " + syllFound
                    + ", expected " + syllables);
            passed = false;
        } else {
            System.out.println("\nCorrect number of syllables.  Found " + syllFound
                    + ", expected " + syllables);
        }
        if (wordsFound != words) {
            System.out.println("\nIncorrect number of words.  Found " + wordsFound
                    + ", expected " + words);
            passed = false;
        } else {
            System.out.println("\nCorrect number of words.  Found " + wordsFound
                    + ", expected " + words);
        }
        if (sentFound != sentences) {
            System.out.println("\nIncorrect number of sentences.  Found " + sentFound
                    + ", expected " + sentences);
            passed = false;
        } else {
            System.out.println("\ncorrect number of sentences.  Found " + sentFound
                    + ", expected " + sentences);
        }

        if (passed) {
            System.out.println("passed.\n");
        } else {
            System.out.println("FAILED.\n");
        }
        System.out.println("========================================");
        return passed;
    }

    /**
     * Return the number of words in this document
     */
    public abstract int getNumWords();

    /**
     * Return the number of sentences in this document
     */
    public abstract int getNumSentences();

    /**
     * Return the number of syllables in this document
     */
    public abstract int getNumSyllables();

    /**
     * Return the entire text of this document
     */
    public String getText() {
        return this.text;
    }

    /**
     * return the Flesch readability score of this document
     */
    public double getFleschScore() {
        // TODO: Implement this method
//        double determinant1 = getNumWords() / getNumSentences();// 2.33333333 2.36833333
//        double determinant2 = getNumSyllables() / getNumWords();// 1.57142857 132.942857
//        double score = 206.835 - (1.015 * determinant1) - (84.6 * determinant2);
//        return score;

        double wordCount = (double) getNumWords();
        return 206.835 - (1.015 * ((wordCount) / getNumSentences()))
                - (84.6 * (((double) getNumSyllables()) / wordCount));
    }

}
