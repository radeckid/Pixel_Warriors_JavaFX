package pixel_warriors;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import pixel_warriors.character.Attack.FactoryAttack;
import pixel_warriors.character.Attack.TypeAttack;
import pixel_warriors.character.Player;
import pixel_warriors.character.characterlogics.ItemFromDatabase;
import pixel_warriors.weaponanimation.Animation;

import java.util.Random;

public class Figth extends Thread {
    private Player player;
    private Player opponent;
    private int experience;
    private int gold;
    private boolean isWin;
    private Animation playerAnim = new Animation(), enemyAnimation = new Animation(), indicatorAnimation = new Animation();
    private Label weaponAnimLabel, enemyWeaponLabel, playerHP, enemyHP, playerEnergy, playerDMGIndi, enemyDMGIndi;
    private ProgressBar playerHpProgress, enemyHpProgress, energyPlayerProgress;

    private Path lightPath = playerAnim.getPath(80, 40, 290, 40, 125, 40, 235, 40),
            normalPath = playerAnim.getPath(70, 40, 290, 40, 125, -20, 235, -20),
            strongPath = playerAnim.getPath(70, 40, 290, 40, 125, -60, 235, -60),

    enemyLightPath = enemyAnimation.getPath(70, 40, -138, 40, 17, 40, -90, 40),
            enemyNormalPath = enemyAnimation.getPath(70, 40, -138, 40, 17, 20, -90, 20),
            enemyStrongPath = enemyAnimation.getPath(70, 40, -138, 40, 17, -40, -90, -40);

    private boolean lightAttack;
    private boolean normalAttack;
    private boolean strongAttack;

    private Random random;

    private MediaPlayer fightMediaplayer;

    public Figth(Player player, Player opponent, int experience, int gold, Label weaponAnimLabel, Label enemyWeaponLabel, Label playerHP, ProgressBar playerHpProgress, Label playerEnergy, ProgressBar energyPlayerProgress, Label enemyHP, ProgressBar enemyHpProgress, MediaPlayer fightMediaplayer, Label playerDMGIndi, Label enemyDMGIndi) {
        this.player = player;
        this.opponent = opponent;
        this.experience = experience;
        this.gold = gold;
        this.weaponAnimLabel = weaponAnimLabel;
        this.enemyWeaponLabel = enemyWeaponLabel;
        this.playerHP = playerHP;
        this.enemyHP = enemyHP;
        this.playerEnergy = playerEnergy;
        this.energyPlayerProgress = energyPlayerProgress;
        this.playerHpProgress = playerHpProgress;
        this.enemyHpProgress = enemyHpProgress;
        this.fightMediaplayer = fightMediaplayer;
        this.playerDMGIndi = playerDMGIndi;
        this.enemyDMGIndi = enemyDMGIndi;

        lightAttack = false;
        normalAttack = false;
        strongAttack = false;
        random = new Random();
    }

    @Override
    public void run() {
        fightMediaplayer.play();
        int att = 0;
        System.out.println("Fight start");
        System.out.println("Player: " + player.getStatistic().getHp());
        System.out.println("Opponent: " + opponent.getStatistic().getHp());

        float playerCurHp = player.getStatistic().getHp(),
                enemyCurHp = opponent.getStatistic().getHp(),
                playerCurEnergy = player.getStatistic().getMana();

        upadateScreen(playerCurHp, playerCurEnergy, enemyCurHp);

        while (true) {

            Main.getController().disableFightButtons(false, false, false);

            while (true) {
                if (this.isLightAttack()) {

                    playerAnim.atkAnim(weaponAnimLabel, 90, 90, 250, lightPath);
                    att = player.getAttack(FactoryAttack.getAttack(TypeAttack.ligthAttack, player, opponent));
                    lightAttack = false;
                    System.out.println("Att: " + att);
                    break;

                } else if (this.isNormalAttack()) {

                    playerAnim.atkAnim(weaponAnimLabel, -50, 60, 500, normalPath);
                    att = player.getAttack(FactoryAttack.getAttack(TypeAttack.normalAttack, player, opponent));
                    normalAttack = false;
                    System.out.println("Att: " + att);
                    break;

                } else if (this.isStrongAttack()) {
                    playerAnim.atkAnim(weaponAnimLabel, -50, 60, 750, strongPath);
                    att = player.getAttack(FactoryAttack.getAttack(TypeAttack.strongAttack, player, opponent));
                    strongAttack = false;
                    System.out.println("Att: " + att);
                    break;
                }
            }

            Main.getController().disableFightButtons(true, true, true);

            opponent.getStatistic().setHp(opponent.getStatistic().getHp() - att);
            System.out.println("Opponent: " + opponent.getStatistic().getHp());
            if (opponent.getStatistic().getHp() <= 0) {
                isWin = true;
                upadateScreen(playerCurHp, playerCurEnergy, enemyCurHp);
                break;
            }

            try {
                Thread.sleep(playerAnim.getDuration());
                upadateScreen(playerCurHp, playerCurEnergy, enemyCurHp);
                setDmgIndicator(enemyDMGIndi, att);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int choice = random.nextInt(3);

            switch (choice) {
                case 0:
                    enemyAnimation.atkAnim(enemyWeaponLabel, -90, -90, 250, enemyLightPath);
                    att = opponent.getAttack(FactoryAttack.getAttack(TypeAttack.ligthAttack, opponent, player));
                    break;
                case 1:
                    enemyAnimation.atkAnim(enemyWeaponLabel, -60, -100, 500, enemyNormalPath);
                    att = opponent.getAttack(FactoryAttack.getAttack(TypeAttack.normalAttack, opponent, player));
                    break;
                case 2:
                    enemyAnimation.atkAnim(enemyWeaponLabel, -45, -130, 750, enemyStrongPath);
                    att = opponent.getAttack(FactoryAttack.getAttack(TypeAttack.strongAttack, opponent, player));
                    break;
            }

            player.getStatistic().setHp(player.getStatistic().getHp() - att);
            System.out.println("Player: " + player.getStatistic().getHp());
            if (player.getStatistic().getHp() <= 0) {
                isWin = false;
                break;
            }
            System.out.println("Att: " + att);

            try {
                Thread.sleep(enemyAnimation.getDuration());
                upadateScreen(playerCurHp, playerCurEnergy, enemyCurHp);
                setDmgIndicator(playerDMGIndi, att);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (isWin) {
            try {
                player.getStatistic().setExp(player.getStatistic().getExp() + this.experience);
                player.getStatistic().setGold(player.getStatistic().getGold() + this.gold);
                ItemFromDatabase.getInstance().saveExpLevelGold(player.getStatistic().getExp(), player.getStatistic().getLevel(), player.getStatistic().getGold(), "ADMIN12345");
                Thread.sleep(4000);
                Main.getController().disableButtons(false, false, false, false, false, false, false);
                Main.getController().setPanels(false, false, false, false, true, false, false, false);
            } catch (InterruptedException ex) {

            }
        }
        fightMediaplayer.stop();
        Main.getController().getMediaPlayer().play();
    }

    public synchronized void setLightAttack(boolean lightAttack) {
        System.out.println("Lightatt");
        this.lightAttack = lightAttack;
    }

    public synchronized void setNormalAttack(boolean normalAttack) {
        this.normalAttack = normalAttack;
    }

    public synchronized void setStrongAttack(boolean strongAttack) {
        this.strongAttack = strongAttack;
    }

    public synchronized boolean isLightAttack() {
        return lightAttack;
    }

    public synchronized boolean isNormalAttack() {
        return normalAttack;
    }

    public synchronized boolean isStrongAttack() {
        return strongAttack;
    }

    private void setDmgIndicator(Label who, int attack) {
        Platform.runLater(() -> {
            if (attack <= 0) {
                who.setTextFill(Color.RED);
                who.setText("-" + attack);
            } else {
                who.setTextFill(Color.DIMGRAY);
                who.setText("BLOK");
            }
            indicatorAnimation.indiDMG(who, attack);
        });
    }

    private void upadateScreen(float playerCurHp, float playerCurEnergy, float enemyCurHp) {
        Platform.runLater(() -> {

            playerHP.setText((int) player.getStatistic().getHp() + "/" + (int) playerCurHp);
            playerHpProgress.setProgress(player.getStatistic().getHp() / playerCurHp);

            if (player.getStatistic().getMana() > playerCurEnergy) {
                playerEnergy.setText((int) playerCurEnergy + "/" + (int) playerCurEnergy);
                try {
                    player.getStatistic().setMana((int) playerCurEnergy);
                } catch (InterruptedException e) {
                }
            } else {
                playerEnergy.setText((int) player.getStatistic().getMana() + "/" + (int) playerCurEnergy);
            }

            energyPlayerProgress.setProgress(player.getStatistic().getMana() / playerCurEnergy);

            enemyHP.setText((int) opponent.getStatistic().getHp() + "/" + (int) enemyCurHp);
            enemyHpProgress.setProgress(opponent.getStatistic().getHp() / enemyCurHp);
        });
    }
}
