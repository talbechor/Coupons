package test;

import beans.Category;
import db.db_manager.*;
import exceptions.Action;
import exceptions.SQLTables;
import exceptions.TableNotCreatedException;
import utils.DBTools;

import java.util.HashMap;
import java.util.Map;

public class SchemaTest {
    /**
     * This method is used to test the schema creation, along with all the tables on the schema.
     * The tables are created at a logical order considering foreign keys.
     *
     * @throws TableNotCreatedException A custom exception that is invoked if a table failed to create properly
     */
    public static boolean createSchema() throws TableNotCreatedException {
        if (!DBTools.runQuery(DBManager.CREATE_DB)) {
            throw new TableNotCreatedException(SQLTables.SCHEMA, Action.CREATE);
        }
        if (!createCategoriesTable()) {
            throw new TableNotCreatedException(SQLTables.CATEGORIES, Action.CREATE);
        }
        if (!DBTools.runQuery(DBManagerCompanies.CREATE_COMPANIES_TABLE)) {
            throw new TableNotCreatedException(SQLTables.COMPANIES, Action.CREATE);
        }
        if (!DBTools.runQuery(DBManagerCustomers.CREATE_CUSTOMERS_TABLE)) {
            throw new TableNotCreatedException(SQLTables.CUSTOMERS, Action.CREATE);
        }
        if (!DBTools.runQuery(DBManagerCoupons.CREATE_COUPONS_TABLE)) {
            throw new TableNotCreatedException(SQLTables.COUPONS, Action.CREATE);
        }
        if (!DBTools.runQuery(DBManagerCustomersVsCoupons.CREATE_CUSTOMERS_VS_COUPONS_TABLE)) {
            throw new TableNotCreatedException(SQLTables.CUSTOMERS_VS_COUPONS, Action.CREATE);
        }
        return true;
    }

    /**
     * This method adds categories to the category table at the database according to the Category enum values
     *
     * @throws TableNotCreatedException A custom exception that is invoked if a category has failed to add properly
     */
    public static boolean createCategoriesTable() throws TableNotCreatedException {
        if (!DBTools.runQuery(DBManagerCategories.CREATE_CATEGORIES_TABLE)) {
            throw new TableNotCreatedException(SQLTables.CATEGORIES, Action.CREATE);
        }

        Map<Integer, Object> values = new HashMap<>();
        values.put(1, Category.FOOD.toString());

        if (!DBTools.runQuery(DBManagerCategories.ADD_CATEGORY, values)) {
            throw new TableNotCreatedException(SQLTables.CATEGORY_VALUE, Action.CREATE, Category.FOOD);
        }
        values.clear();
        values.put(1, Category.ELECTRICITY.toString());

        if (!DBTools.runQuery(DBManagerCategories.ADD_CATEGORY, values)) {
            throw new TableNotCreatedException(SQLTables.CATEGORY_VALUE, Action.CREATE, Category.ELECTRICITY);
        }

        values.clear();
        values.put(1, Category.RESTAURANT.toString());

        if (!DBTools.runQuery(DBManagerCategories.ADD_CATEGORY, values)) {
            throw new TableNotCreatedException(SQLTables.CATEGORY_VALUE, Action.CREATE, Category.RESTAURANT);
        }

        values.put(1, Category.VACATION.toString());
        if (!DBTools.runQuery(DBManagerCategories.ADD_CATEGORY, values)) {
            throw new TableNotCreatedException(SQLTables.CATEGORY_VALUE, Action.CREATE, Category.VACATION);
        }
        return true;
    }

    /**
     * This method deletes the schema if it exists and creates a new one clean schema from the Test class.
     *
     * @throws TableNotCreatedException A custom exception invoked if the method failed to drop the schema
     */
    public static void cleanDB() throws TableNotCreatedException {
        if (!DBTools.runQuery(DBManager.DROP_DB)) {
            throw new TableNotCreatedException(SQLTables.SCHEMA, Action.DROP);
        }
        if (SchemaTest.createSchema()) {
            System.out.println("The database has been set!");
        } else {
            System.exit(0);
        }
    }

}
