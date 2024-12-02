package use_case.gameplay_use_cases.start_game;

import interface_adapter.team_use_case.TeamState;

public class Start_GameInteractor implements Start_GameInputBoundary{
    private final TeamState teamState;
    private final Start_GameOutputBoundary presenter;

    public Start_GameInteractor(TeamState teamState) {}
}
