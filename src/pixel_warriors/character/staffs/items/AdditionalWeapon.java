package pixel_warriors.character.staffs.items;

import pixel_warriors.character.Statistic;

public class AdditionalWeapon extends Weapon<AdditionalWeapon>
{
    TypeAttackAdditionalWeapons typeAttackAdditionalWeapons;
    public AdditionalWeapon(int idItem, String name, String path, int attack, String pathBody, TypeAttackAdditionalWeapons typeAttackAdditionalWeapons)
    {
        super(idItem, name, path,attack, pathBody, ItemType.AdditionalWeapons);
        this.typeAttackAdditionalWeapons = typeAttackAdditionalWeapons;
    }

    public AdditionalWeapon(AdditionalWeapon armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), armor.getAttack(),armor.getPathBody(), ItemType.AdditionalWeapons);
        this.typeAttackAdditionalWeapons = armor.getTypeAttackAdditionalWeapons();
    }

    public void addStatistic(Statistic statistic)
    {
        try
        {
            switch (typeAttackAdditionalWeapons)
            {
                case Attack:
                    statistic.setAttack(statistic.getAttack() + this.attack);
                    break;
                case Defense:
                    statistic.setPhysicalDefense(statistic.getPhysicalDefense() + this.attack);
                    statistic.setMagicalDefense(statistic.getMagicalDefense() + this.attack);
                    break;
            }
        }
        catch(InterruptedException ex){}
    }

    public void substractStatistic(Statistic statistic)
    {
        try
        {
            switch (typeAttackAdditionalWeapons)
            {
                case Attack:
                    statistic.setAttack(statistic.getAttack() - this.attack);
                    break;
                case Defense:
                    statistic.setPhysicalDefense(statistic.getPhysicalDefense() - this.attack);
                    statistic.setMagicalDefense(statistic.getMagicalDefense() - this.attack);
                    break;
            }
        }
        catch(InterruptedException ex){}
    }

    public TypeAttackAdditionalWeapons getTypeAttackAdditionalWeapons() {
        return typeAttackAdditionalWeapons;
    }

    public void setTypeAttackAdditionalWeapons(TypeAttackAdditionalWeapons typeAttackAdditionalWeapons) {
        this.typeAttackAdditionalWeapons = typeAttackAdditionalWeapons;
    }
}
