package pixel_warriors.ranking;


import pixel_warriors.connectiondb.ConnectionDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RankPlayerTable {

    private ArrayList<RankPlayers> rankPlayersArrayList = new ArrayList<RankPlayers>();
    private Connection connection;
    private ArrayList<String> playersData = new ArrayList<String>();

    public RankPlayerTable() {
        ConnectionDB connectiondb = ConnectionDB.getInstance();
        connection = connectiondb.getConnection();
    }


    public void getDataFromDB() throws SQLException {

        String sql = "SELECT IDPLayer_Statistic, Level FROM statistics ORDER BY RankingPoints DESC";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        playersData.clear();
        rankPlayersArrayList.clear();

        while (resultSet.next()) {
            playersData.add(resultSet.getString(1));
            playersData.add(resultSet.getString(2));
        }

        for (int i = 0, tmp = 1; i < playersData.size(); i += 2, tmp++) {
            rankPlayersArrayList.add(new RankPlayers(tmp, playersData.get(i), Integer.parseInt(playersData.get(i + 1))));
        }
    }

    public ArrayList<RankPlayers> getRankPlayersArrayList() {
        return rankPlayersArrayList;
    }
}
