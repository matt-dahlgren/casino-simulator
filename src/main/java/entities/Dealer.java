package entities;

public class Dealer extends Player {
    public Dealer() {
    }

    public void doTurn() {
        while (getScore() < 17) {
            hit();
        }

        if (getScore() >= 17) {
            bust();
        }

        stand();
    }
}
