package pixel_warriors.character.staffs.items;

public class Trousers extends Armor <Trousers>
{
    public Trousers(int idItem, String name, String path, int physicalDefense, int magicalDefense, String pathBody)
    {
        super(idItem, name, path, ItemType.Trousers, physicalDefense, magicalDefense, pathBody);
    }

    public Trousers(Trousers armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Trousers, armor.getPhysicalDefense(), armor.getMagicalDefense(),armor.getPathBody());
    }
}
