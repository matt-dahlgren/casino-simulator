package view;

import interface_adapter.freeplay.stand.FreePlayStandViewModel;
import

import javax.swing.*;
import java.util.ArrayList;

public class DealerAfterStandView extends JPanel {

    FreePlayStandViewModel viewModel;
    ArrayList<String> dealerCards;
    int dealerScore;
    int playerScore;

    public DealerAfterStandView(FreePlayStandViewModel viewModel) {
        this.viewModel = viewModel;

    }

}
