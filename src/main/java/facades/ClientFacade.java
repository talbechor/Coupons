package facades;

import dao.CompaniesDAO;
import dao.CouponsDAO;
import dao.CustomersDAO;
import exceptions.DBDAOException;
import exceptions.NotExistException;

public abstract class ClientFacade {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDAO couponsDAO;

    public boolean login(String email, String password) throws NotExistException, DBDAOException {
        return false;
    }

}
