package pixel_warriors.character.staffs.items;

public class Shoes extends Armor <Shoes>
{
    public Shoes(int idItem, String name, String path, int physicalDefense, int magicalDefense, String pathBody)
    {
        super(idItem, name, path, ItemType.Shoes, physicalDefense, magicalDefense, pathBody);
    }

    public Shoes(Shoes armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Shoes, armor.getPhysicalDefense(), armor.getMagicalDefense(),armor.getPathBody());
    }
}
