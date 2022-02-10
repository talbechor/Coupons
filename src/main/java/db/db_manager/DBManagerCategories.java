package db.db_manager;

public class DBManagerCategories {

    //Table
    public static final String CREATE_CATEGORIES_TABLE = "CREATE TABLE IF NOT EXISTS `coupons`.`categories` (" +
            "  `id` INT NOT NULL AUTO_INCREMENT," +
            "  `name` VARCHAR(45) NULL," +
            "  PRIMARY KEY (`id`)," +
            "  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);";

    //Create
    public static final String ADD_CATEGORY = "INSERT IGNORE INTO `coupons`.`categories` (`name`) VALUES ( ?)";

    //Update
    public static final String UPDATE_CATEGORY = " UPDATE `coupons`.`categories` SET name=? WHERE id=? ";

    //Delete
    public static final String DELETE_CATEGORY = " DELETE FROM `coupons`.`categories` WHERE id=? ";

    //Read all
    public static final String GET_ALL_CATEGORIES = " SELECT * FROM `coupons`.`categories` ";

}
