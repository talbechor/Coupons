package db.connection_pool;

import db.db_manager.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
    //Number of connection to SQL (Maximum is 20, Default is 10)
    private static final int NUMBER_OF_CONNECTIONS = 10;
    private static ConnectionPool instance = null;
    private final Stack<Connection> connections = new Stack<>();

    /**
     * Private constructor of the connection pool.
     * This constructor is used in the getInstance() method, as the ConnectionPool class is a Singleton
     *
     * @throws SQLException An SQLException might occur during the connection between the java program and the database
     */
    private ConnectionPool() throws SQLException {
        System.out.println("A new connection pool instance was created...");
        openAllConnections();
    }

    /**
     * The method creates a pre-determined amount of connections for users, and stores them in the connection stack
     * (This connection pool opens 10 connections)
     *
     * @throws SQLException An SQLException might occur during the connection between the java program and the database
     */
    private void openAllConnections() throws SQLException {
        for (int counter = 0; counter < NUMBER_OF_CONNECTIONS; counter++) {
            //Creates new connection
            Connection connection = DriverManager.getConnection(DBManager.URL, DBManager.SQL_USER, DBManager.SQL_PASSWORD);
            //Pushes the new connection into the connections stack
            connections.push(connection);
        }
    }

    /**
     * The method waits in order to collect all connections back into the connection stack,
     * and then closing the connections by removing all of them from the connection stack
     *
     * @throws InterruptedException wait() method might invoke an InterruptedException
     */
    public void closeAllConnections() throws InterruptedException {
        synchronized (connections) {
            while (connections.size() < NUMBER_OF_CONNECTIONS) {
                connections.wait();
            }
            connections.removeAllElements();
        }
    }

    /**
     * The method checks if an instance of the connection pool has been created.
     * In case the connection pool instance is null, it activates the constructor to create a new instance.
     *
     * @return ConnectionPool instance
     */
    public static ConnectionPool getInstance() {
        //First, check that instance is null
        if (instance == null) {
            //Synchronized-critical code , makes sure that another thread will not pass here
            synchronized (ConnectionPool.class) {
                //Double-check, that no other thread has created an instance
                if (instance == null) {
                    try {
                        instance = new ConnectionPool();
                    } catch (SQLException err) {
                        System.out.println(err.getMessage());
                    }
                }
            }
        }
        return instance;
    }

    /**
     * The method waits until a connection exists in the connection stack, and sends the connection
     * to the required user.
     *
     * @return Connection to user
     * @throws InterruptedException wait() method might invoke an InterruptedException
     */
    public Connection getConnection() throws InterruptedException {
        synchronized (connections) {
            if (connections.isEmpty()) {
                //Wait until we will get a connection back
                connections.wait();
            }
            return connections.pop();
        }
    }

    /**
     * The method receives back a connection from the user, and pushes it to the connection stack
     *
     * @param connection Connection received from user
     */
    public void returnConnection(Connection connection) {
        synchronized (connections) {
            connections.push(connection);
            //Notify that we got back a connection from the user...
            connections.notify();
        }
    }

}
