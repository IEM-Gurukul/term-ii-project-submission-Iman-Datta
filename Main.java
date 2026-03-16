import java.sql.Connection;

import database.*;
class Main{
    public static void main(String[] args){


    Connection conn = DBConnection.getConnection();

    if (conn != null) {
        System.out.println("Connected to SQLite successfully!");
    }
    }
}