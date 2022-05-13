/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcards;

/**
 *
 * @author novac
 */
public class Card {

    private String term;
    private String def;
    private boolean flipped = false;

    public Card(String theTerm, String theDef) {
        term = theTerm;
        def = theDef;
    }

    public Card(String theTerm, String theDef, boolean flip) {
        term = theTerm;
        def = theDef;
        flipped = flip;
    }

    public void flip() {
        flipped = !flipped;
    }

    public String getTerm() {
        return term;
    }

    public String getDef() {
        return def;
    }

    public boolean isFlipped() {
        return flipped;
    }

    @Override
    public String toString() {
        if (isFlipped()) {
            return getDef();
        } else {
            return getTerm();
        }
    }
}
