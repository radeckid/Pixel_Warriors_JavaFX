package pixel_warriors.character;

import pixel_warriors.character.Attack.Attack;
import pixel_warriors.character.characterlogics.ItemFromDatabase;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;


public abstract class Player implements IPlayerAttackForm
{
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

    public Player(Inventory inventory, Backpack backpack, Statistic statistic) {
        this.inventory = inventory;
        this.backpack = backpack;
        this.statistic = statistic;
    }

    public Player(Statistic statistic)
    {
        //this.inventory = new Inventory(playerCopy.getInventory().getList());
        this.statistic = new Statistic(statistic);
    }

    public void update() {
        inventory.update();
        backpack.update();
        statistic.update();
    }

    public int getAttack(Attack attack)
    {
        return attack.attack();
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
