package entities;

/**
 * Factory that creates a card class.
 */
public class CardFactory implements CardFactoryInterface {

    /**
     * Creates card entity.
     * @param rank is the rank of the card
     * @param suit is the suit of the card
     * @return Card type object
     */
    @Override
    public Card createCard(String rank, String suit, String image) {
        return new Card(rank, suit, image);
    }
}
