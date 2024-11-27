package data_access;

import entities.Card;
import entities.CardFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.freePlayMode.FreePlayDA;

import java.io.IOException;

import static api.APIConstants.*;

public class APIDataAccessObject implements FreePlayDA {
    /**
     * @param deckID is the ID of the current deck in play
     * @return Card object of the new card
     */
    @Override
    public Card getCard(String deckID) {
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
     * @return ID of the new deck
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
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        } catch (final IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param deckID is the ID of the current deck in play
     * @return true if deck has been shuffled, elese false
     */
    @Override
    public Boolean shuffleExistingDeck(String deckID) {
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
}

