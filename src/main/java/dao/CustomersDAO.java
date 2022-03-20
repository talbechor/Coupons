package dao;

import beans.Customer;
import exceptions.DBDAOException;
import exceptions.NotExistException;

import java.util.List;
import java.util.Map;

public interface CustomersDAO {

    boolean isCustomerExists(String email, String password) throws NotExistException;

    boolean isExists(String sql, Map<Integer, Object> values) throws NotExistException;

    boolean addCustomer(Customer customer) throws DBDAOException;

    boolean updateCustomer(Customer customer) throws DBDAOException;

    boolean deleteCustomer(int customerId) throws DBDAOException;

    List<Customer> getAllCustomers(String sql, Map<Integer, Object> values) throws DBDAOException;

    List<Customer> getAllCustomers() throws DBDAOException;

    Customer getOneCustomer(int customerId) throws DBDAOException;

    Customer getOneCustomer(String email, String password) throws DBDAOException;

}
