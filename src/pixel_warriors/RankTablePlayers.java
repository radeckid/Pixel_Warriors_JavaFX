package pixel_warriors;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RankTablePlayers {

    private SimpleIntegerProperty playerId;
    private SimpleStringProperty nick;
    private SimpleIntegerProperty lvl;

    public RankTablePlayers(int playerId, String nick, int lvl) {
        this.playerId = new SimpleIntegerProperty(playerId);
        this.nick = new SimpleStringProperty(nick);
        this.lvl = new SimpleIntegerProperty(lvl);
    }

    public int getPlayerId() {
        return playerId.get();
    }

    public void setPlayerId(int playerId) {
        this.playerId = new SimpleIntegerProperty(playerId);
    }

    public String getNick() {
        return nick.get();
    }

    public void setNick(String nick) {
        this.nick = new SimpleStringProperty(nick);
    }

    public int getLvl() {
        return lvl.get();
    }

    public void setLvl(int lvl) {
        this.lvl = new SimpleIntegerProperty(lvl);
    }
}