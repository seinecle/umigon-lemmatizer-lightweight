/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package net.clementlevallois.lemmatizerlightweight.tests;

import net.clementlevallois.lemmatizerlightweight.Lemmatizer;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author LEVALLOIS
 */
public class LemmatizerTest {

    public LemmatizerTest() {
    }

    /**
     * Test of generateNgramsUpto method, of class
     * NGramFinderBisForTextFragments.
     */
    @Test
    public void testBritishAmericanMerger() {
        String example = "What a strange behaviour what a behavior";
        
        Lemmatizer l = new Lemmatizer("en");
        String sentenceLemmatizer = l.sentenceLemmatizer(example);
        Assert.assertTrue(sentenceLemmatizer.contains("behavior"));
        Assert.assertFalse(sentenceLemmatizer.contains("behaviour"));

        l.dontMergeToAmericanEnglish();
        sentenceLemmatizer = l.sentenceLemmatizer(example);
        Assert.assertTrue(sentenceLemmatizer.contains("behavior"));
        Assert.assertTrue(sentenceLemmatizer.contains("behaviour"));
        
    }

}
