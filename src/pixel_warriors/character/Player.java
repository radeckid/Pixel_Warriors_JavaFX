package pixel_warriors.character;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import pixel_warriors.character.characterlogics.ItemFromDatabase;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;


public abstract class Player {
    private Inventory inventory;
    private Backpack backpack;
    protected Statistic statistic;

    public Player() {
        this.inventory = null;
        this.backpack = null;
        this.statistic = null;
    }

    public Player(Inventory inventory, Backpack backpack) {
        this.inventory = inventory;
        this.backpack = backpack;
        statistic = ItemFromDatabase.getInstance().getStatistic();
    }

    public void update(Label[] labels, ProgressBar progressBar) {
        inventory.update();
        backpack.update();
        statistic.update(labels, progressBar);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
