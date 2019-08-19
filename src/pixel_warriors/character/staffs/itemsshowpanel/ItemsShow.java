package pixel_warriors.character.staffs.itemsshowpanel;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;
import pixel_warriors.character.staffs.items.*;

public class ItemsShow {

    private Label weaponName, armorName, necklesName, weaponLvl, armorLvl, necklesLvl, upgradeWeaponCost, upgradeArmorCost, upgradeNecklesCost, itemAtk, itemDef, itemAtr_1, itemAtr_2, itemAtr_3, itemAtr_4;
    private AnchorPane weaponStatsPane, necklesStatsPane, armorStatsPane;

    public ItemsShow(Label weaponName, Label armorName, Label necklesName, Label weaponLvl, Label armorLvl, Label necklesLvl, Label upgradeWeaponCost, Label upgradeArmorCost, Label upgradeNecklesCost, Label itemAtk, Label itemDef, Label itemAtr_1, Label itemAtr_2, Label itemAtr_3, Label itemAtr_4, AnchorPane weaponStatsPane, AnchorPane necklesStatsPane, AnchorPane armorStatsPane) {
        this.weaponName = weaponName;
        this.armorName = armorName;
        this.necklesName = necklesName;
        this.weaponLvl = weaponLvl;
        this.armorLvl = armorLvl;
        this.necklesLvl = necklesLvl;
        this.upgradeWeaponCost = upgradeWeaponCost;
        this.upgradeArmorCost = upgradeArmorCost;
        this.upgradeNecklesCost = upgradeNecklesCost;
        this.itemAtk = itemAtk;
        this.itemDef = itemDef;
        this.itemAtr_1 = itemAtr_1;
        this.itemAtr_2 = itemAtr_2;
        this.itemAtr_3 = itemAtr_3;
        this.itemAtr_4 = itemAtr_4;
        this.weaponStatsPane = weaponStatsPane;
        this.necklesStatsPane = necklesStatsPane;
        this.armorStatsPane = armorStatsPane;
    }

    public void showPane(Backpack backpack, int id) {

        if (backpack.getElementById(id).getItem() != null) {

            weaponStatsPane.setVisible(false);
            armorStatsPane.setVisible(false);
            necklesStatsPane.setVisible(false);

            Item tmp = backpack.getElementById(id).getItem();
            Item item = FactoryItem.getItem(tmp);
            makePanels(item);
        }

    }

    public void showPane(Inventory inventory, int id) {

        if (inventory.getElementById(id).getItem() != null) {

            weaponStatsPane.setVisible(false);
            armorStatsPane.setVisible(false);
            necklesStatsPane.setVisible(false);

            Item tmp = inventory.getElementById(id).getItem();
            Item item = FactoryItem.getItem(tmp);
            makePanels(item);
        }

    }

    private void makePanels(Item item) {
        if (item.getItemType().equals(ItemType.Helmets)) {

            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((Helmets) item).getPhysicalDefense()));
            armorStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.Armors)) {

            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((Armor) item).getPhysicalDefense()));
            armorStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.Trousers)) {

            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((Trousers) item).getPhysicalDefense()));
            armorStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.Shoes)) {

            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((Shoes) item).getPhysicalDefense()));
            armorStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.Necklaces)) {

            necklesName.setText(item.getName());
            itemAtr_1.setText(String.valueOf(((Necklace) item).getFirstBonus()));
            itemAtr_2.setText(String.valueOf(((Necklace) item).getSecondBonus()));
            itemAtr_3.setText(String.valueOf(((Necklace) item).getThirdBonus()));
            itemAtr_4.setText(String.valueOf(((Necklace) item).getFourthBonus()));
            necklesStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.MainWeapons)) {

            weaponName.setText(item.getName());
            itemAtk.setText(String.valueOf(((MainWeapon) item).getAttack()));
            weaponStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.AdditionalWeapons)) {

            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((AdditionalWeapon) item).getAttack()));  //TODO tarcza musi miec defa!!!
            armorStatsPane.setVisible(true);

        } else if (item.getItemType().equals(ItemType.Gloves)) {
            armorName.setText(item.getName());
            itemDef.setText(String.valueOf(((Gloves) item).getPhysicalDefense()));
            armorStatsPane.setVisible(true);
        }
    }

}
