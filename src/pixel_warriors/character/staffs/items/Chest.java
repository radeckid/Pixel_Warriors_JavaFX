package pixel_warriors.character.staffs.items;

public class Chest extends Armor <Chest>
{
    public Chest(int idItem, String name, String path, int physicalDefense, int magicalDefense, String pathBody)
    {
        super(idItem, name, path, ItemType.Armors,physicalDefense,magicalDefense, pathBody);
    }

    public Chest(Chest armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Armors, armor.getPhysicalDefense(),armor.getMagicalDefense(), armor.getPathBody());
    }
}
