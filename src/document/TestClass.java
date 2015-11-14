/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package document;

import static document.Document.testCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author LANREWAJU
 */
public class TestClass {

  static  String text = "";

    public TestClass(String text) {
        TestClass.text = text;
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
    /* The main method for testing this class. 
     * You are encouraged to add your own tests.  */
    public int getNumSyllables() {
        //TODO: Implement this method.  See the Module 1 support videos 
        // if you need help.

        List<String> words = getTokens("[a-zA-Z]+");
        int count = 0;
        for (String word : words) {
            System.out.println("\nword: " + word);
            count += countSyllables(word);
            System.out.println("\ncount: " + count);
        }
        return count;

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

        return getSyllableCount(word);
    }

    public static int getSyllableCount(String word) {
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

private  static void testCase(TestClass test,int syllableCount){
    int numberOfSyllables = getSyllableCount(text);
    if(syllableCount == numberOfSyllables){
        System.out.println("Passed");
    }else{
        System.out.println("Failed");
    }
}
    public static void main(String[] args) {
         testCase(new TestClass("this is a test.23,54,390."), 4);
        testCase(new TestClass("This is a test. How many???  "
                + "Senteeeeeeeeeences are here... there should be 5! Right?"),
                16);
        testCase(new TestClass(""), 0);
        testCase(new TestClass("sentence, with, lots, of, commas.!  "
                + "(And some poaren)).  The output is: 7.5."), 15);
        testCase(new TestClass("many???  Senteeeeeeeeeences are"), 6);

    }
}
