package pixel_warriors.missions;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pixel_warriors.connectiondb.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Missions {

    private Connection connection;
    private Quest[] quests = new Quest[3];


    public Missions() {
        connection = ConnectionDB.getInstance().getConnection();
    }

    public Quest getQuests(int i) {
        if (i >= 0 && i <= 2) {
            return quests[0];
        }
        return null;
    }

    public void getMissionsDB(Button[] missionBtn, Label[] missionLabel) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery("Use Pixelwarriors;");

            Quest quest = getMission(stmt, 1);
            missionBtn[0].setText(quest.getTitle() + " (" + quest.getExp() + "exp)");
            missionLabel[0].setText(quest.getDesc() + " (" + quest.getGold() + "r)");
            quests[0] = quest;

            quest = getMission(stmt, 2);
            missionBtn[1].setText(quest.getTitle() + " (" + quest.getExp() + "exp)");
            missionLabel[1].setText(quest.getDesc() + " (" + quest.getGold() + "r)");
            quests[1] = quest;

            quest = getMission(stmt, 3);
            missionBtn[2].setText(quest.getTitle() + " (" + quest.getExp() + "exp)");
            missionLabel[2].setText(quest.getDesc() + " (" + quest.getGold() + "r)");
            quests[2] = quest;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Quest getMission(Statement stmt, int idMission) {
        Quest quest = new Quest();
        try {
            String query = "SELECT Title, Description, Gold, Experience FROM missions WHERE IDMission=" + idMission + ";";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                quest = new Quest(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
                return quest;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        quest.setAll("null", 0);
        return quest;
    }
}
