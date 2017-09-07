package com.orasi.database.databaseImpl;

import com.orasi.database.Database;

public class SQLServerDatabase extends Database {
    public SQLServerDatabase(String host, String port, String dbName) {
        setDbDriver("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        setDbConnectionString("jdbc:microsoft:sqlserver://" + host + ":" + port + ";DatabaseName=" + dbName);
    }

    @Override
    protected void setDbDriver(String driver) {
        super.driver = driver;
    }

    @Override
    protected void setDbConnectionString(String connection) {
        super.connectionString = connection;
    }
}
