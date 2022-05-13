/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flashcards;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author novac
 */
public class Deck {

    private ArrayList<Card> cards;
    private int topCard = 0;
    private int numCards = 0;

    public Deck() {
        cards = new ArrayList<Card>();
        numCards = 0;
    }

    public void addCard(Card c) {
        cards.add(c);
        numCards++;
    }

    public void next() {
        if (topCard >= numCards - 1) {
            topCard = 0;
        } else {
            topCard++;
        }
    }

    public void prev() {
        if (topCard <= 0) {
            topCard = numCards - 1;
        } else {
            topCard--;
        }
    }

    public Card getTopCard() {
        return cards.get(topCard);
    }

    public void shuffle() {
        // modified from https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
        // modified to use an arraylist rather than an array
        for (int i = 0; i < numCards; i++) {
            int randomPosition = (int) (Math.random() * numCards);
            Card temp = cards.get(i);
            cards.set(i,cards.get(randomPosition));
            cards.set(randomPosition,temp);
        }
        goToRandomCard();

    }

    void goToRandomCard() {
        topCard = (int) (Math.random() * numCards);
    }
}
