/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.clementlevallois.lemmatizerlightweight.fr;

import java.util.Set;
import net.clementlevallois.lemmatizerlightweight.LemmatizerInterface;

/**
 *
 * @author LEVALLOIS
 */
public class LemmatizerFR implements LemmatizerInterface {


    public LemmatizerFR() {
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
        if (term.endsWith("ies") || term.endsWith("sses")) {
            term = term.substring(0, term.length() - 1);
        }
        if (term.endsWith("ère")) {
            term = term.substring(0, term.length() - 3) + "er";
        }
        if (term.endsWith("rions")) {
            term = term.substring(0, term.length() - 5);
        }
        // more work to do on French conjuguaisons obviously - doable! Get in touch via Github issues.

        return term;

    }

}
