package interface_adapter.email_report;

/**
 * The state for the emailing game report view model.
 */
public class EmailReportState {
    private String[][] gameData;
    private int gameNum;
    private String email;

    public String[][] getGameData() { return gameData; }

    public int getGameNum() { return gameNum; }

    public String getEmail() { return email; }

    public void setGameData(String[][] gameData) { this.gameData = gameData; }

    public void setGameNum(int gameNum) { this.gameNum = gameNum; }

    public void setEmail(String email) { this.email = email; }
}
