/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcards;

import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JFileChooser;
import java.util.*;
import java.nio.file.*;

/**
 *
 * @author novac
 */
public class Main {
    private Deck d;
    private FlashcardView view;
    /**
     * @param args the command line arguments
     */
    
    
    public void start() {
        // Build our initial sample deck
        d = new Deck();
        d.addCard(new Card("hardware",
            "The physical parts of a computer."));
        d.addCard(new Card("software",
            "The set of commands and rules that make a computer do anything."));
        d.addCard(new Card("abstraction",
                "simplifying to an idea so we can ignore the implementation."));
        //add more cards
        view = new FlashcardView();
        view.setController(this);
        view.setVisible(true);
        view.changeCardText(d.getTopCard().toString());
    }
    
    public void flipButtonPressed() {
        d.getTopCard().flip();
        updateCardInView();
    }

    public void prevButtonPressed() {
        d.prev();
        updateCardInView();
    }

    public void nextButtonPressed() {
        d.next();    
        updateCardInView();
    }
    
    public void openButtonPressed() {
        int returnVal = view.openChooser();
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            String file = view.getChooserFilename();
            readFile(file);
        }
    }
    
    private void updateCardInView() {
        view.changeCardText(d.getTopCard().toString());
    }
    
    public void shuffleButtonPressed() {
        d.shuffle();
        updateCardInView();
    }
    
    public void randomButtonPressed() {
        d.goToRandomCard();
        updateCardInView();
    }
    
    private void readFile(String fname) {
        String newterm = "";
        String newdef = "";
        // clear the old deck
        d = new Deck();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fname), StandardCharsets.UTF_8);
            Iterator it = lines.iterator();
            while (it.hasNext()) {
                String nextline = (String) it.next();
                if (nextline.equals("") && ! newterm.equals("")) {
                    // blank line and we have a card, so we are done with this card
                    // add it to the deck then reset the values

                    d.addCard(new Card(newterm, newdef));
                    newterm = "";
                    newdef = "";
                    
                }
                else if (newterm.equals("")) {
                    // this line must be the new term since newterm doesn't exist
                    newterm = nextline;
                } else {
                    // this line must be part of the definition.
                    if (! newdef.equals("")) {
                        newdef += "\n";
                    }
                    newdef += nextline;
                }
            }
            if (! newterm.equals("")) {
                // we still have one card left when the read finishes...
                d.addCard(new Card(newterm, newdef));
            }

        } catch (IOException ex) {
            view.changeCardText("Problem accesssing the file " + fname);
        }
        updateCardInView();
    }
    
    public static void main(String[] args) {
        Main controller = new Main();
        controller.start();
    }
}
