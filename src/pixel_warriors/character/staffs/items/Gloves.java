package pixel_warriors.character.staffs.items;

public class Gloves extends Armor <Gloves>
{
    public Gloves(int idItem, String name, String path, int physicalDefense, int magicalDefense, String pathBody)
    {
        super(idItem, name, path, ItemType.Gloves, physicalDefense, magicalDefense, pathBody);
    }

    public Gloves(Gloves armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Gloves, armor.getPhysicalDefense(), armor.getMagicalDefense(),armor.getPathBody());
    }
}
