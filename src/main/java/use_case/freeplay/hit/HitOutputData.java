package use_case.freeplay.hit;

import java.util.ArrayList;

public class HitOutputData {
    private ArrayList<String> playerHandImages;
    private ArrayList<String> dealerHandImages;

    public HitOutputData(ArrayList<String> playerHandImages, ArrayList<String> dealerHandImages) {
        this.playerHandImages = playerHandImages;
        this.dealerHandImages = dealerHandImages;
    }

    public ArrayList<String> getPlayerHandImages() {
        return playerHandImages;
    }

    public ArrayList<String> getDealerHandImages() {
        return dealerHandImages;
    }
}
