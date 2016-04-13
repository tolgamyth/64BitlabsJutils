/*
 * Copyright (C) 2014-2016 Tolga Yilmaz
 * info@64bitlabs.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * See LICENSE.txt for details.
 */

package com._64bitlabs.util.database;

/**
 * Created by tolga on 12.04.2016.
 */
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

/**
 * The type Db factory.
 */
public class DBFactory {

    /**
     * The constant unitTestDbUrl.
     */
    private static String unitTestDbUrl;
    /**
     * The constant unitTestDbUser.
     */
    private static String unitTestDbUser;
    /**
     * The constant unitTestDbPass.
     */
    private static String unitTestDbPass;


    /**
     * The constant disabled.
     */
    private static boolean disabled;
    /**
     * The constant suicidal.
     */
    private static boolean suicidal;

    static {
        if(System.getProperty("pamoja.dbfactory.closeOnError") != null) {
            suicidal = true;
        }
    }

    /**
     * Gets read only connection.
     *
     * @return the read only connection
     * @throws SQLException               the sql exception
     * @throws DBPoolUnavailableException the db pool unavailable exception
     */
    public static Connection getReadOnlyConnection() throws SQLException, DBPoolUnavailableException {
        Connection dbc = getConnection();
        dbc.setReadOnly(true);
        dbc.setAutoCommit(false);
        return dbc;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException               the sql exception
     * @throws DBPoolUnavailableException the db pool unavailable exception
     */
    public static Connection getConnection() throws SQLException, DBPoolUnavailableException {
        if(disabled && suicidal) {
            throw new DBPoolUnavailableException("The database pool has failed, please restart the server.", null);
        }

        if (unitTestDbUrl != null) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
            }
            return new TransactionalConnectionWrapper(DriverManager.getConnection(unitTestDbUrl, unitTestDbUser, unitTestDbPass));
        }

        DataSource ds;
        try {
            Context env = (Context) new InitialContext().lookup("java:comp/env");
            ds = (DataSource) env.lookup("jdbc/registry");
        } catch (NamingException e) {
            throw new DBPoolUnavailableException("Database pool could not be found", e);
        }
        return getConnection(ds);
    }

    /**
     * Gets connection.
     *
     * @param ds the ds
     * @return the connection
     * @throws SQLException               the sql exception
     * @throws DBPoolUnavailableException the db pool unavailable exception
     */
    private static Connection getConnection(DataSource ds) throws SQLException, DBPoolUnavailableException {
        try {
            Connection dbc = ds.getConnection();
            TransactionalConnectionWrapper wrapper = new TransactionalConnectionWrapper(dbc);
            wrapper.setReadOnly(false);
            wrapper.setAutoCommit(true);
            return wrapper;
        } catch (Exception e) {
            throw new DBPoolUnavailableException("Low level problem getting a connection from the connection pool", e);
        }
    }

    /**
     * Sets unit test connection strings.
     *
     * @param url  the url
     * @param user the user
     * @param pass the pass
     */
    public static void setUnitTestConnectionStrings(String url, String user, String pass) {
        unitTestDbUrl = url;
        unitTestDbUser = user;
        unitTestDbPass = pass;
    }

    /**
     * Begin transaction.
     *
     * @param dbc       Database transactional connection object
     * @param clientId  the client id
     * @param loginName currently operating Login object name
     * @param ip        the ip
     * @throws SQLException the sql exception
     */
    public static void beginTransaction(Connection dbc, String clientId, String loginName, String ip) throws SQLException {
        PreparedStatement ps = null;

        if (dbc instanceof TransactionalConnectionWrapper) {
            dbc = ((TransactionalConnectionWrapper) dbc).getUnderlyingConnection();
        }

        try {
            dbc.setAutoCommit(false);
            // Should I?  Or shouldn't I?
            dbc.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            if (ip == null) {
                ps = dbc.prepareStatement("insert into audit.master (audit_user, audit_login) values (?, ?)");
            } else {
                ps = dbc.prepareStatement("insert into audit.master (audit_user, audit_login, audit_ip) values (?, ?, ?)");
            }

            ps.setString(1, clientId);
            ps.setString(2, loginName);

            if (ip != null) {
                ps.setString(3, ip);
            }
            ps.executeUpdate();
        } finally {
            JDBCUtil.safeClose(ps);
        }

    }

    /**
     * Commit transaction.
     *
     * @param dbc Database transactional connection object
     * @throws SQLException the sql exception
     */
    public static void commitTransaction(Connection dbc) throws SQLException {

        if (dbc instanceof TransactionalConnectionWrapper) {
            if (!((TransactionalConnectionWrapper) dbc).hasUpdateBeenMade()) {
                dbc.rollback();
            } else {
                dbc.commit();
            }
        } else {
            throw new IllegalStateException("Unexpected connection type");
        }
    }

    /**
     * Rollback transaction.
     *
     * @param dbc Database transactional connection object
     */
    public static void rollbackTransaction(Connection dbc){
        try{
            if(dbc != null) {
                dbc.rollback();
            }
        }catch (SQLException e){
        }
    }

    /**
     * Disables the Database factory.
     * To be used when a problem is detected on startup.  This will ensure that the DB pool is
     * unusable and no configuration can be written or operations can be performed.  The entire
     * application should be restarted to clear this flag.
     */
    public static final void disableDBFactory() {
        disabled = true;
    }
}
