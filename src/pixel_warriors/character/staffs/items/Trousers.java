package pixel_warriors.character.Staffs.Items;

import java.sql.SQLException;

public class Trousers extends Item <Trousers>
{
    private int physicalDefense;
    private int magicalDefense;
    public Trousers()
    {

    }
    public Trousers(int idItem, String name, String path, int physicalDefense, int magicalDefense)
    {
        super(idItem, name, path, ItemType.Trousers);
        this.physicalDefense = physicalDefense;
        this.magicalDefense = magicalDefense;
    }

    public Trousers(Trousers armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Trousers);
        this.physicalDefense = armor.getPhysicalDefense();
        this.magicalDefense = armor.getMagicalDefense();
    }

    public void setItem(Trousers item)
    {
        super.setItem(item);
        this.physicalDefense = item.getPhysicalDefense();
        this.magicalDefense = item.getMagicalDefense();
    }

    public String toString()
    {
        return super.toString() + "Physical defense: " + physicalDefense + "\nMagical defense: " + magicalDefense;
    }

    public void validation() throws SQLException
    {
        super.validation();
        if(physicalDefense==0 || magicalDefense==0) throw new SQLException();
    }

    public void setPhysicalDefense(int physicalDefense) { this.physicalDefense = physicalDefense;}

    public int getPhysicalDefense(){ return physicalDefense;}

    public void setMagicalDefense(int magicalDefense) { this.magicalDefense = magicalDefense;}

    public int getMagicalDefense(){ return magicalDefense;}
}
