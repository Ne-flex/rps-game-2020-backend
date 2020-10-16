package rps.game.backend.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertIntoDatabase {
    private final Connection connection;

    public InsertIntoDatabase(Connection connection) {
        this.connection = connection;
    }

    public void insertDecisionIntoDatabase(int gameID, int playerID, String playerDecision) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Games(gameID, playerID, playerDecision, requested) VALUES(?, ?, ?, FALSE)");
            preparedStatement.setInt(1, gameID);
            preparedStatement.setInt(2, playerID);
            preparedStatement.setString(3, playerDecision);

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String[] getDecisionsFromDatabase(int gameID) {
        try {
            String[] decisions = new String[2];
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT playerID, playerDecision FROM Games WHERE gameID = ? ORDER BY playerID");
            preparedStatement.setInt(1, gameID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                decisions[0] = resultSet.getString(2);
                if (resultSet.next()) {
                    decisions[1] = resultSet.getString(2);
                } else {
                    return null;
                }
                return decisions;
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public boolean idsNotPresentInDatabase(int gameID, int playerID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM Games WHERE gameID = ? AND playerID = ?");
            preparedStatement.setInt(1, gameID);
            preparedStatement.setInt(2, playerID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;
            }
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return false;
    }

    public void deleteGame(int gameID){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Games WHERE gameID = ?");
            preparedStatement.setInt(1, gameID);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void addRequested(int gameID, int playerID){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Games SET requested = TRUE WHERE gameID = ? AND playerID = ?");
            preparedStatement.setInt(1, gameID);
            preparedStatement.setInt(2, playerID);
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public boolean requestedAmount(int gameID){
        try{
            int requestChecker = 0;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Games WHERE gameID = ? AND playerID = 1");
            preparedStatement.setInt(1, gameID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getBoolean("requested")){
                    requestChecker++;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            preparedStatement = connection.prepareStatement("SELECT * FROM Games WHERE gameID = ? AND playerID = 2");
            preparedStatement.setInt(1, gameID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if(resultSet.getBoolean("requested")){
                    requestChecker++;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            if(requestChecker == 2){
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            return false;
        }
    }
}
