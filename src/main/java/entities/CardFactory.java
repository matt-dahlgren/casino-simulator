package entities;

/**
 * Factory that creates a card class
 */
public class CardFactory implements CardFactoryInterface {

    /**
     * @param rank is the rank of the card
     * @param suit is the suit of the card
     * @return Card type object
     */
    @Override
    public Card createCard(String rank, String suit) {
        return new Card(rank, suit);
    }
}
