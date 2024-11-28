package use_case.freeplay.hit;

import java.util.ArrayList;

public class HitOutputData {
    private ArrayList<String> playerHandImages;

    public HitOutputData(ArrayList<String> playerHandImages) {
        this.playerHandImages = playerHandImages;
    }

    public ArrayList<String> getPlayerHandImages() {
        return playerHandImages;
    }
}
