package pixel_warriors;

import Connection.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RankPlayerTable {

    private ArrayList<rankPlayers> rankPlayersArrayList = new ArrayList<rankPlayers>();
    private Connection connection;
    private ConnectionDB connectionDB;
    private ArrayList<String> playersData = new ArrayList<String>();

    RankPlayerTable() {
        connectionDB = new ConnectionDB("lab_test", "lab", "lab");
        connection = connectionDB.getConnection();
    }


    public void getDataFromDB() throws SQLException {

        String sql = "SELECT Nick, Lvl FROM rank_table ORDER BY Lvl DESC";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        playersData.clear();
        rankPlayersArrayList.clear();

        while (resultSet.next()) {
            playersData.add(resultSet.getString(1));
            playersData.add(resultSet.getString(2));
        }

        for (int i = 0, tmp = 1; i < playersData.size(); i += 2, tmp++) {
            rankPlayersArrayList.add(new rankPlayers(tmp, playersData.get(i), Integer.parseInt(playersData.get(i + 1))));
        }
    }

    public ArrayList<rankPlayers> getRankPlayersArrayList() {
        return rankPlayersArrayList;
    }
}
