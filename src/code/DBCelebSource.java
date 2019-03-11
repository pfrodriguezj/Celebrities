package code;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBCelebSource implements ICelebSource{

	private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:./data/test";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    
	public DBCelebSource(String table) {
		try {
			insertWithPreparedStatement(table);
		} catch (SQLException e) {
			System.out.println("Error initializing data in db");
		}
	}



	@Override
	public List<List<Boolean>> readSource(String table) throws SQLException {
		String selectQuery = "select * from " + table + " order by id";
		List<List<Boolean>> population = new ArrayList<>();
		
		PreparedStatement selectPreparedStatement = null;
		Connection connection = getDBConnection();
		selectPreparedStatement = connection.prepareStatement(selectQuery);
		
        ResultSet rs = selectPreparedStatement.executeQuery();
        while (rs.next()) {
            List<Boolean> knowns = new ArrayList<>();
            for(int i=1;i<=10;i++) {
            	knowns.add(rs.getBoolean(i+1));
            }
            population.add(knowns);
            
        }
        selectPreparedStatement.close();
        return population;
	}

    private void insertWithPreparedStatement(String table) throws SQLException {
        Connection connection = getDBConnection();
        PreparedStatement createPreparedStatement = null;
        PreparedStatement insertPreparedStatement = null;
        PreparedStatement selectPreparedStatement = null;

        String createQuery = "CREATE TABLE " +  table + "(id int primary key, k1 boolean, k2 boolean, k3 boolean, k4 boolean, k5 boolean, k6 boolean,k7 boolean, k8 boolean, k9 boolean, k10 boolean)";
        String insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(1,1,1,1,1,1,1,1,1,1,1)";
        String SelectQuery = "select * from PERSON";
        try {
            connection.setAutoCommit(false);
           
            try {
	            createPreparedStatement = connection.prepareStatement(createQuery);
	            createPreparedStatement.executeUpdate();
	            createPreparedStatement.close();
            }catch(SQLException e) {
            	
            }
           
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + " (id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(2,1,0,1,0,1,0,0,1,0,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
                        
            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(3,0,0,1,1,1,1,0,0,1,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(4,0,0,1,0,1,0,0,0,0,0)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(5,0,0,0,0,1,0,0,0,0,0)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(6,1,1,0,0,1,0,0,0,0,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(7,0,1,0,0,1,1,1,1,1,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table  + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(8,0,1,1,1,1,0,1,0,1,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(9,0,1,0,0,1,0,0,1,0,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();

            insertQuery = "INSERT INTO " + table + "(id, k1,k2,k3,k4,k5,k6,k7,k8,k9,k10) values" + "(10,1,0,1,0,1,1,0,1,1,1)";
            insertPreparedStatement = connection.prepareStatement(insertQuery);
            insertPreparedStatement.executeUpdate();
            insertPreparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
	private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,
                    DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
	
	
}
	
