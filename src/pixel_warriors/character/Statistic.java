package pixel_warriors.character;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import pixel_warriors.character.characterlogics.ItemFromDatabase;

public class Statistic {
    private int level;
    private int gold;
    private int exp;
    private int strength;
    private int agility;
    private int inteligence;
    private int hp;
    private int mana;
    private int stamina;
    private int attack;
    private int physicalDefense;
    private int magicalDefense;
    private int critical;
    private int defenseChance;

    public Statistic(int level, int gold, int exp, int strength, int agility, int inteligence, int hp, int mana, int stamina, int attack, int physicalDefense, int magicalDefense, int critical, int defenseChance) {
        try {
            this.setLevel(level);
            this.setGold(gold);
            this.setExp(exp);
            this.setStrength(strength);
            this.setAgility(agility);
            this.setInteligence(inteligence);
            this.setHp(hp);
            this.setMana(mana);
            this.setStamina(stamina);
            this.setAttack(attack);
            this.setPhysicalDefense(physicalDefense);
            this.setMagicalDefense(magicalDefense);
            this.setCritical(critical);
            this.setDefenseChance(defenseChance);
        } catch (InterruptedException ex) {

        }
    }

    public Statistic(Statistic statistic) {
        try {
            this.setLevel(statistic.getLevel());
            this.setGold(statistic.getGold());
            this.setExp(statistic.getExp());
            this.setStrength(statistic.getStrength());
            this.setAgility(statistic.getAgility());
            this.setInteligence(statistic.getInteligence());
            this.setHp(statistic.getHp());
            this.setMana(statistic.getMana());
            this.setStamina(statistic.getStamina());
            this.setAttack(statistic.getAttack());
            this.setPhysicalDefense(statistic.getPhysicalDefense());
            this.setMagicalDefense(statistic.getMagicalDefense());
            this.setCritical(statistic.getCritical());
            this.setDefenseChance(statistic.getDefenseChance());
        } catch (InterruptedException ex) {

        }
    }

    public String toString() {
        return "Magical: " + this.magicalDefense + "\n" +
                "Physical: " + this.physicalDefense + "\n" +
                "DefenseChance: " + this.defenseChance + "\n" +
                "Critical: " + this.critical + "\n" +
                "Attack: " + this.attack + "\n";
    }

    public void update() {
        ItemFromDatabase.getInstance().updateStatistic(this);
    }

    public void setStatisticLabels(Label[] labels, ProgressBar progressBar) {
        labels[0].setText(String.valueOf(getStrength()));
        labels[1].setText(String.valueOf(getAgility()));
        labels[2].setText(String.valueOf(getInteligence()));
        labels[3].setText(String.valueOf(getHp()));
        labels[4].setText(String.valueOf(getMana()));
        labels[5].setText(String.valueOf(getStamina()));
        labels[6].setText(String.valueOf(getPhysicalDefense()));
        labels[7].setText(String.valueOf(getMagicalDefense()));
        labels[8].setText(String.valueOf(getCritical()));
        labels[9].setText(String.valueOf(getDefenseChance()));
        labels[10].setText(String.valueOf(getAttack()));

        float expToNextLvl = 75 + ((getLevel() * 25) * getLevel());
        labels[11].setText(getExp() + " / " + (int) expToNextLvl);
        progressBar.setProgress(getExp() / expToNextLvl);
    }

    public void setStatisticTableView(ListView listView, ProgressBar progressBar) {
        ObservableList<Integer> statisticList = FXCollections.<Integer>observableArrayList(getAttack(), getStrength(), getAgility(), getInteligence(), getHp(), getMana(), getStamina(), getPhysicalDefense(), getMagicalDefense(), getCritical(), getDefenseChance());
        listView.setItems(statisticList);
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) throws InterruptedException {
        if (level > 0 && level > this.level) {
            this.level = level;
        } else {
            throw new InterruptedException("Wrong given level");
        }
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) throws InterruptedException {
        if (gold >= 0) {
            this.gold = gold;
        } else {
            throw new InterruptedException("Wrong given gold");
        }
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) throws InterruptedException {
        int expToNextLvl = 75 + ((getLevel() * 25) * getLevel());
        if (exp >= 0 && exp >= this.exp) {
            if (exp >= expToNextLvl) {
                this.exp = this.exp - expToNextLvl;
                this.setLevel(this.getLevel() + 1);
            } else {
                this.exp = exp;
            }
        } else {
            throw new InterruptedException("Wrong given exp");
        }
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) throws InterruptedException {
        if (strength >= 0) {
            this.strength = strength;
        } else {
            throw new InterruptedException("Wrong given strength");
        }
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) throws InterruptedException {
        if (agility >= 0) {
            this.agility = agility;
        } else {
            throw new InterruptedException("Wrong given agility");
        }
    }

    public int getInteligence() {
        return inteligence;
    }

    public void setInteligence(int inteligence) throws InterruptedException {
        if (inteligence >= 0) {
            this.inteligence = inteligence;
        } else {
            throw new InterruptedException("Wrong given inteligence");
        }
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) throws InterruptedException {
        if (mana >= 0) {
            this.mana = mana;
        } else {
            throw new InterruptedException("Wrong given mana");
        }
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) throws InterruptedException {
        if (stamina >= 0) {
            this.stamina = stamina;
        } else {
            throw new InterruptedException("Wrong given stamina");
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) throws InterruptedException {
        if (attack >= 0) {
            this.attack = attack;
        } else {
            throw new InterruptedException("Wrong given attack");
        }
    }

    public int getPhysicalDefense() {
        return physicalDefense;
    }

    public void setPhysicalDefense(int physicalDefense) throws InterruptedException {
        if (physicalDefense >= 0) {
            this.physicalDefense = physicalDefense;
        } else {
            throw new InterruptedException("Wrong given physicalDefense");
        }
    }

    public int getMagicalDefense() {
        return magicalDefense;
    }

    public void setMagicalDefense(int magicalDefense) throws InterruptedException {
        if (magicalDefense >= 0) {
            this.magicalDefense = magicalDefense;
        } else {
            throw new InterruptedException("Wrong given magicalDefense");
        }
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) throws InterruptedException {
        if (critical >= 0) {
            this.critical = critical;
        } else {
            throw new InterruptedException("Wrong given critical");
        }
    }

    public int getDefenseChance() {
        return defenseChance;
    }

    public void setDefenseChance(int defenseChance) throws InterruptedException {
        if (defenseChance >= 0) {
            this.defenseChance = defenseChance;
        } else {
            throw new InterruptedException("Wrong given defenseChance");
        }
    }
}
