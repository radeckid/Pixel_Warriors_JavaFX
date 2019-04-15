package pixel_warriors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import pixel_warriors.character.CharacterLogics.ItemFromDatabase;
import pixel_warriors.character.CharacterLogics.LoadImage;
import pixel_warriors.character.CharacterLogics.MoveItem;
import pixel_warriors.character.Staffs.Backpack;
import pixel_warriors.character.Staffs.Inventory;
import pixel_warriors.character.Staffs.Items.ItemType;
import pixel_warriors.character.Staffs.Items.MainWeapon;
import pixel_warriors.character.Staffs.Items.WeaponType;
import pixel_warriors.character.Staffs.Slots.EmptySlotType;
import pixel_warriors.missions.Missions;
import pixel_warriors.ranking.RankPlayerTable;
import pixel_warriors.ranking.rankPlayers;

public class Controller implements Initializable {

    private LoginDialog loginDialog = new LoginDialog();
    private ObservableList<rankPlayers> observableList;
    private RankPlayerTable rankPlayerTable = new RankPlayerTable();
    private Inventory inventory;
    private Backpack backpack;
    private Missions missions = new Missions();

    //top bar
    @FXML
    private Button statsBtn, inventoryBtn, tavernBtn, rankingBtn, banerBtn;
    @FXML
    private Label lvl_Indicator;

    //bottom bar
    @FXML
    private Button logoutBtn, authorsBtn;
    @FXML
    private Label userNameLabel;
    @FXML
    private ToggleButton musicBtn;
    @FXML
    private ImageView musicImage;
    private MediaPlayer mediaPlayer;


    //stats and inv panel
    @FXML
    private Label expLabel, strengthLabel, agilityLabel, intligenceLabel, hpLabel, manaLabel, staminaLabel, physicalLabe, magicLabel, criticalLabel, defChanceLabel;
    @FXML
    private ProgressBar expProgress;

    @FXML
    private Button weaponOneBtn, weaponTwoBtn, jaweleryBtn, glovesBtn, headBtn, chestBtn, legsBtn, shoesBtn;
    @FXML
    private Button slot_1, slot_2, slot_3, slot_4, slot_5, slot_6, slot_7, slot_8, slot_9, slot_10, slot_11, slot_12;
    @FXML
    private ImageView slot_1_img, slot_2_img, slot_3_img, slot_4_img, slot_5_img, slot_6_img, slot_7_img, slot_8_img, slot_9_img, slot_10_img, slot_11_img, slot_12_img;

    //stats and inv character visual
    @FXML
    private ImageView weaponOneShowImage, weaponTwoShowImage, headShowImage, chestShowImage, legsShowImage, shoesShowImage, glovesShowImageL, glovesShowImageR;

    //weared stuff images
    @FXML
    private ImageView slotHeadImg, slotChestImg, slotLegsImg, slotShoesImg, slotJeweleryImg, slotWeaponOneImg, slotWeaponTwoImg, slotGlovesImg;

    //tavern panel
    @FXML
    private Button innkeeperBtn, missionOneBtn, missionTwoBtn, missionThreeBtn;
    @FXML
    private ImageView tavernImage;
    private boolean imageFlag = true;
    private Image image_quest, image_non_quest, image_speak;

    //rank panel
    @FXML
    private Button attackRank, searchButton;
    @FXML
    private TextField searchRankLabel;
    @FXML
    private TableView<rankPlayers> tableRank;
    @FXML
    private TableColumn<rankPlayers, Integer> colNr, colLvl;
    @FXML
    private TableColumn<rankPlayers, String> colNick;

    //panels
    @FXML
    private AnchorPane banerPaneImage, statsPane, invPane, statsInvPane, tavernPane, rankPane, authorsPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image_quest = new LoadImage("background/tavern_quest.gif", "quest_smoke", EmptySlotType.Body).getImage();
        image_non_quest = new LoadImage("background/tavern.gif", "quest_no", EmptySlotType.Body).getImage();
        image_speak = new LoadImage("background/tavern_speak.gif", "quest_speak", EmptySlotType.Body).getImage();

        //Animacja ruszania ustami taverna
        animTavernBoy();

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

        //Items form database (lokalizacja do zmiany \|/ )
        ItemFromDatabase itemFromDatabase = new ItemFromDatabase();
        ImageView[] viewsBackpack = new ImageView[]{slot_1_img, slot_2_img, slot_3_img, slot_4_img, slot_5_img, slot_6_img, slot_7_img, slot_8_img, slot_9_img, slot_10_img, slot_11_img, slot_12_img};
        ImageView[] viewInventory = new ImageView[]{slotHeadImg, slotChestImg, slotLegsImg, slotShoesImg, slotJeweleryImg, slotWeaponOneImg, slotWeaponTwoImg, slotGlovesImg};
        ImageView[] viewBody = new ImageView[]{headShowImage, chestShowImage, legsShowImage, shoesShowImage, weaponOneShowImage, weaponTwoShowImage, glovesShowImageR, glovesShowImageL};


        inventory = new Inventory(itemFromDatabase.getInventory(), viewInventory, viewBody);
        backpack = new Backpack(itemFromDatabase.getEquipment(), viewsBackpack);
        inventory.update(backpack);
        backpack.update();

        //Missions buttons
        Button[] missionBtn = new Button[]{missionOneBtn, missionTwoBtn, missionThreeBtn};
        missions.getMissionsDB(missionBtn);

    }

    public void setUserNameLabel(String userNameLabel) {
        this.userNameLabel.setText(userNameLabel);
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
        }

        if (event.getSource().equals(statsBtn)) {
            invPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);

            if (!statsInvPane.isVisible()) {
                statsInvPane.setVisible(true);
            }

            statsPane.setVisible(true);
        }

        if (event.getSource().equals(inventoryBtn)) {
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            statsPane.setVisible(false);
            banerPaneImage.setVisible(false);

            if (!statsInvPane.isVisible()) {
                statsInvPane.setVisible(true);
            }

            invPane.setVisible(true);
        }

        if (event.getSource().equals(tavernBtn)) {
            missionOneBtn.setVisible(false);
            missionTwoBtn.setVisible(false);
            missionThreeBtn.setVisible(false);
            tavernImage.setImage(image_non_quest);
            imageFlag = true;

            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);
            statsInvPane.setVisible(false);
            tavernPane.setVisible(true);
        }

        if (event.getSource().equals(rankingBtn)) {
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
        }
    }

    @FXML
    void bottomButtons(ActionEvent event) {
        if (event.getSource().equals(logoutBtn)) {
            musicPlay(false);
            loginDialog.pStage.close();
            userNameLabel.setText("Zalogowano: NULL");
            lvl_Indicator.setText("NULL");
            loginDialog.makeLoginDialog();
        }

        if (event.getSource().equals(authorsBtn)) {
            statsInvPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            banerPaneImage.setVisible(false);
            authorsPane.setVisible(true);
        }

        if (event.getSource().equals(musicBtn)) {
            if (musicBtn.isSelected()) {
                musicImage.setImage(new Image(this.getClass().getResource("images/etc/music_off.gif").toString()));
                mediaPlayer.setMute(true);
            } else {
                musicImage.setImage(new Image(this.getClass().getResource("images/etc/music_on.gif").toString()));
                mediaPlayer.setMute(false);
            }
        }

    }

    @FXML
    void inventoryStatsButtons(ActionEvent event) {

        if (!invPane.isVisible()) {
            statsPane.setVisible(false);
            invPane.setVisible(true);
        }

        if (event.getSource().equals(headBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Helmets), inventory));
        } else if (event.getSource().equals(chestBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Armor), inventory));
        } else if (event.getSource().equals(legsBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Trousers), inventory));
        } else if (event.getSource().equals(shoesBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Shoes), inventory));
        } else if (event.getSource().equals(jaweleryBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Necklaces), inventory));
        } else if (event.getSource().equals(weaponOneBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.MainWeapons), inventory));
        } else if (event.getSource().equals(weaponTwoBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.AdditionalWeapons), inventory));
        } else if (event.getSource().equals(glovesBtn)) {
            backpack.findFirstEmpty().setItem(MoveItem.takeOffItem(inventory.find(ItemType.Gloves), inventory));
        } else if (event.getSource().equals(slot_1)) {
            MoveItem.putOnItem(backpack.find(1), inventory, backpack);
        } else if (event.getSource().equals(slot_2)) {
            MoveItem.putOnItem(backpack.find(2), inventory, backpack);
        } else if (event.getSource().equals(slot_3)) {
            MoveItem.putOnItem(backpack.find(3), inventory, backpack);
        } else if (event.getSource().equals(slot_4)) {
            MoveItem.putOnItem(backpack.find(4), inventory, backpack);
        } else if (event.getSource().equals(slot_5)) {
            MoveItem.putOnItem(backpack.find(5), inventory, backpack);
        } else if (event.getSource().equals(slot_6)) {
            MoveItem.putOnItem(backpack.find(6), inventory, backpack);
        } else if (event.getSource().equals(slot_7)) {
            MoveItem.putOnItem(backpack.find(7), inventory, backpack);
        } else if (event.getSource().equals(slot_8)) {
            MoveItem.putOnItem(backpack.find(8), inventory, backpack);
        } else if (event.getSource().equals(slot_9)) {
            MoveItem.putOnItem(backpack.find(9), inventory, backpack);
        } else if (event.getSource().equals(slot_10)) {
            MoveItem.putOnItem(backpack.find(10), inventory, backpack);
        } else if (event.getSource().equals(slot_11)) {
            MoveItem.putOnItem(backpack.find(11), inventory, backpack);
        } else if (event.getSource().equals(slot_12)) {
            MoveItem.putOnItem(backpack.find(12), inventory, backpack);
        }

        if (((MainWeapon) inventory.find(ItemType.MainWeapons).getItem()).getWeaponType() == WeaponType.TwoHanded) {
            backpack.findFirstEmpty().setItem((MoveItem.takeOffItem(inventory.find(ItemType.AdditionalWeapons), inventory)));
        }

        inventory.update(backpack);
        backpack.update();
    }

    @FXML
    void tavernPanel(ActionEvent event) {

        if (event.getSource().equals(innkeeperBtn)) {
            if (imageFlag) {
                missionOneBtn.setVisible(true);
                missionTwoBtn.setVisible(true);
                missionThreeBtn.setVisible(true);
                tavernImage.setImage(image_quest);
                imageFlag = false;
            } else if (!imageFlag) {
                missionOneBtn.setVisible(false);
                missionTwoBtn.setVisible(false);
                missionThreeBtn.setVisible(false);
                tavernImage.setImage(image_speak);
                imageFlag = true;
            }
        }
    }

    private void animTavernBoy() {
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (imageFlag) {
                    tavernImage.setImage(image_non_quest);
                }
            }
        };
        innkeeperBtn.addEventFilter(MouseEvent.MOUSE_EXITED, eventHandler);
        eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                if (imageFlag) {
                    tavernImage.setImage(image_speak);
                }
            }
        };
        innkeeperBtn.addEventFilter(MouseEvent.MOUSE_ENTERED, eventHandler);
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

    public void musicPlay(boolean play) {
        if (!play) {
            mediaPlayer.stop();
        }
        mediaPlayer.setAutoPlay(play);
    }
}

