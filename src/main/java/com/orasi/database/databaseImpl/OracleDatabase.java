package com.orasi.database.databaseImpl;

import com.orasi.database.Database;
import com.orasi.utils.Constants;

public class OracleDatabase extends Database {

    public OracleDatabase(String tnsName) {
        super.driver = "oracle.jdbc.driver.OracleDriver";
        super.connectionString = "jdbc:oracle:thin:@" + tnsName.toUpperCase();
    }

    public OracleDatabase(String host, String port, String sid) {
        super.driver = "oracle.jdbc.driver.OracleDriver";
        super.connectionString = "jdbc:oracle:thin:@" + host + ":" + port + ":"
                + sid;
    }
}
