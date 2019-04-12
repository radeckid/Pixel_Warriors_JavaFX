package pixel_warriors.missions;

public class Quest {

    private String title, desc;
    private int gold, exp;

    public Quest(String title, String desc, int gold, int exp) {
        this.title = title;
        this.desc = desc;
        this.gold = gold;
        this.exp = exp;
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
