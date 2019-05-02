package pixel_warriors.character.staffs.items;

public class Helmets extends Armor <Helmets>
{
    public Helmets(int idItem, String name, String path, int physicalDefense, int magicalDefense, String pathBody)
    {
        super(idItem, name, path, ItemType.Helmets, physicalDefense, magicalDefense, pathBody);
    }

    public Helmets(Helmets armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Helmets, armor.getPhysicalDefense(), armor.getMagicalDefense(),armor.getPathBody());
    }
}
