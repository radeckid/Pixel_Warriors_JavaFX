package pixel_warriors.character.staffs.items;

import pixel_warriors.character.Statistic;

import java.sql.SQLException;

public abstract class Armor<T> extends ItemBody<T>
{
    private int physicalDefense;
    private int magicalDefense;

    public Armor(int idItem, String name, String path, ItemType itemType, int physicalDefense, int magicalDefense, String body)
    {
        super(idItem, name, path, itemType, body);
        this.physicalDefense = physicalDefense;
        this.magicalDefense = magicalDefense;
    }

    public Armor(Chest armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), armor.getItemType(), armor.getPathBody());
        this.physicalDefense = armor.getPhysicalDefense();
        this.magicalDefense = armor.getMagicalDefense();
    }

    public void setItem(T item)
    {
        super.setItem(item);
        this.physicalDefense = ((Armor)item).getPhysicalDefense();
        this.magicalDefense = ((Armor)item).getMagicalDefense();
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

    public void addStatistic(Statistic statistic)
    {
        try
        {
            statistic.setMagicalDefense(statistic.getMagicalDefense() + this.magicalDefense);
            statistic.setPhysicalDefense(statistic.getPhysicalDefense() + this.physicalDefense);
        }
        catch (InterruptedException ex)
        {
            System.out.println("Error");
        }
    }
    public void substractStatistic(Statistic statistic)
    {
        try
        {
            statistic.setMagicalDefense(statistic.getMagicalDefense() - this.magicalDefense);
            statistic.setPhysicalDefense(statistic.getPhysicalDefense() - this.physicalDefense);
        }
        catch (InterruptedException ex) {}
    }
}
