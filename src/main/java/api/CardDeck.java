package api;

import java.io.IOException;

import entities.CardFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import entities.Card;
import entities.Dealer;
import entities.Player;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static api.APIConstants.*;


/**
 * The CardDeck class used to interact with the DeckofCards API.
 */
abstract public class CardDeck implements DeckofCards {

    /**
     * Gets the deck ID from the deckofcards API
     */
    @Override
    public String getNewDeckID() {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder().url(SHUFFLE).build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean("success")) {
                return responseBody.getString("deck_id");
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (final IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A method that draws 1card from the deck in play.
     *
     * @param deckID is the id of the deck you want to draw from
     * @return the Card pulled from the deck in play.
     */
    @Override
    public Card drawCard(String deckID) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final Request request = new Request.Builder().
                url(String.format("%s%s/draw/?count=%d", URL, deckID, ONE_CARD)).build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean("success")) {
                CardFactory cardFactory = new CardFactory();
                JSONObject cards = responseBody.getJSONArray("cards").getJSONObject(0);
                return cardFactory.createCard(cards.getString("value"), cards.getString("suit"));
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (final IOException | JSONException e) {
            throw new RuntimeException(e);
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
    public boolean shuffleDeck(String deckID) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final Request request = new Request.Builder().
                url(String.format("%s%s/shuffle/?remaining=true", URL, deckID)).build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getBoolean("success")) {
                return true;
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (final IOException | JSONException e) {
            throw new RuntimeException(e);
        }
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