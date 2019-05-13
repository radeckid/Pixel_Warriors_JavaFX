package pixel_warriors.character;

import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;


public class Warrior extends Player
{
    public Warrior(Inventory inventory, Backpack backpack) {
        super(inventory, backpack);
    }

    public Warrior(Inventory inventory, Backpack backpack, Statistic statistic)
    {
        super(inventory, backpack, statistic);
    }

    public Warrior(Statistic statistic)
    {
        super(statistic);
    }

    @Override
    public TypeCharacter getTypeCharacter() {
        return TypeCharacter.Warrior;
    }
}
