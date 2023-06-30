/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.lemmatizerlightweight.en;

import java.util.Set;
import net.clementlevallois.lemmatizerlightweight.LemmatizerInterface;

/**
 *
 * @author LEVALLOIS
 */
public class LemmatizerEN implements LemmatizerInterface {

    private boolean mergeToAmericanEnglish;

    public LemmatizerEN(boolean mergeToAmericanEnglish) {
        this.mergeToAmericanEnglish = mergeToAmericanEnglish;
    }
    
    public void dontMergeToAmericanEnglish(){
        this.mergeToAmericanEnglish = false;
    }

    @Override
    public String lemmatizeTerm(String term) {
        if ((term.endsWith("s") | term.endsWith("s'"))
                && !term.endsWith("us")
                && !term.endsWith("as")
                && !term.endsWith("ss")
                && !term.endsWith("sses")
                && !term.endsWith("ies")
                && !term.endsWith("is")) {
            if (term.endsWith("s")) {
                term = term.substring(0, term.length() - 1);
            }
            if (term.endsWith("s'")) {
                term = term.substring(0, term.length() - 2);
            }

        } else if (term.endsWith("'")) {
            term = term.substring(0, term.length() - 1);
        }

        if (term.endsWith("sses")) {
            term = term.substring(0, term.length() - 2);
        }
        if (term.endsWith("ies")) {
            if (!term.endsWith("movies")) {
                term = term.substring(0, term.length() - 3) + "y";
            } else {
                term = term.substring(0, term.length() - 1);
            }
        } else if (term.endsWith("'s")) {
            term = term.substring(0, term.length() - 2);
        } else if (term.endsWith("ed")) {
            if (term.endsWith("rred") || term.endsWith("mmed")) {
                term = term.substring(0, term.length() - 3);
            } else if (term.endsWith("lked")
                    || term.endsWith("cked")
                    || term.endsWith("pted")
                    || term.endsWith("ssed")
                    || term.endsWith("lled")
                    || term.endsWith("iased")
                    || (term.endsWith("red") && (!term.endsWith("ired") & (!term.endsWith("ured") && !term.equals("clustered")) & !term.endsWith("ared")))
                    || (term.endsWith("med") && (!term.endsWith("framed") & !term.endsWith("lamed") & !term.endsWith("named") & !term.endsWith("shamed")))
                    || term.endsWith("aired")
                    || term.endsWith("used")
                    || term.endsWith("ned")
                    || (term.endsWith("ded") & !term.endsWith("ided"))
                    || (term.endsWith("ted") & !term.endsWith("ated"))) {
                term = term.substring(0, term.length() - 2);
            } else if (term.endsWith("ied")) {
                term = term.substring(0, term.length() - 3) + "y";
            } else if (term.endsWith("eed")) {
                // do nothing (as in: exceed, proceed)
            } else {
                // purchased -> purchase
                term = term.substring(0, term.length() - 1);
            }
        } else if (term.endsWith("'s")) {
            term = term.substring(0, term.length() - 2);
        } else if (term.endsWith("ing")) {
            if (term.endsWith("king")) {
                term = term.substring(0, term.length() - 3) + "e";
            } else if (term.endsWith("ging") && !term.endsWith("gging")) {
                term = term.substring(0, term.length() - 3) + "e";
            } else if (term.endsWith("sing")
                    || term.endsWith("zing")
                    || term.endsWith("cing")
                    || (term.endsWith("oding") && !term.endsWith("ooding"))
                    || (term.endsWith("ting") && !term.endsWith("sting") && !term.endsWith("tting") && !term.endsWith("nting") && !term.endsWith("cting"))
                    || (term.endsWith("ming") && (term.endsWith("framing") || !term.endsWith("laming") || !term.endsWith("naming") || !term.endsWith("shaming")))
                    || term.endsWith("ving")
                    || term.endsWith("ring") & !term.endsWith("during")) {
                term = term.substring(0, term.length() - 3) + "e";
            } else if (term.length() > 2) {
                term = term.substring(0, term.length() - 3);
                //running has become runn. Should become run.
                int size = term.length();
                if (size > 1) {
                    String lastTwoLetters = term.substring(size - 2, size);
                    if (lastTwoLetters.length() > 1 && lastTwoLetters.charAt(0) == lastTwoLetters.charAt(1)) {
                        term = term.substring(0, term.length() - 1);
                    }
                    size = term.length();
                    if (size > 1) {
                        //voting has become vot. Should become vote. Same for any word ending in at or ot (not plant, suspect, ...). Yet, it will miscorrect "pivot" and "format"
                        lastTwoLetters = term.substring(size - 2, size);
                        if (!term.equals("pivot") && !term.equals("format")) {
                            if (lastTwoLetters.equals("at") || lastTwoLetters.equals("ot") || lastTwoLetters.equals("id")) {
                                term = term + "e";
                            }
                        }
                    }
                }
            }
        } else if (term.endsWith("ier")) {
            term = term.substring(0, term.length() - 3) + "y";
        }
        if (mergeToAmericanEnglish) {
            term = BritishAmericanMerger.mergeToAmericanEnglish(term);
        }
        return term;

    }

}
