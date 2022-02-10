package dbdao;

import beans.Customer;
import dao.CustomersDAO;
import db.db_manager.DBManagerCustomers;
import exceptions.Action;
import exceptions.DBDAOException;
import exceptions.NotExistException;
import login.ClientType;
import utils.DBTools;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {
    /**
     * The method receives a customer's login credentials, and checks if there's a customer with matching records
     * in the database.
     *
     * @param email    Customer's email
     * @param password Customer's password
     * @return boolean that determines if results were found.
     * @throws NotExistException A custom exception created to be invoked in case of no matching results.
     */
    @Override
    public boolean isCustomerExists(String email, String password) throws NotExistException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCustomers.COUNT_CUSTOMER_BY_EMAIL_AND_PASSWORD, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt("counter") == 1);
        } catch (Exception err) {
            throw new NotExistException(ClientType.CUSTOMER);

        }

    }

    /**
     * A generic method which receives SQL command and values, and checks if there are matching records
     * in the database.
     *
     * @param sql    SQL Command
     * @param values Map with relevant values
     * @return boolean that determines if results were found.
     * @throws NotExistException A custom exception created to be invoked in case of no matching results.
     */
    @Override
    public boolean isExists(String sql, Map<Integer, Object> values) throws NotExistException {
        ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        try {
            assert resultSet != null;
            resultSet.next();
            return (resultSet.getInt("counter") > 0);
        } catch (Exception err) {
            throw new NotExistException(ClientType.CUSTOMER);
        }

    }

    /**
     * The method receives an instance of a Customer, and adds it to the database in case there is no customer
     * with the same records.
     *
     * @param customer Customer instance
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean addCustomer(Customer customer) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getFirstName());
        values.put(2, customer.getLastName());
        values.put(3, customer.getEmail());
        values.put(4, customer.getPassword());
        if (DBTools.runQuery(DBManagerCustomers.ADD_CUSTOMER, values)) {
            return true;
        }
        throw new DBDAOException(Action.CREATE, ClientType.CUSTOMER);
    }

    /**
     * The method receives an instance of a Customer, and updates its details in the database by the customer ID.
     *
     * @param customer Customer instance
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean updateCustomer(Customer customer) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getFirstName());
        values.put(2, customer.getLastName());
        values.put(3, customer.getEmail());
        values.put(4, customer.getPassword());
        values.put(5, customer.getId());
        if (DBTools.runQuery(DBManagerCustomers.UPDATE_CUSTOMER, values)) {
            return true;
        }
        throw new DBDAOException(Action.UPDATE, ClientType.CUSTOMER);
    }

    /**
     * The method receives an ID of a Customer, and deletes that instance from the database by the received ID.
     *
     * @param customerID Customer's ID
     * @return Boolean that determines if the action was successful.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public boolean deleteCustomer(int customerID) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerID);
        if (DBTools.runQuery(DBManagerCustomers.DELETE_CUSTOMER, values)) {
            return true;
        }
        throw new DBDAOException(Action.DELETE, ClientType.CUSTOMER);
    }

    /**
     * A generic method which receives SQL command and values, and returns a list of customers from the database
     * according to the received values.
     *
     * @param sql    SQL command
     * @param values Map of relevant values
     * @return List of customers from the database according to the received values.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Customer> getAllCustomers(String sql, Map<Integer, Object> values) throws DBDAOException {
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(sql, values);
        Customer customer;
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                customers.add(customer);
            }
            try {
                if (customers.isEmpty()) {
                    throw new NotExistException(ClientType.CUSTOMER);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.CUSTOMER);
        }
        return customers;
    }

    /**
     * The method will return a list of all customers from the database.
     *
     * @return List of all customers from the database.
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public List<Customer> getAllCustomers() throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        List<Customer> customers = new ArrayList<>();
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCustomers.GET_ALL_CUSTOMERS, values);
        Customer customer;
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
                customers.add(customer);
            }
            try {
                if (customers.isEmpty()) {
                    throw new NotExistException(ClientType.CUSTOMER);
                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage() + ", List is empty ");
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.CUSTOMER);
        }
        return customers;
    }

    /**
     * The method receives an ID of a customer, and will return the customer details from the database
     * with the matching ID.
     *
     * @param customerID Customer's ID
     * @return Customer instance from the database with the matching ID, if it exists
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    public Customer getOneCustomer(int customerID) throws DBDAOException {
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCustomers.GET_CUSTOMER_BY_ID, customerID);
        Customer customer = null;
        try {
            assert resultSet != null;
            if (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
            try {
                if (customer == null) {
                    throw new NotExistException(ClientType.CUSTOMER);

                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage());
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.CUSTOMER);
        }
        return customer;
    }

    /**
     * The method receives a customer's login credentials, and will return customer details from the database
     * with the matching records.
     *
     * @param email    Customer's email
     * @param password Customer's password
     * @return Customer instance from the database with the matching credentials
     * @throws DBDAOException A custom exception created to be invoked in case of failure in executing the action.
     */
    @Override
    public Customer getOneCustomer(String email, String password) throws DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, email);
        values.put(2, password);
        ResultSet resultSet = DBTools.runQueryForResult(DBManagerCustomers.GET_CUSTOMERS_BY_EMAIL_AND_PASSWORD, values);
        Customer customer = null;
        try {
            assert resultSet != null;
            while (resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"));
            }
            try {
                if (customer == null) {
                    throw new NotExistException(ClientType.CUSTOMER);

                }
            } catch (NotExistException err) {
                System.out.println(err.getMessage());
            }
        } catch (Exception err) {
            throw new DBDAOException(Action.READ, ClientType.CUSTOMER);
        }
        return customer;
    }

}
