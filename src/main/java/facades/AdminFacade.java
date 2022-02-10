package facades;

import beans.Company;
import beans.Customer;
import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import db.db_manager.DBManagerCompanies;
import db.db_manager.DBManagerCustomers;
import dbdao.CompaniesDBDAO;
import dbdao.CouponsDBDAO;
import dbdao.CustomersDBDAO;
import exceptions.AlreadyExistException;
import exceptions.DBDAOException;
import exceptions.NotExistException;
import login.ClientType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminFacade extends ClientFacade {
    private static CompaniesDAO companiesDAO = new CompaniesDBDAO();
    private static CustomersDAO customersDAO = new CustomersDBDAO();
    private static CouponsDAO couponsDAO = new CouponsDBDAO();

    /**
     * Default Constructor - used to create a customer facade after correct login
     */
    public AdminFacade() {

    }

    /**
     * The method checks if the credentials match admin credentials
     *
     * @param email    Email from login entrance
     * @param password Password from login entrance
     * @return boolean that determines if the user is the admin
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * The method allows the admin to add a company to the database,
     * as long as the company's name or email doesn't match an existing company's name or email.
     *
     * @param company Company instance
     * @return boolean that determines if the company was added successfully
     * @throws NotExistException     Invoked if the company does not exist in the database
     * @throws AlreadyExistException Invoked if a company with the same name or email already exists
     * @throws DBDAOException        Invoked if the add action failed
     */
    public boolean addCompany(Company company) throws NotExistException, AlreadyExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getName());
        values.put(2, company.getEmail());
        if (companiesDAO.isExists(DBManagerCompanies.COUNT_COMPANY_BY_NAME_OR_EMAIL, values)) {
            throw new AlreadyExistException(ClientType.COMPANY);
        }
        return companiesDAO.addCompany(company);
    }

    /**
     * The method allows the admin to update a company on the database,
     * if the company's ID and name hasn't changed.
     *
     * @param company Company instance
     * @return boolean that determines if the company was updated successfully
     * @throws NotExistException Invoked if the company does not exist in the database
     * @throws DBDAOException    Invoked if the update action failed
     */
    public boolean updateCompany(Company company) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, company.getId());
        values.put(2, company.getName());
        if (companiesDAO.isExists(DBManagerCompanies.COUNT_COMPANY_BY_ID_AND_NAME, values)) {
            return companiesDAO.updateCompany(company);
        }
        throw new NotExistException(ClientType.COMPANY);
    }

    /**
     * The method allows the admin to delete a company from the database by the company's ID,
     * if the company ID that was entered exists in the database.
     *
     * @param companyID Company's ID
     * @return boolean that determines if the company was deleted successfully
     * @throws NotExistException Invoked if the company does not exist in the database
     * @throws DBDAOException    Invoked if the delete action failed
     */
    public boolean deleteCompany(int companyID) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, companyID);
        if (companiesDAO.isExists(DBManagerCompanies.COUNT_COMPANY_BY_ID, values)) {
            return companiesDAO.deleteCompany(companyID);
        }
        throw new NotExistException(ClientType.COMPANY);
    }

    /**
     * The method gets a list of all the companies in the database
     *
     * @return List of all the companies in the database
     * @throws DBDAOException Invoked if the read action failed
     */
    public List<Company> getAllCompanies() throws DBDAOException {
        return companiesDAO.getAllCompanies();
    }

    /**
     * The method gets a company instance from the database using a company's ID
     *
     * @param companyID Company's ID
     * @return Instance of company from database
     * @throws DBDAOException Invoked if the read action failed
     */
    public Company getOneCompany(int companyID) throws DBDAOException {
        return companiesDAO.getOneCompany(companyID);
    }

    /**
     * The method allows the admin to add a customer to the database,
     * as long as the customer's email doesn't match an existing customer's email.
     *
     * @param customer Customer instance
     * @return boolean that determines if the customer was added successfully
     * @throws NotExistException     Invoked if the customer does not exist in the database
     * @throws AlreadyExistException Invoked if a customer with the same email already exists
     * @throws DBDAOException        Invoked if the add action failed
     */
    public boolean addCustomer(Customer customer) throws NotExistException, DBDAOException, AlreadyExistException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getEmail());
        if (customersDAO.isExists(DBManagerCustomers.COUNT_CUSTOMER_BY_EMAIL, values)) {
            throw new AlreadyExistException(ClientType.CUSTOMER);
        }
        return customersDAO.addCustomer(customer);
    }

    /**
     * The method allows the admin to update a customer in the database,
     * as long as the customer's ID hasn't changed.
     *
     * @param customer Customer instance
     * @return boolean that determines if the customer was updated successfully
     * @throws NotExistException Invoked if the customer does not exist in the database
     * @throws DBDAOException    Invoked if the update action failed
     */
    public boolean updateCustomer(Customer customer) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customer.getId());
        if (customersDAO.isExists(DBManagerCustomers.COUNT_CUSTOMER_BY_ID, values)) {
            return customersDAO.updateCustomer(customer);
        }
        throw new NotExistException(ClientType.CUSTOMER);
    }

    /**
     * The method allows the admin to delete a customer from the database by the customer's ID,
     * if the customer ID that was entered exists in the database.
     *
     * @param customerId Customer's ID
     * @return boolean that determines if the customer was deleted successfully
     * @throws NotExistException Invoked if the customer does not exist in the database
     * @throws DBDAOException    Invoked if the delete action failed
     */
    public boolean deleteCustomer(int customerId) throws NotExistException, DBDAOException {
        Map<Integer, Object> values = new HashMap<>();
        values.put(1, customerId);
        if (customersDAO.isExists(DBManagerCustomers.COUNT_CUSTOMER_BY_ID, values)) {
            return customersDAO.deleteCustomer(customerId);
        }
        throw new NotExistException(ClientType.CUSTOMER);
    }

    /**
     * The method gets a list of all the customers in the database
     *
     * @return List of all the customers in the database
     * @throws DBDAOException Invoked if the read action failed
     */
    public List<Customer> getAllCustomers() throws DBDAOException {
        return customersDAO.getAllCustomers();
    }

    /**
     * The method gets a customer instance from the database using a customer's ID
     *
     * @param customerId Customer's ID
     * @return Instance of customer from database
     * @throws DBDAOException Invoked if the read action failed
     */
    public Customer getOneCustomer(int customerId) throws DBDAOException {
        return customersDAO.getOneCustomer(customerId);
    }

}
