/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.lemmatizerlightweight.es;

import java.util.HashMap;
import java.util.Map;
import net.clementlevallois.lemmatizerlightweight.LemmatizerInterface;

/**
 *
 * @author LEVALLOIS
 */
public class LemmatizerES implements LemmatizerInterface {

    private static final Map<String, String> irregularVerbs = createIrregularVerbsMap();

    public LemmatizerES() {
    }

    private static Map<String, String> createIrregularVerbsMap() {
        Map<String, String> irregularVerbs = new HashMap<>();
        irregularVerbs.put("ser", "ser");
        irregularVerbs.put("estar", "estar");
        irregularVerbs.put("ir", "ir");
        // Add more irregular verb forms and their infinitive forms as needed
        return irregularVerbs;
    }

    public String lemmatizeTerm(String term) {
        // Handle feminine forms
        if (term.endsWith("a") || term.endsWith("as")) {
            term = term.substring(0, term.length() - 1); // Remove the last character
        }

        // Handle plural forms
        if (term.endsWith("s")) {
            term = term.substring(0, term.length() - 1); // Remove the last character
        }

        // Handle verb lemmatization
        if (term.endsWith("ar") || term.endsWith("er") || term.endsWith("ir")) {
            term = term.substring(0, term.length() - 2); // Remove the last two characters
        }

        // Handle irregular verbs
        if (irregularVerbs.containsKey(term)) {
            term = irregularVerbs.get(term); // Replace with the corresponding infinitive form
        }

        // Apply additional rules for specific cases
        if (term.endsWith("ción") || term.endsWith("sión")) {
            term = term.substring(0, term.length() - 3); // Remove the last three characters
        }

        // Rule: Remove the suffix "mente" for adverbs
        if (term.endsWith("mente")) {
            term = term.substring(0, term.length() - 5); // Remove the last five characters
        }

        // Rule: Handle plural forms ending in "es"
        if (term.endsWith("es")) {
            term = term.substring(0, term.length() - 2); // Remove the last two characters
        }

        // Rule: Handle adjective forms ending in "os", "as", "o", "a"
        if (term.endsWith("os") || term.endsWith("as") || term.endsWith("o") || term.endsWith("a")) {
            term = term.substring(0, term.length() - 1); // Remove the last character
        }

        // Rule: Handle nouns ending in "ista" and "istas"
        if (term.endsWith("ista") || term.endsWith("istas")) {
            term = term.substring(0, term.length() - 4); // Remove the last four characters
        }

        // New rules
        // Rule: Handle verb forms ending in "aba" and "ía" and convert them to infinitive
        if (term.endsWith("aba") || term.endsWith("ía")) {
            term = term.substring(0, term.length() - 3); // Remove the last three characters
        }

        // Rule: Handle adjective forms ending in "ante" and "iente" and remove the suffix
        if (term.endsWith("ante") || term.endsWith("iente")) {
            term = term.substring(0, term.length() - 4); // Remove the last four characters
        }

        // Rule: Handle noun forms ending in "idad" and remove the suffix
        if (term.endsWith("idad")) {
            term = term.substring(0, term.length() - 4); // Remove the last four characters
        }

        // Rule: Handle noun forms ending in "ero" and "era" and remove the suffix
        if (term.endsWith("ero") || term.endsWith("era")) {
            term = term.substring(0, term.length() - 3); // Remove the last three characters
        }

        // Rule: Handle noun forms ending in "eza" and remove the suffix
        if (term.endsWith("eza")) {
            term = term.substring(0, term.length() - 3); // Remove the last three characters
        }

        // Rule: Handle verb forms ending in "ando" and "iendo" and remove the suffix
        if (term.endsWith("ando") || term.endsWith("iendo")) {
            term = term.substring(0, term.length() - 4); // Remove the last four characters
        }

        // Add more rules as needed for specific cases
        return term;
    }

}
