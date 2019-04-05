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

import pixel_warriors.character.staffs.items.*;
import pixel_warriors.character.characterLogics.*;
import pixel_warriors.character.staffs.Backpack;
import pixel_warriors.character.staffs.Inventory;

public class Controller implements Initializable {

    private LoginDialog loginDialog = new LoginDialog();
    private ObservableList<rankPlayers> observableList;
    private RankPlayerTable rankPlayerTable = new RankPlayerTable();
    Inventory inventory;
    Backpack backpack;

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
    private ImageView weaponOneShowImage, weaponTwoShowImage, headShowImage, chestShowImage, legsShowImage, shoesShowImage;

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

    private Button attackRank, searchButton;
    @FXML
    private TextField searchRankLabel;
    @FXML
    private TableView<rankPlayers> tableRank;
    @FXML
    private TableColumn<rankPlayers, Integer> colNr, colLvl;
    @FXML
    private TableColumn<rankPlayers, String> colNick;

    //Figth panel
    @FXML
    private Button firstAtkBtn, secondAtkBtn, surrBtn;
    @FXML
    private Label playerHP, enemyHP, playerStamina;
    @FXML
    private ProgressBar staminaProgressBar, playerHpProgress, enemyHpProgress;

    //panels
    @FXML
    private AnchorPane banerPaneImage, statsPane, invPane, statsInvPane, tavernPane, rankPane, authorsPane, fightPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        image_quest = new Image(this.getClass().getResource("images/background/tavern_quest.gif").toString());
        image_non_quest = new Image(this.getClass().getResource("images/background/tavern.gif").toString());
        image_speak = new Image(this.getClass().getResource("images/background/tavern_speak.gif").toString());

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

        ImageView[] viewsBackpack = new ImageView[]{slot_1_img, slot_2_img, slot_3_img, slot_4_img, slot_5_img, slot_6_img, slot_7_img, slot_8_img, slot_9_img, slot_10_img, slot_11_img, slot_12_img};
        ImageView[] viewInventory = new ImageView[]{slotHeadImg, slotChestImg, slotLegsImg, slotShoesImg, slotJeweleryImg, slotWeaponOneImg, slotWeaponTwoImg, slotGlovesImg};
        inventory = new Inventory(viewInventory);
        Armor armor = (Armor) new ItemFromDatabase().getItem(ItemType.Armor, 1, "ADMIN12345");
        inventory.find(ItemType.Armor).setItem(new Armor(armor));
        backpack = new Backpack(viewsBackpack);
        MoveItem.update(inventory, backpack);
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

        if (statsPane.isVisible()) {
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
        MoveItem.update(inventory, backpack);
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
        } else if (event.getSource().equals(missionOneBtn)) {
            statsInvPane.setVisible(false);
            tavernPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);
            fightPane.setVisible(true);
        } else if (event.getSource().equals(missionTwoBtn)) {
        } else if (event.getSource().equals(missionThreeBtn)) {
        } else if (event.getSource().equals(surrBtn)) { //TODO chwilowo w celu wizualizacji
            statsInvPane.setVisible(false);
            rankPane.setVisible(false);
            authorsPane.setVisible(false);
            banerPaneImage.setVisible(false);
            fightPane.setVisible(false);
            tavernPane.setVisible(true);
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

