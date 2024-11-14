package use_case.freePlayMode;

import java.util.ArrayList;

/**
 * input data for free play mode use case
 *
 */
public class FreePlayInputData {
    private final Boolean hit;

    /**
     *
     * @param hit is a bool. if user chooses to hit, it is true.
     */
    public FreePlayInputData(Boolean hit) {
        this.hit = hit;
    }

    public Boolean getHitStatus() {
        return hit;
    }
}
