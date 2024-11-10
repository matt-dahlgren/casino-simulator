package api;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;


import entities.Card;
import entities.Dealer;
import entities.Player;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static api.APIConstants.SHUFFLE;


/**
 * The CardDeck class used to interact with the DeckofCards API.
 */
public class CardDeck implements DeckofCards {

    /**
     * Gets the deck ID from the deckofcards API
     */
    @Override
    public String getDeckID() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().url(SHUFFLE).build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean("success")) {
                return responseBody.getString("deck_id");
            }
            else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (final IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that draws a card from the deck in play.
     * @param numCards is the amount of cards you want
     * @return the Card pulled from the deck in play.
     */
    @Override
    public Card drawCard(int numCards) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().
                url(String.format("%s/draw/?count=%d", getDeckID(), numCards)).build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean("success")) {
                final JSONObject cards = responseBody.getJSONObject("cards");
            }
    }

    /**
     * A method that creates a pile to act as the player's hand.
     *
     * @param player is a valid Player.
     * @param card   is a valid Card.
     * @return True if the card(s) have been added to the player's pile successfully.
     */
    @Override
    public boolean addtoHand(Player player, Card card) {
        return false;
    }

    /**
     * A method that creates a pile to act as the dealer's hand.
     *
     * @param dealer is a valid Dealer
     * @param card   is a valid Card.
     * @return
     */
    @Override
    public boolean addtoDealerHand(Dealer dealer, Card card) {
        return false;
    }

    /**
     * A method that shuffles the deck in play.
     *
     * @return True if and only if the deck shuffle is successful.
     */
    @Override
    public boolean shuffleDeck() {
        return false;
    }

    /**
     * A method that returns all hands back to the deck to be reshuffled.
     *
     * @return True if and only if the deck has been reset and is shuffled.
     */
    @Override
    public boolean returnHandstoDeck() {
        return false;
    }
}