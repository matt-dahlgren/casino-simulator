package interface_adapter.freeplay.setup;

public class SetupState {
    private String dealerCardOne="";
    private String dealerCardTwo="";
    private String playerCardOne="";
    private String playerCardTwo="";

    public String getDealerCardOne(){
        return dealerCardOne;
    }
    public String getDealerCardTwo(){
        return dealerCardTwo;
    }
    public String getPlayerCardOne(){
        return playerCardOne;
    }
    public String getPlayerCardTwo(){
        return playerCardTwo;
    }
    public void setDealerCardOne(String firstDealerCard){
        this.dealerCardOne=firstDealerCard;
    }
    public void setDealerCardTwo(String secondDealerCard){
        this.dealerCardTwo=secondDealerCard;
    }
    public void setPlayerCardOne(String firstPlayerCard){
        this.playerCardOne=firstPlayerCard;
    }
    public void setPlayerCardTwo(String secondPlayerCard){
        this.playerCardTwo=secondPlayerCard;
    }
}
