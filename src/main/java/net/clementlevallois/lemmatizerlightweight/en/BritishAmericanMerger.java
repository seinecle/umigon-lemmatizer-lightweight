/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.lemmatizerlightweight.en;

import java.util.Set;

/**
 *
 * @author LEVALLOIS
 */
public class BritishAmericanMerger {

    private static final Set<String> dontMerge = Set.of("our", "flour", "four", "hour", "pour", "sour", "tour");

    public static String mergeToAmericanEnglish(String input) {

        if (dontMerge.contains(input)) {
            return input;
        }

        if (input.endsWith("our") && !input.endsWith("tour") && !input.endsWith("hour") && !input.endsWith("pour")) {
            return input.substring(0, input.length() - 3) + "or";
        }
        else {
            return input;
        }
    }

}
