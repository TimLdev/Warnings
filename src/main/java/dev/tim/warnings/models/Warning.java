package dev.tim.warnings.models;

import java.util.Date;
import java.util.Random;

public class Warning {

    private String playerName;
    private String playerWhoWarnedName;
    private String reason;
    private Date date;
    private String id;

    public Warning(String playerName, String playerWhoWarnedName, String reason){
        this.id = String.valueOf(new Random().nextInt(9999999) + 10000);
        this.playerName = playerName;
        this.playerWhoWarnedName = playerWhoWarnedName;
        this.reason = reason;
        this.date = new Date();
    }

    public String getId(){
        return id;
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerWhoWarnedName(){
        return playerWhoWarnedName;
    }

    public String getReason() { return reason; }

    public Date getDate() { return date; }

}
