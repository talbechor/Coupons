package db.db_manager;

public class DBManager {

    //Credentials
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String SQL_USER = "root";
    public static final String SQL_PASSWORD = "12345678";

    //Database
    public static final String CREATE_DB = "CREATE SCHEMA IF NOT EXISTS `coupons`";
    public static final String DROP_DB = "DROP SCHEMA `coupons`";

}
