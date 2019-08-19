package pixel_warriors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import pixel_warriors.character.Player;
import pixel_warriors.character.Statistic;
import pixel_warriors.character.Warrior;
import pixel_warriors.character.characterlogics.ItemFromDatabase;
import pixel_warriors.character.characterlogics.LoadImage;
import pixel_warriors.character.characterlogics.MoveItem;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;
import pixel_warriors.character.staffs.items.ItemType;
import pixel_warriors.character.staffs.items.MainWeapon;
import pixel_warriors.character.staffs.items.WeaponType;
import pixel_warriors.character.staffs.itemsshowpanel.ItemsShow;
import pixel_warriors.character.staffs.slots.EmptySlotType;
import pixel_warriors.character.staffs.slots.SlotBackpack;
import pixel_warriors.character.staffs.slots.SlotInventory;
import pixel_warriors.htmlhelp.ShowRules;
import pixel_warriors.innkeepercharacter.InnkeeperSpeak;
import pixel_warriors.missions.Missions;
import pixel_warriors.missions.Quest;
import pixel_warriors.ranking.RankPlayerTable;
import pixel_warriors.ranking.RankPlayers;

import java.io.File;
import java.sql.SQLException;

public class Controller {

    private LoginDialog loginDialog;
    private ObservableList<RankPlayers> observableList;
    private RankPlayerTable rankPlayerTable = new RankPlayerTable();
    private Warrior player;
    private ItemsShow itemsShow;
    private Figth figth;
    private Missions missions;

    //top bar
    @FXML
    private Button statsBtn, inventoryBtn, tavernBtn, rankingBtn, banerBtn;
    @FXML
    private Label lvl_Indicator, goldLabel;

    //bottom bar
    @FXML
    private Button logoutBtn, authorsBtn, helpBtn;
    @FXML
    private Label userNameLabel;
    @FXML
    private ToggleButton musicBtn;
    @FXML
    private ImageView musicImage;
    private MediaPlayer mediaPlayer;
    private ShowRules showRules;

    //stats and inv panel
    @FXML
    private Label expLabel, strengthLabel, agilityLabel, intligenceLabel, hpLabel, manaLabel, energyLabel, defPhysicLabel, defMagicLabel, criticalLabel, defChanceLabel, atackLabel;
    @FXML
    private ProgressBar expProgress;
    @FXML
    private ListView statisticVarListView, statisticNamesListView;
    private Label[] statisticsLabel;

    @FXML
    private Button weaponOneBtn, weaponTwoBtn, jaweleryBtn, glovesBtn, headBtn, chestBtn, legsBtn, shoesBtn;
    @FXML
    private Button slot_1, slot_2, slot_3, slot_4, slot_5, slot_6, slot_7, slot_8, slot_9, slot_10, slot_11, slot_12;
    @FXML
    private ImageView slot_1_img, slot_2_img, slot_3_img, slot_4_img, slot_5_img, slot_6_img, slot_7_img, slot_8_img, slot_9_img, slot_10_img, slot_11_img, slot_12_img;

    //stats and inv character visual
    @FXML
    ImageView weaponOneShowImage, weaponTwoShowImage, headShowImage, chestShowImage, legsShowImage, shoesShowImage, glovesShowImageR, glovesShowImageL;

    //weared stuff images
    @FXML
    private ImageView slotHeadImg, slotChestImg, slotLegsImg, slotShoesImg, slotJeweleryImg, slotWeaponOneImg, slotWeaponTwoImg, slotGlovesImg;

    //items Stats Panel
    @FXML
    private AnchorPane weaponStatsPane, necklesStatsPane, armorStatsPane;
    @FXML
    private Button weaponStatExitBtn, armorStatExitBtn, neclkesStatExitBtn, upgradeWeaponBtn, upgradeArmorBtn, upgradeNecklesBtn;
    @FXML
    private Label weaponNameLabel, armorNameLabel, necklesNameLabel, weaponLvlLabel, armorLvlLabel, necklesLvlLabel, upgradeWeaponCostLabel, upgradeArmorCostLabel, upgradeNecklesCostLabel, itemAtkLabel, itemDefLabel, itemAtrLabel_1, itemAtrLabel_2, itemAtrLabel_3, itemAtrLabel_4;

    //tavern panel
    @FXML
    private Button innkeeperBtn, missionOneBtn, missionTwoBtn, missionThreeBtn;
    @FXML
    private Label missionOneLabel, missionTwoLabel, missionThreeLabel;
    @FXML
    private ImageView tavernImage, innkeeperMouth;
    private static boolean imageFlag = true;
    private Image image_quest, image_non_quest;
    private InnkeeperSpeak innkeeperSpeak;

    //rank panel
    @FXML
    private Button attackRank, searchButton;
    @FXML
    private TextField searchRankLabel;
    @FXML
    private TableView<RankPlayers> tableRank;
    @FXML
    private TableColumn<RankPlayers, Integer> colNr, colLvl;
    @FXML
    private TableColumn<RankPlayers, String> colNick;

    //panels
    @FXML
    private AnchorPane banerPaneImage, statsPane, invPane, statsInvPane, tavernPane, rankPane, authorsPane, fightPane;

    //fight panel
    @FXML
    private Button firstAtkBtn, secondAtkBtn, thirdAtkBtn, surrBtn;
    @FXML
    private Label weaponAnimLabel, enemyWeaponLabel;
    @FXML
    private ImageView weaponAnimImg, enemyWeaponImg; //TODO ustawiamy img przy rozpoczeciu misji
    @FXML
    private Label playerEnergy, enemyHP, playerHP;
    @FXML
    private ProgressBar playerHpProgress, enemyHpProgress, energyPlayerProgress;
    private MediaPlayer fightMediaplayer;

    //Weared Stuff Fight
    @FXML
    private ImageView weaponOneShowImageFight, weaponTwoShowImageFight, headShowImageFight, chestShowImageFight, legsShowImageFight, shoesShowImageFight, glovesShowImageRFight, glovesShowImageLFight;
    @FXML
    private Label playerDMGIndi, enemyDMGIndi;


    @FXML
    public void initialize() {
        image_quest = new LoadImage("background/tavern_quest.gif", "quest_smoke", EmptySlotType.Body).getImage();
        image_non_quest = new LoadImage("background/tavern.gif", "quest_no", EmptySlotType.Body).getImage();

        //Animacja ruszania ustami taverna
        animTavernBoy();
        innkeeperSpeak = new InnkeeperSpeak(innkeeperMouth);

        //for rank table
        colNr.setCellValueFactory(new PropertyValueFactory<>("PlayerId"));
        colNick.setCellValueFactory(new PropertyValueFactory<>("Nick"));
        colLvl.setCellValueFactory(new PropertyValueFactory<>("Lvl"));

        //load music and play
        String path = "src/pixel_warriors/audio/backgroud/PixelWarriorsMain.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);

        String path2 = "src/pixel_warriors/audio/fight/fightMusic.mp3";
        Media media2 = new Media(new File(path2).toURI().toString());
        fightMediaplayer = new MediaPlayer(media2);
        fightMediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        fightMediaplayer.setVolume(0.1);

        //items form database (lokalizacja do zmiany)
        ImageView[] viewsBackpack = new ImageView[]{slot_1_img, slot_2_img, slot_3_img, slot_4_img, slot_5_img, slot_6_img, slot_7_img, slot_8_img, slot_9_img, slot_10_img, slot_11_img, slot_12_img};
        ImageView[] viewInventory = new ImageView[]{slotHeadImg, slotChestImg, slotLegsImg, slotShoesImg, slotJeweleryImg, slotWeaponOneImg, slotWeaponTwoImg, slotGlovesImg};
        ImageView[] viewBody = new ImageView[]{headShowImage, chestShowImage, legsShowImage, shoesShowImage, weaponOneShowImage, weaponTwoShowImage, glovesShowImageR, glovesShowImageL};


        Inventory inventory = new Inventory(ItemFromDatabase.getInstance().getInventory(), viewInventory, viewBody);
        Backpack backpack = new Backpack(ItemFromDatabase.getInstance().getEquipment(), viewsBackpack);
        player = new Warrior(inventory, backpack);

        statisticsLabel = new Label[]{strengthLabel, agilityLabel, intligenceLabel, hpLabel, manaLabel, energyLabel, defPhysicLabel, defMagicLabel, criticalLabel, defChanceLabel, atackLabel, expLabel};
        player.update();
        player.getStatistic().setStatisticLabels(statisticsLabel, expProgress);
        player.getStatistic().setStatisticTableView(statisticVarListView, expProgress);

//TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
        ObservableList<String> statisticList = FXCollections.<String>observableArrayList("Atak:", "Siła:", "Zwinność:", "Inteligencja:", "Życie:", "Mana:", "Energia:", "Obr.Fizyczna:", "Obr.Magiczna:", "Krytyk:", "Szansa na obronę:");
        statisticNamesListView.setItems(statisticList);
//TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO

        //ItemsStatsPanel
        itemsShow = new ItemsShow(weaponNameLabel, armorNameLabel, necklesNameLabel, weaponLvlLabel, armorLvlLabel, necklesLvlLabel, upgradeWeaponCostLabel, upgradeArmorCostLabel, upgradeNecklesCostLabel, itemAtkLabel, itemDefLabel, itemAtrLabel_1, itemAtrLabel_2, itemAtrLabel_3, itemAtrLabel_4, weaponStatsPane, necklesStatsPane, armorStatsPane);

        //Missions buttons

        Button[] missionBtns = new Button[]{missionOneBtn, missionTwoBtn, missionThreeBtn};
        Label[] missionLabels = new Label[]{missionOneLabel, missionTwoLabel, missionThreeLabel};

        missions = new Missions();
        missions.getMissionsDB(missionBtns, missionLabels);

        //Zasady gry
        showRules = new ShowRules();

    }

    public void setUserNameLabel(String loggedUser) {
        userNameLabel.setText("Zalogowano: " + loggedUser);
    }

    public void setLvl_Indicator(String lvl_Indicator) {
        this.lvl_Indicator.setText(lvl_Indicator);
    }

    @FXML
    void showPanels(ActionEvent event) {

        if (event.getSource().equals(banerBtn)) {
            statsInvPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(true);
        } else if (event.getSource().equals(statsBtn)) {
            invPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);

            if (!statsInvPane.isVisible()) {
                statsInvPane.setVisible(true);
            }

            statsPane.setVisible(true);
        } else if (event.getSource().equals(inventoryBtn)) {
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            statsPane.setVisible(false);
            banerPaneImage.setVisible(false);

            if (!statsInvPane.isVisible()) {
                statsInvPane.setVisible(true);
            }

            invPane.setVisible(true);
        } else if (event.getSource().equals(tavernBtn)) {
            missionOneBtn.setVisible(false);
            missionTwoBtn.setVisible(false);
            missionThreeBtn.setVisible(false);
            missionOneLabel.setVisible(false);
            missionTwoLabel.setVisible(false);
            missionThreeLabel.setVisible(false);
            tavernImage.setImage(image_non_quest);
            innkeeperMouth.setVisible(false);
            imageFlag = true;

            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);
            statsInvPane.setVisible(false);
            tavernPane.setVisible(true);

            innkeeperSpeak.innkeeperSpeach();
        } else if (event.getSource().equals(rankingBtn)) {
            tableRank.getItems().clear();

            try {
                rankPlayerTable.getDataFromDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            observableList = FXCollections.observableArrayList();
            observableList.addAll(rankPlayerTable.getRankPlayersArrayList());

            tableRank.setItems(observableList);
            searchRankLabel.setText("");
            tavernPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);
            statsInvPane.setVisible(false);
            rankPane.setVisible(true);
        } else if (event.getSource().equals(weaponStatExitBtn) || event.getSource().equals(armorStatExitBtn) || event.getSource().equals(neclkesStatExitBtn)) {
            weaponStatsPane.setVisible(false);
            armorStatsPane.setVisible(false);
            necklesStatsPane.setVisible(false);
        }

    }

    @FXML
    void bottomButtons(ActionEvent event) {
        if (event.getSource().equals(logoutBtn)) {
            musicPlay(false);
            loginDialog = new LoginDialog();
            loginDialog.pStage.close();
            userNameLabel.setText("Zalogowano: NULL");
            lvl_Indicator.setText("NULL");
            loginDialog.makeLoginDialog();
        } else if (event.getSource().equals(authorsBtn)) {
            statsInvPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            banerPaneImage.setVisible(false);
            authorsPane.setVisible(true);
        } else if (event.getSource().equals(helpBtn)) {
            showRules.showStage();
        } else if (event.getSource().equals(musicBtn)) {
            if (musicBtn.isSelected()) {
                musicImage.setImage(new Image(this.getClass().getResource("images/etc/music_off.gif").toString()));
                mediaPlayer.setMute(true);
                innkeeperSpeak.setMute(true);
                fightMediaplayer.setMute(true);
            } else {
                musicImage.setImage(new Image(this.getClass().getResource("images/etc/music_on.gif").toString()));
                mediaPlayer.setMute(false);
                innkeeperSpeak.setMute(false);
                fightMediaplayer.setMute(false);
            }
        }


    }

    @FXML
    void inventoryStatsButtons(ActionEvent event) {

        ItemType itemType = ItemType.empty;
        SlotBackpack slotBackpack = null;
        SlotInventory slotInventory = null;
        Object source = event.getSource();
        if (source.equals(headBtn) || source.equals(chestBtn) || source.equals(legsBtn) || source.equals(glovesBtn) || source.equals(shoesBtn) || source.equals(jaweleryBtn) || source.equals(weaponOneBtn) || source.equals(weaponTwoBtn)) {
            if (source.equals(headBtn)) {
                itemType = ItemType.Helmets;
            } else if (source.equals(chestBtn)) {
                itemType = ItemType.Armors;
            } else if (source.equals(legsBtn)) {
                itemType = ItemType.Trousers;
            } else if (source.equals(shoesBtn)) {
                itemType = ItemType.Shoes;
            } else if (source.equals(jaweleryBtn)) {
                itemType = ItemType.Necklaces;
            } else if (source.equals(weaponOneBtn)) {
                itemType = ItemType.MainWeapons;
            } else if (source.equals(weaponTwoBtn)) {
                itemType = ItemType.AdditionalWeapons;
            } else if (source.equals(glovesBtn)) {
                itemType = ItemType.Gloves;
            }
            slotBackpack = player.getBackpack().findFirstEmpty();
            slotInventory = player.getInventory().find(itemType);
            slotBackpack.setItem(MoveItem.takeOffItem(slotInventory, player.getInventory(), player.getStatistic()));
        } else {
            if (event.getSource().equals(slot_1)) {
                slotBackpack = player.getBackpack().find(1);
            } else if (event.getSource().equals(slot_2)) {
                slotBackpack = player.getBackpack().find(2);
            } else if (event.getSource().equals(slot_3)) {
                slotBackpack = player.getBackpack().find(3);
            } else if (event.getSource().equals(slot_4)) {
                slotBackpack = player.getBackpack().find(4);
            } else if (event.getSource().equals(slot_5)) {
                slotBackpack = player.getBackpack().find(5);
            } else if (event.getSource().equals(slot_6)) {
                slotBackpack = player.getBackpack().find(6);
            } else if (event.getSource().equals(slot_7)) {
                slotBackpack = player.getBackpack().find(7);
            } else if (event.getSource().equals(slot_8)) {
                slotBackpack = player.getBackpack().find(8);
            } else if (event.getSource().equals(slot_9)) {
                slotBackpack = player.getBackpack().find(9);
            } else if (event.getSource().equals(slot_10)) {
                slotBackpack = player.getBackpack().find(10);
            } else if (event.getSource().equals(slot_11)) {
                slotBackpack = player.getBackpack().find(11);
            } else if (event.getSource().equals(slot_12)) {
                slotBackpack = player.getBackpack().find(12);
            }
            itemType = slotBackpack.getItemType();
            slotInventory = player.getInventory().find(itemType);
            MoveItem.putOnItem(slotBackpack, player);
        }

        slotBackpack.updateSlot();
        slotInventory.updateSlot();

        if ((player.getInventory().find(ItemType.MainWeapons).getItem()) != null && player.getInventory().find(ItemType.AdditionalWeapons).getItem() != null) {
            if (((MainWeapon) player.getInventory().find(ItemType.MainWeapons).getItem()).getWeaponType() == WeaponType.TwoHanded) {
                SlotBackpack slot = player.getBackpack().findFirstEmpty();
                slot.setItem((MoveItem.takeOffItem(player.getInventory().find(ItemType.AdditionalWeapons), player.getInventory(), player.getStatistic())));
                slot.updateSlot();
                player.getInventory().find(ItemType.AdditionalWeapons).updateSlot();
            }
        }

        ItemFromDatabase.getInstance().updateStatistic(player.getStatistic());
        player.getStatistic().setStatisticLabels(statisticsLabel, expProgress);
        player.getStatistic().setStatisticTableView(statisticVarListView, expProgress); //TODO usunąc to lub linie wyzej (jak wybierzemy panel stat)
        System.out.println(player.getStatistic().toString());
    }

    @FXML
    void itemStatsMouse(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            if (event.getSource().equals(slot_1)) {
                itemsShow.showPane(player.getBackpack(), 0);
            } else if (event.getSource().equals(slot_2)) {
                itemsShow.showPane(player.getBackpack(), 1);
            } else if (event.getSource().equals(slot_3)) {
                itemsShow.showPane(player.getBackpack(), 2);
            } else if (event.getSource().equals(slot_4)) {
                itemsShow.showPane(player.getBackpack(), 3);
            } else if (event.getSource().equals(slot_5)) {
                itemsShow.showPane(player.getBackpack(), 4);
            } else if (event.getSource().equals(slot_6)) {
                itemsShow.showPane(player.getBackpack(), 5);
            } else if (event.getSource().equals(slot_7)) {
                itemsShow.showPane(player.getBackpack(), 6);
            } else if (event.getSource().equals(slot_8)) {
                itemsShow.showPane(player.getBackpack(), 7);
            } else if (event.getSource().equals(slot_9)) {
                itemsShow.showPane(player.getBackpack(), 8);
            } else if (event.getSource().equals(slot_10)) {
                itemsShow.showPane(player.getBackpack(), 9);
            } else if (event.getSource().equals(slot_11)) {
                itemsShow.showPane(player.getBackpack(), 10);
            } else if (event.getSource().equals(slot_12)) {
                itemsShow.showPane(player.getBackpack(), 11);
            } else if (event.getSource().equals(headBtn)) {
                itemsShow.showPane(player.getInventory(), 0);
            } else if (event.getSource().equals(chestBtn)) {
                itemsShow.showPane(player.getInventory(), 1);
            } else if (event.getSource().equals(legsBtn)) {
                itemsShow.showPane(player.getInventory(), 2);
            } else if (event.getSource().equals(shoesBtn)) {
                itemsShow.showPane(player.getInventory(), 3);
            } else if (event.getSource().equals(jaweleryBtn)) {
                itemsShow.showPane(player.getInventory(), 4);
            } else if (event.getSource().equals(weaponOneBtn)) {
                itemsShow.showPane(player.getInventory(), 5);
            } else if (event.getSource().equals(weaponTwoBtn)) {
                itemsShow.showPane(player.getInventory(), 6);
            } else if (event.getSource().equals(glovesBtn)) {
                itemsShow.showPane(player.getInventory(), 7);
            }
        }
    }

    @FXML
    void tavernPanel(ActionEvent event) {
        Statistic statistic = new Statistic(1, 1, 1, 2, 3, 2, 150, 15, 1, 22, 1, 1, 1, 1);
        Statistic statistic2 = new Statistic(3, 1, 1, 15, 12, 13, 150, 90, 1, 20, 15, 12, 10, 11);
        Player opp = new Warrior(statistic2);
        Player playerCurrent = new Warrior(player.getStatistic());
        Quest quest;

        if (event.getSource().equals(innkeeperBtn)) {
            if (imageFlag) {
                missionOneBtn.setVisible(true);
                missionTwoBtn.setVisible(true);
                missionThreeBtn.setVisible(true);
                missionOneLabel.setVisible(true);
                missionTwoLabel.setVisible(true);
                missionThreeLabel.setVisible(true);
                tavernImage.setImage(image_quest);
                imageFlag = false;
            } else if (!imageFlag) {
                missionOneBtn.setVisible(false);
                missionTwoBtn.setVisible(false);
                missionThreeBtn.setVisible(false);
                missionOneLabel.setVisible(false);
                missionTwoLabel.setVisible(false);
                missionThreeLabel.setVisible(false);
                tavernImage.setImage(image_non_quest);
                imageFlag = true;
            }
        } else if (event.getSource().equals(missionOneBtn)) {
            disableButtons(true, true, true, true, true, true, true);
            setPanels(false, false, false, false, false, false, false, true);
            setVisualLook();
            quest = missions.getQuests(0);
            figth = new Figth(playerCurrent, opp, quest.getExp(), quest.getGold(), weaponAnimLabel, enemyWeaponLabel, playerHP, playerHpProgress, playerEnergy, energyPlayerProgress, enemyHP, enemyHpProgress, fightMediaplayer, playerDMGIndi, enemyDMGIndi);
            figth.start();
            mediaPlayer.stop();
        } else if (event.getSource().equals(missionTwoBtn)) {
            disableButtons(true, true, true, true, true, true, true);
            setPanels(false, false, false, false, false, false, false, true);
            setVisualLook();
            quest = missions.getQuests(0);
            figth = new Figth(playerCurrent, opp, quest.getExp(), quest.getGold(), weaponAnimLabel, enemyWeaponLabel, playerHP, playerHpProgress, playerEnergy, energyPlayerProgress, enemyHP, enemyHpProgress, fightMediaplayer, playerDMGIndi, enemyDMGIndi);
            figth.start();
            mediaPlayer.stop();
        } else if (event.getSource().equals(missionThreeBtn)) {
            disableButtons(true, true, true, true, true, true, true);
            setPanels(false, false, false, false, false, false, false, true);
            setVisualLook();
            quest = missions.getQuests(0);
            figth = new Figth(playerCurrent, opp, quest.getExp(), quest.getGold(), weaponAnimLabel, enemyWeaponLabel, playerHP, playerHpProgress, playerEnergy, energyPlayerProgress, enemyHP, enemyHpProgress, fightMediaplayer, playerDMGIndi, enemyDMGIndi);
            figth.start();
            mediaPlayer.stop();
        }
    }

    @FXML
    void fightButtons(ActionEvent event) {
        if (event.getSource().equals(firstAtkBtn)) {
            figth.setLightAttack(true);
        } else if (event.getSource().equals(secondAtkBtn)) {
            figth.setNormalAttack(true);
        } else if (event.getSource().equals(thirdAtkBtn)) {
            figth.setStrongAttack(true);
        } else if (event.getSource().equals(surrBtn)) {
            //TODO zmniejszana stamina dodac
            Main.getController().disableButtons(false, false, false, false, false, false, false);
            Main.getController().setPanels(false, false, false, false, true, false, false, false);

            if (figth.isAlive()) {
                figth.stop();
            }
            fightMediaplayer.stop();
            mediaPlayer.play();
        }
    }

    @FXML
    void rankButtons(ActionEvent event) {
        if (event.getSource().equals(attackRank)) {

        }

        if (event.getSource().equals(searchButton)) {
            String search = searchRankLabel.getText();
            if (isInt(search)) {
                int changeSearch = Integer.parseInt(search) - 1;
                tableRank.scrollTo(changeSearch);
                tableRank.getSelectionModel().select(changeSearch);
            } else {
                int i;
                for (i = 0; i < colNick.getTableView().getItems().size(); i++) {
                    if (observableList.get(i).getNick().equals(search)) {
                        break;
                    }
                }
                tableRank.scrollTo(i);
                tableRank.getSelectionModel().select(i);
            }
            int selected = tableRank.getSelectionModel().getSelectedIndex();
            tableRank.getFocusModel().focus(selected);
        }
    }

    @FXML
    void rankingTableMouse(MouseEvent event) {
        if (event.getClickCount() == 1) {
            try {
                int selected = tableRank.getSelectionModel().getSelectedIndex();
                searchRankLabel.setText(String.valueOf(observableList.get(selected).getNick()));
            } catch (Exception e) {
            }
        }
    }

    @FXML
    void rankingTableEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            try {
                int selected = tableRank.getSelectionModel().getSelectedIndex();
                searchRankLabel.setText(String.valueOf(observableList.get(selected).getNick()));
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    @FXML
    void rankingLabelEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String search = searchRankLabel.getText();
            if (isInt(search)) {
                int changeSearch = Integer.parseInt(search) - 1;
                tableRank.scrollTo(changeSearch);
                tableRank.getSelectionModel().select(changeSearch);
            } else {
                int i;
                for (i = 0; i < colNick.getTableView().getItems().size(); i++) {
                    if (observableList.get(i).getNick().equals(search)) {
                        break;
                    }
                }
                tableRank.scrollTo(i);
                tableRank.getSelectionModel().select(i);
            }
            int selected = tableRank.getSelectionModel().getSelectedIndex();
            tableRank.getFocusModel().focus(selected);
        }
    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private void setVisualLook() {
        weaponOneShowImageFight.setImage(weaponOneShowImage.getImage());
        weaponTwoShowImageFight.setImage(weaponTwoShowImage.getImage());
        headShowImageFight.setImage(headShowImage.getImage());
        chestShowImageFight.setImage(chestShowImage.getImage());
        legsShowImageFight.setImage(legsShowImage.getImage());
        shoesShowImageFight.setImage(shoesShowImage.getImage());
        glovesShowImageRFight.setImage(glovesShowImageR.getImage());
        glovesShowImageLFight.setImage(glovesShowImageL.getImage());

        weaponAnimImg.setImage(weaponOneShowImage.getImage());
    }

    public void setPanels(boolean banerPaneImage, boolean statsPane, boolean invPane, boolean statsInvPane, boolean tavernPane, boolean rankPane, boolean authorsPane, boolean fightPane) {
        this.statsInvPane.setVisible(statsInvPane);
        this.statsPane.setVisible(statsPane);
        this.invPane.setVisible(invPane);

        if (tavernPane) {
            missionOneBtn.setVisible(false);
            missionTwoBtn.setVisible(false);
            missionThreeBtn.setVisible(false);
            missionOneLabel.setVisible(false);
            missionTwoLabel.setVisible(false);
            missionThreeLabel.setVisible(false);
            tavernImage.setImage(image_non_quest);
            innkeeperMouth.setVisible(false);
            imageFlag = true;
        }

        this.tavernPane.setVisible(tavernPane);
        this.rankPane.setVisible(rankPane);
        this.authorsPane.setVisible(authorsPane);
        this.banerPaneImage.setVisible(banerPaneImage);
        this.fightPane.setVisible(fightPane);
    }

    public void disableButtons(boolean banerBtn, boolean statsBtn, boolean inventoryBtn, boolean tavernBtn, boolean rankingBtn, boolean logoutBtn, boolean authorsBtn) {
        this.banerBtn.setDisable(banerBtn);
        this.statsBtn.setDisable(statsBtn);
        this.inventoryBtn.setDisable(inventoryBtn);
        this.tavernBtn.setDisable(tavernBtn);
        this.rankingBtn.setDisable(rankingBtn);
        this.logoutBtn.setDisable(logoutBtn);
        this.authorsBtn.setDisable(authorsBtn);
    }

    public void disableFightButtons(boolean firstBtn, boolean secondBtn, boolean thirdBtn) {
        firstAtkBtn.setDisable(firstBtn);
        secondAtkBtn.setDisable(secondBtn);
        thirdAtkBtn.setDisable(thirdBtn);
    }

    private void animTavernBoy() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (imageFlag) {
                    innkeeperMouth.setVisible(false);
                }
            }
        };
        innkeeperBtn.addEventFilter(MouseEvent.MOUSE_EXITED, eventHandler);
        eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (imageFlag) {
                    innkeeperMouth.setVisible(true);
                }
            }
        };
        innkeeperBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler);
    }

    public static boolean getFlag() {
        return imageFlag;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void musicPlay(boolean play) {
        if (!play) {
            mediaPlayer.stop();
        }
        mediaPlayer.setAutoPlay(play);
    }

}