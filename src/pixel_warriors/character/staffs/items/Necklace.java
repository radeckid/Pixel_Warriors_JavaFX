package pixel_warriors.character.staffs.items;

import pixel_warriors.character.Statistic;

import java.sql.SQLException;

public class Necklace extends Item <Necklace>
{
    int firstBonus;
    TypeFirstBonus typeFirstBonus;
    int secondBonus;
    int thirdBonus;
    int fourthBonus;
    FourthBonusType fourthBonusType;
    public Necklace()
    {

    }

    public Necklace(int idItem, String name, String path, int firstBonus, TypeFirstBonus typeFirstBonus, int secondBonus, int thirdBonus, int fourthBonus, FourthBonusType fourthBonusType)
    {
        super(idItem, name, path, ItemType.Necklaces);
        this.firstBonus = firstBonus;
        this.typeFirstBonus = typeFirstBonus;
        this.secondBonus = secondBonus;
        this.thirdBonus = thirdBonus;
        this.fourthBonus = fourthBonus;
        this.fourthBonusType = fourthBonusType;
    }

    public Necklace(Necklace armor)
    {
        super(armor.getIdItem(), armor.getName(), armor.getPath(), ItemType.Necklaces);
        this.firstBonus = armor.getFirstBonus();
        this.typeFirstBonus = armor.getTypeFirstBonus();
        this.secondBonus = armor.getSecondBonus();
        this.thirdBonus = armor.getThirdBonus();
        this.fourthBonus = armor.getFourthBonus();
        this.fourthBonusType = armor.getFourthBonusType();
    }

    public void setItem(Necklace item)
    {
        super.setItem(item);
        this.firstBonus = item.getFirstBonus();
        this.typeFirstBonus = item.getTypeFirstBonus();
        this.secondBonus = item.getSecondBonus();
        this.thirdBonus = item.getThirdBonus();
        this.fourthBonus = item.getFourthBonus();
        this.fourthBonusType = item.getFourthBonusType();
    }

    public String toString()
    {
        return super.toString() + "First Bonus is " + typeFirstBonus + ": " + firstBonus +
                "\n Second Bonus HP: " + secondBonus +
                "\n Third Bonus Stamina: " + thirdBonus +
                "\n Fourth Bonus is " + fourthBonusType + ": " + fourthBonus + "\n";
    }
    public void validation() throws SQLException
    {
        super.validation();
        if(firstBonus==0 || typeFirstBonus==null || secondBonus==0 || thirdBonus==0 || fourthBonus==0 || fourthBonusType==null) throw new SQLException();
    }

    public void addStatistic(Statistic statistic)
    {
        try
        {
            switch (typeFirstBonus)
            {
                case Agility:
                    statistic.setAgility(statistic.getAgility() + firstBonus);
                    break;
                case Strenght:
                    statistic.setStrength(statistic.getStrength() + firstBonus);
                    break;
            }
            statistic.setHp(statistic.getHp() + this.secondBonus);
            statistic.setMana(statistic.getMana() + this.thirdBonus);
            switch (fourthBonusType)
            {
                case Critical:
                    statistic.setCritical(statistic.getCritical() + fourthBonus);
                    break;
                case DefenseChance:
                    statistic.setDefenseChance(statistic.getDefenseChance() + fourthBonus);
                    break;
            }
        }
        catch (InterruptedException ex) {}
    }

    public void substractStatistic(Statistic statistic)
    {
        try
        {
            switch (typeFirstBonus)
            {
                case Agility:
                    statistic.setAgility(statistic.getAgility() - firstBonus);
                    break;
                case Strenght:
                    statistic.setStrength(statistic.getStrength() - firstBonus);
                    break;
            }
            statistic.setHp(statistic.getHp() - this.secondBonus);
            statistic.setMana(statistic.getMana() - this.thirdBonus);
            switch (fourthBonusType)
            {
                case Critical:
                    statistic.setCritical(statistic.getCritical() - fourthBonus);
                    break;
                case DefenseChance:
                    statistic.setDefenseChance(statistic.getDefenseChance() - fourthBonus);
                    break;
            }
        }
        catch (InterruptedException ex) {}
    }

    public int getFirstBonus() {
        return firstBonus;
    }

    public TypeFirstBonus getTypeFirstBonus() {
        return typeFirstBonus;
    }

    public int getSecondBonus() {
        return secondBonus;
    }

    public int getThirdBonus() {
        return thirdBonus;
    }

    public int getFourthBonus() {
        return fourthBonus;
    }

    public FourthBonusType getFourthBonusType() {
        return fourthBonusType;
    }

    public void setFirstBonus(int firstBonus) {
        this.firstBonus = firstBonus;
    }

    public void setTypeFirstBonus(TypeFirstBonus typeFirstBonus) {
        this.typeFirstBonus = typeFirstBonus;
    }

    public void setSecondBonus(int secondBonus) {
        this.secondBonus = secondBonus;
    }

    public void setThirdBonus(int thirdBonus) {
        this.thirdBonus = thirdBonus;
    }

    public void setFourthBonus(int fourthBonus) {
        this.fourthBonus = fourthBonus;
    }

    public void setFourthBonusType(FourthBonusType fourthBonusType) {
        this.fourthBonusType = fourthBonusType;
    }
}
