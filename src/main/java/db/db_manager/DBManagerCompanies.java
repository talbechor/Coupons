package db.db_manager;

public class DBManagerCompanies {

    //Table
    public static final String CREATE_COMPANIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`companies` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NULL," +
            "  `email` VARCHAR(45) NULL," +
            "  `password` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`))";

    //Create
    public static final String ADD_COMPANY =
            "INSERT INTO `coupons`.`companies` (`name`,`email`,`password` ) VALUES (?,?,?) ";

    //Update
    public static final String UPDATE_COMPANY = " UPDATE `coupons`.`companies` SET email=?,password=? WHERE id=?";

    //Delete
    public static final String DELETE_COMPANY = " DELETE FROM `coupons`.`companies` WHERE id=? ";

    //Read all
    public static final String GET_ALL_COMPANIES = " SELECT * FROM `coupons`.`companies` ";

    //Read by filter
    public static final String GET_COMPANY_BY_ID = " SELECT * FROM `coupons`.`companies` WHERE id=? ";
    public static final String GET_COMPANY_BY_NAME = "  SELECT * FROM  `coupons`.`companies` WHERE name=? ";
    public static final String GET_COMPANY_BY_EMAIL = "  SELECT * FROM  `coupons`.`companies` WHERE email=? ";

    public static final String GET_COMPANY_BY_EMAIL_AND_PASSWORD =
            " SELECT * FROM  `coupons`.`companies` WHERE email=? AND password =? ";

    //Count company
    public static final String COUNT_COMPANY_BY_ID =
            "SELECT count(*) AS counter FROM `coupons`.`companies` WHERE id=? ";
    public static final String COUNT_COMPANY_BY_ID_AND_NAME =
            "SELECT count(*) AS counter FROM `coupons`.`companies` WHERE id=? AND name=?";
    public static final String COUNT_COMPANY_BY_EMAIL_AND_PASSWORD =
            " SELECT count(*) AS counter FROM `coupons`.`companies` WHERE email=? AND password=? ";
    public static final String COUNT_COMPANY_BY_NAME_OR_EMAIL =
            "SELECT count(*) AS counter FROM `coupons`.`companies` WHERE name=? OR email=?";

}
