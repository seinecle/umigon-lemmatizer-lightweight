/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.clementlevallois.lemmatizerlightweight;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.clementlevallois.lemmatizerlightweight.en.LemmatizerEN;
import net.clementlevallois.lemmatizerlightweight.es.LemmatizerES;
import net.clementlevallois.lemmatizerlightweight.fr.LemmatizerFR;

/**
 *
 * @author LEVALLOIS
 */
public class Lemmatizer {

    private String[] noLemmaEN = new String[]{"access", "accumbens", "addresses", "afterwards", "always", "amazing", "approaches", "analyses", "biases", "businesses", "ceiling", "classes", "crises", "daunting", "discusses", "during", "economics", "elsevier", "ethics", "focuses", "fries", "goes", "humanities", "hundred", "hypotheses", "inches", "king", "lens", "linguistics", "lies", "losses", "marketing", "morning", "news", "outlier", "outstanding", "physics", "politics", "premises", "processes", "red", "rigged", "ries", "series", "sometimes", "something", "species", "spring", "status", "ted", "themselves", "neural processes", "united", "wales", "witnesses"};
    private String[] noLemmaFR = new String[]{"accès", "ailleurs", "alors", "alpes", "ailleurs", "apres", "après", "arrière", "arrières", "aupres", "auprès", "Calvados", "concours", "corps", "cours", "critère", "critères", "dans", "discours", "divers", "états-unis", "etats-unis", "etes", "êtes", "ethos", "éthos", "gens", "gros", "héros", "lors", "outils", "pays", "parcours", "pres", "près", "proces", "procès", "propos", "puis", "recours", "sans", "secours", "sens", "sommes", "succès", "succes", "temps", "toujours", "travers", "très", "tres", "univers", "viens", "vos"};
    private String[] noLemmaES = new String[]{"revés", "atrás", "país", "gafas", "años","adiós","peces","tres", "azulgris","compás","menos", "mes", "tijeras", "avis", "anís", "vals", "compás", "alas", "análisis", "oasis", "paréntesis", "estrés", "colchones", "espejuelos", "martes", "lunes", "miércoles", "jueves", "viernes", "calcetines", "álbumes", "nueces", "veces", "coches", "alfileres", "lazos", "pistaches", "pañales", "prismas", "bolsos", "panes", "alfileres", "golpes", "jardines", "manos", "ojos", "dedos", "radios"};
    private String[] noLemma = new String[]{"analytics", "accumbens", "aws", "bayes", "business", "charles", "ects", "cnrs", "cosmos", "cowles", "deep learning", "developer", "ethos", "faas", "forbes", "iaas", "james", "keynes", "koopmans", "nhs", "paas", "paris", "programming", "reactjs", "saas", "siemens", "sanders", "ted", "virus", "vuejs", "united states"};

    private Set<String> noLemmaSet;
    private boolean mergeToAmericanEnglish = true;
    private LemmatizerInterface lemmatizerInterface;

    public void dontMergeToAmericanEnglish() {
        mergeToAmericanEnglish = false;
        if (lemmatizerInterface!= null && lemmatizerInterface instanceof LemmatizerEN lemmatizerEN){
            ((LemmatizerEN) lemmatizerInterface).dontMergeToAmericanEnglish();
        }
    }

    /**
     *
     * @param lang the language of the text to lemmatize. "en" and "fr" are the
     * two accepted values.
     */
    public Lemmatizer(String lang) {
        noLemmaSet = new HashSet();
        noLemmaSet.addAll(Arrays.asList(noLemma));

        switch (lang) {
            case "en":
                noLemmaSet.addAll(Arrays.asList(noLemmaEN));
                lemmatizerInterface = new LemmatizerEN(mergeToAmericanEnglish);
                break;
            case "fr":
                noLemmaSet.addAll(Arrays.asList(noLemmaFR));
                lemmatizerInterface = new LemmatizerFR();
                break;
            case "es":
                noLemmaSet.addAll(Arrays.asList(noLemmaES));
                lemmatizerInterface = new LemmatizerES();
                break;
            default:
                noLemmaSet.addAll(Arrays.asList(noLemmaEN));
                lemmatizerInterface = new LemmatizerEN(mergeToAmericanEnglish);
                break;
        }
    }

    /**
     *
     * @param term the term to lematize
     * @return the lemmatized term
     */
    public String lemmatize(String term) {

        if (noLemmaSet.contains(term)) {
            return term;
        }
        term = lemmatizerInterface.lemmatizeTerm(term);

        if (term.endsWith("'")) {
            term = term.substring(0, term.length() - 1);
        }
        return term.trim();

    }

    /**
     *
     * @param sentence the sentence to lemmatize
     * @return the lemmatized sentence
     */
    public String sentenceLemmatizer(String sentence) {

        String[] terms = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String term : terms) {
            term = lemmatizerInterface.lemmatizeTerm(term);
            sb.append(term);
            sb.append(" ");
        }
        return sb.toString().trim();
    }
}
