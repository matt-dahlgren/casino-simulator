package entities;

/**
 * Interface that makes a card.
 */
public interface CardFactoryInterface {
    Card createCard(String rank, String suit);
}
