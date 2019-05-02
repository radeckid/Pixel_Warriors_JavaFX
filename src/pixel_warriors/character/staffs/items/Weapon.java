package pixel_warriors.character.staffs.items;

import pixel_warriors.character.Statistic;

import java.sql.SQLException;

public class Weapon<T> extends ItemBody<T>
{
    int attack;
    public Weapon()
    {

    }

    public Weapon(int idItem, String name, String path, int attack, String pathBody, ItemType itemType)
    {
        super(idItem, name, path, itemType, pathBody);
        this.attack = attack;
    }

    public Weapon(AdditionalWeapon armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), armor.itemType, armor.getPathBody());
        this.attack = armor.getAttack();
    }

    public void setItem(T item)
    {
        super.setItem(item);
        this.attack = ((Weapon)item).getAttack();
    }

    public void validation() throws SQLException
    {
        super.validation();
        if(attack==0) throw  new SQLException();
    }

    public void addStatistic(Statistic statistic)
    {
        try
        {
            statistic.setAttack(statistic.getAttack() + this.attack);
        }
        catch (InterruptedException ex) {}
    }

    public void substractStatistic(Statistic statistic)
    {
        try
        {
            statistic.setAttack(statistic.getAttack() - this.attack);
        }
        catch (InterruptedException ex) {}
    }

    public int getAttack() {return attack;}
    public void setAttack(int attack){this.attack = attack;}
}
