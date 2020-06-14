/**
 * 
 */
package com.buddi.colombia.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author irfan
 *
 */
public class MySQLJDBCUtility {
	private static Properties properties;
	
	/**
     * Get database properties
     *
     * @return a properties key
	 * @throws IOException 
    */
	
	public static String getDBPropertyValue(String key) throws IOException{
		try {
			FileInputStream file = new FileInputStream("resources/db.properties");
			properties = new Properties();
            properties.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 	
		return properties.getProperty(key);		
	}
	
    /**
     * Get database connection
     *
     * @return a Connection object
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null; 
        try{
        	FileInputStream file = new FileInputStream("resources/db.properties");  
            // load the properties file
            Properties properties = new Properties();
            properties.load(file);
 
            // Assign db parameters
            //String dburl = getDBPropertyValue("dburl");
            String dburl = "jdbc:mysql://"+getDBPropertyValue("dbhostname")+":"+getDBPropertyValue("dbport")+"/"+getDBPropertyValue("dbname");
            String dbuser = getDBPropertyValue("dbuser");
            String dbpassword = getDBPropertyValue("dbpassword");              
            // Create a connection to the database
            connection = DriverManager.getConnection(dburl, dbuser, dbpassword);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
    
    
    /*
     * Get database table row count
     */
    public static int getDBTableRowCount(String dbTableName){
    	Connection connection;
    	
    	int tableRow = 0;
		// create a new connection from MySQLJDBCUtil
		try{
			connection = MySQLJDBCUtility.getConnection();
			// print out a message
			//System.out.println(String.format("Connected to database %s " + "successfully.", connection.getCatalog()));
			Statement statement  = connection.createStatement();
			String sqlQuery = "";
			try {
				dbTableName = getDBPropertyValue("dbtablename");
				sqlQuery = "SELECT * FROM "+ dbTableName;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while (resultSet.next()) {
				tableRow++;
		      }  
			resultSet.close();
			statement.close();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Total rows present in "+dbTableName + " table are: "+tableRow);
		return tableRow;
	}
    
}