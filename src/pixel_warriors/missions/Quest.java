package pixel_warriors.missions;

public class Quest {

    private String title, desc;
    private int gold, exp;

    public Quest() {
    }

    public Quest(String title, String desc, int gold, int exp) {
        this.title = title;
        this.desc = desc;
        this.gold = gold;
        this.exp = exp;
    }

    public void setAll(String title, int value) {
        this.title = title;
        this.desc = title;
        this.gold = value;
        this.exp = value;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getGold() {
        return gold;
    }

    public int getExp() {
        return exp;
    }

}
