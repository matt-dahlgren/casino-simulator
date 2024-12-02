package entities;

/**
 * Interface that makes a card.
 */
public interface CardFactoryInterface {
    /**
     * Creates a card.
     * @param rank rank.
     * @param suit suit.
     * @param image link to image.
     * @return card entity.
     */
    Card createCard(String rank, String suit, String image);
}
