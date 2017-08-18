package com.orasi.utils.database;

import static com.orasi.utils.TestReporter.logDebug;
import static com.orasi.utils.TestReporter.logTrace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.orasi.utils.database.exceptions.DatabaseException;

public abstract class Database {

    protected String driver = null;
    private String dbHost = null;
    private String dbPort = null;
    private String dbService = null;
    private String dbUser = null;
    private String dbPassword = null;
    protected String connectionString = null;

    protected abstract void setDbDriver(String driver);

    protected String getDbDriver() {
        return driver;
    }

    protected void setDbHost(String host) {
        dbHost = host;
    }

    protected String getDbHost() {
        return dbHost;
    }

    protected void setDbPort(String port) {
        dbPort = port;
    }

    protected String getDbPort() {
        return dbPort;
    }

    protected void setDbService(String serivce) {
        dbService = serivce;
    }

    protected String getDbService() {
        return dbService;
    }

    public void setDbUserName(String user) {
        dbUser = user;
    }

    protected String getDbUserName() {
        return dbUser;
    }

    public void setDbPassword(String pass) {
        dbPassword = pass;
    }

    protected String getDbPassword() {
        return dbPassword;
    }

    protected abstract void setDbConnectionString(String connection);

    protected String getDbConnectionString() {
        return connectionString;
    }

    public Object[][] getResultSet(String query) {
        logTrace("Entering Database#getResultSet");
        loadDriver();

        logTrace("Attempt to connect to database [ " + connectionString + " ]");
        try (Connection connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);) {
            logTrace("Connection successful");

            logTrace("Running query");
            logTrace(query);
            try (ResultSet rs = runQuery(connection, query)) {
                logTrace("Query results returned with no errors. Parsing results");
                Object[][] parsedRs = extract(rs);
                logTrace("Exiting Database#getResultSet");
                return parsedRs;
            }
        } catch (Exception e) {
            throw new DatabaseException("Failed to extract data into a Recordset", e);
        }
    }

    private void loadDriver() {
        logTrace("Entering Database#loadDriver");
        try {
            logTrace("Attempting to load driver [ " + driver + " ]");
            Class.forName(driver);
            logTrace("Successfully loaded driver [ " + driver + " ]");
        } catch (ClassNotFoundException cnfe) {
            throw new DatabaseException("Error loading driver", cnfe);
        }
        logTrace("Exiting Database#loadDriver");
    }

    private static ResultSet runQuery(Connection connection, String query) {
        logDebug("Entering Database#runQuery");
        try {
            logDebug("Attempting to create Database Statement object from current connection");
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            logDebug("Successfully created Database Statement");

            logDebug("Attempting to execute query");
            ResultSet resultSet = statement.executeQuery(query);
            logDebug("Successfully executed query");

            logDebug("Exiting Database#runQuery");

            return (resultSet);

        } catch (SQLException sqle) {
            throw new DatabaseException("Failed to execute SQL Query", sqle);
        }
    }

    /**
     * Returns an ArrayList of ArrayLists of Strings extracted from a
     * ResultSet retrieved from the database.
     *
     * @param resultSet
     *            ResultSet to extract Strings from
     * @return an ArrayList of ArrayLists of Strings
     * @throws SQLException
     *             if an SQL exception occurs
     */
    private static Object[][] extract(ResultSet resultSet) {
        logTrace("Entering Database#extract");
        // get row and column count
        int rowCount = 0;
        try {
            logTrace("Determining number of rows in results");
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            logTrace("Rows to to be extracted [ " + rowCount + " ] ");
        } catch (Exception ex) {
            rowCount = 0;
        }
        try {

            logTrace("Determining number of columns in results");
            int columnCount = resultSet.getMetaData().getColumnCount();
            logTrace("Columns to to be extracted [ " + columnCount + " ] ");

            logTrace("Generating Object array for the size of String[" + (rowCount + 1) + "][" + columnCount + "] (One row added for column headers)");
            Object[][] table = new String[rowCount + 1][columnCount];

            logTrace("Retrieve Result Set metadata");
            ResultSetMetaData rsmd = resultSet.getMetaData();

            logTrace("Extacting data from ResultSet and storing in Object[][]");
            for (int rowNum = 0; rowNum <= rowCount; rowNum++) {
                for (int colNum = 0, rsColumn = 1; colNum < columnCount; colNum++, rsColumn++) {

                    if (rowNum == 0) {
                        table[rowNum][colNum] = resultSet.getMetaData().getColumnName(rsColumn);
                    } else if (resultSet.getString(colNum + 1) == null) {
                        // System.out.println("null");
                        table[rowNum][colNum] = "NULL";
                    } else {
                        try {
                            switch (rsmd.getColumnType(rsColumn)) {

                                case Types.DATE:
                                    table[rowNum][colNum] = String.valueOf(resultSet.getTimestamp(rsColumn));
                                    break;

                                case Types.TIMESTAMP:
                                    table[rowNum][colNum] = String.valueOf(resultSet.getTimestamp(rsColumn));
                                    break;

                                case Types.TIME:
                                    table[rowNum][colNum] = resultSet.getTime(rsColumn);
                                    break;

                                default:
                                    table[rowNum][colNum] = resultSet.getString(rsColumn).intern();
                                    break;
                            }
                        } catch (Exception e) {
                            table[rowNum][colNum] = resultSet.getString(rsColumn).intern();
                        }
                    }
                }
                resultSet.next();
            }

            logTrace("Extraction complete");
            logTrace("Exiting Database#extract");
            return table;
        } catch (SQLException sql) {
            throw new DatabaseException("Failed to generate result set", sql);
        }
    }

}
