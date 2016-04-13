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

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * Created by tolga on 12.04.2016.
 */
public class TransactionalConnectionWrapper implements Connection {
    /**
     * The Underlying connection.
     */
    private Connection underlyingConnection;
    /**
     * The Update made.
     */
    private boolean updateMade;
    /**
     * The constant numberOfConnections.
     */
    private static int numberOfConnections ;

    /**
     * Instantiates a new Transactional connection wrapper.
     *
     * @param dbc Database transactional connection object
     */
    public TransactionalConnectionWrapper(Connection dbc) {
        underlyingConnection = dbc;
        incrementConnectionCount();
    }

    /**
     * Sets update made.
     */
    public void setUpdateMade() {
        updateMade = true;
    }

    /**
     * Has update been made boolean.
     *
     * @return the boolean
     */
    public boolean hasUpdateBeenMade() {
        return updateMade;
    }

    /**
     * Gets underlying connection.
     *
     * @return the underlying connection
     */
    public Connection getUnderlyingConnection() {
        return underlyingConnection;
    }

    /**
     * Sets underlying connection.
     *
     * @param underlyingConnection the underlying connection
     */
    public void setUnderlyingConnection(Connection underlyingConnection) {
        this.underlyingConnection = underlyingConnection;
    }

    /**
     * Create statement statement.
     *
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement() throws SQLException {
        Statement st = underlyingConnection.createStatement();
        return new TransactionalStatementWrapper(this, st);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s the s
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Prepare call callable statement.
     *
     * @param s the s
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String s) throws SQLException {
        return underlyingConnection.prepareCall(s);
    }

    /**
     * Native sql string.
     *
     * @param s the s
     * @return the string
     * @throws SQLException the sql exception
     */
    @Override
    public String nativeSQL(String s) throws SQLException {
        return underlyingConnection.nativeSQL(s);
    }

    /**
     * Sets auto commit.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setAutoCommit(boolean b) throws SQLException {
        underlyingConnection.setAutoCommit(b);
    }

    /**
     * Gets auto commit.
     *
     * @return the auto commit
     * @throws SQLException the sql exception
     */
    @Override
    public boolean getAutoCommit() throws SQLException {
        return underlyingConnection.getAutoCommit();
    }

    /**
     * Commit.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void commit() throws SQLException {
        underlyingConnection.commit();
    }

    /**
     * Rollback.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void rollback() throws SQLException {
        underlyingConnection.rollback();
    }

    /**
     * Close.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void close() throws SQLException {
        underlyingConnection.close();
        decrementConnectionCount();
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isClosed() throws SQLException {
        return underlyingConnection.isClosed();
    }

    /**
     * Gets meta data.
     *
     * @return the meta data
     * @throws SQLException the sql exception
     */
    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return underlyingConnection.getMetaData();
    }

    /**
     * Sets read only.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setReadOnly(boolean b) throws SQLException {
        underlyingConnection.setReadOnly(b);
    }

    /**
     * Is read only boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isReadOnly() throws SQLException {
        return underlyingConnection.isReadOnly();
    }

    /**
     * Sets catalog.
     *
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setCatalog(String s) throws SQLException {
        underlyingConnection.setCatalog(s);
    }

    /**
     * Gets catalog.
     *
     * @return the catalog
     * @throws SQLException the sql exception
     */
    @Override
    public String getCatalog() throws SQLException {
        return underlyingConnection.getCatalog();
    }

    /**
     * Sets transaction isolation.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setTransactionIsolation(int i) throws SQLException {
        underlyingConnection.setTransactionIsolation(i);
    }

    /**
     * Gets transaction isolation.
     *
     * @return the transaction isolation
     * @throws SQLException the sql exception
     */
    @Override
    public int getTransactionIsolation() throws SQLException {
        return underlyingConnection.getTransactionIsolation();
    }

    /**
     * Gets warnings.
     *
     * @return the warnings
     * @throws SQLException the sql exception
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return underlyingConnection.getWarnings();
    }

    /**
     * Clear warnings.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearWarnings() throws SQLException {
        underlyingConnection.clearWarnings();
    }

    /**
     * Create statement statement.
     *
     * @param i  the
     * @param i1 the 1
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement(int i, int i1) throws SQLException {
        Statement st = underlyingConnection.createStatement(i, i1);
        return new TransactionalStatementWrapper(this, st);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s  the s
     * @param i  the
     * @param i1 the 1
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s, i, i1);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Prepare call callable statement.
     *
     * @param s  the s
     * @param i  the
     * @param i1 the 1
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1) throws SQLException {
        return underlyingConnection.prepareCall(s, i, i1);
    }

    /**
     * Gets type map.
     *
     * @return the type map
     * @throws SQLException the sql exception
     */
    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return underlyingConnection.getTypeMap();
    }

    /**
     * Sets type map.
     *
     * @param stringClassMap the string class map
     * @throws SQLException the sql exception
     */
    @Override
    public void setTypeMap(Map<String, Class<?>> stringClassMap) throws SQLException {
        underlyingConnection.setTypeMap(stringClassMap);
    }

    /**
     * Sets holdability.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setHoldability(int i) throws SQLException {
        underlyingConnection.setHoldability(i);
    }

    /**
     * Gets holdability.
     *
     * @return the holdability
     * @throws SQLException the sql exception
     */
    @Override
    public int getHoldability() throws SQLException {
        return underlyingConnection.getHoldability();
    }

    /**
     * Sets savepoint.
     *
     * @return the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public Savepoint setSavepoint() throws SQLException {
        return underlyingConnection.setSavepoint();
    }

    /**
     * Sets savepoint.
     *
     * @param s the s
     * @return the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public Savepoint setSavepoint(String s) throws SQLException {
        return underlyingConnection.setSavepoint(s);
    }

    /**
     * Rollback.
     *
     * @param savepoint the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        underlyingConnection.rollback(savepoint);
    }

    /**
     * Release savepoint.
     *
     * @param savepoint the savepoint
     * @throws SQLException the sql exception
     */
    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        underlyingConnection.releaseSavepoint(savepoint);
    }

    /**
     * Create statement statement.
     *
     * @param i  the
     * @param i1 the 1
     * @param i2 the 2
     * @return the statement
     * @throws SQLException the sql exception
     */
    @Override
    public Statement createStatement(int i, int i1, int i2) throws SQLException {
        Statement st = underlyingConnection.createStatement(i, i1, i2);
        return new TransactionalStatementWrapper(this, st);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s  the s
     * @param i  the
     * @param i1 the 1
     * @param i2 the 2
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i, int i1, int i2) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s, i, i1, i2);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Prepare call callable statement.
     *
     * @param s  the s
     * @param i  the
     * @param i1 the 1
     * @param i2 the 2
     * @return the callable statement
     * @throws SQLException the sql exception
     */
    @Override
    public CallableStatement prepareCall(String s, int i, int i1, int i2) throws SQLException {
        return underlyingConnection.prepareCall(s, i, i1, i2);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s the s
     * @param i the
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s, int i) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s, i);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s    the s
     * @param ints the ints
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s, int[] ints) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s, ints);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Prepare statement prepared statement.
     *
     * @param s       the s
     * @param strings the strings
     * @return the prepared statement
     * @throws SQLException the sql exception
     */
    @Override
    public PreparedStatement prepareStatement(String s, String[] strings) throws SQLException {
        PreparedStatement ps = underlyingConnection.prepareStatement(s, strings);
        return new TransactionalPreparedStatementWrapper(this, ps);
    }

    /**
     * Create clob clob.
     *
     * @return the clob
     * @throws SQLException the sql exception
     */
    @Override
    public Clob createClob() throws SQLException {
        return underlyingConnection.createClob();
    }

    /**
     * Create blob blob.
     *
     * @return the blob
     * @throws SQLException the sql exception
     */
    @Override
    public Blob createBlob() throws SQLException {
        return underlyingConnection.createBlob();
    }

    /**
     * Create n clob n clob.
     *
     * @return the n clob
     * @throws SQLException the sql exception
     */
    @Override
    public NClob createNClob() throws SQLException {
        return underlyingConnection.createNClob();
    }

    /**
     * Create sqlxml sqlxml.
     *
     * @return the sqlxml
     * @throws SQLException the sql exception
     */
    @Override
    public SQLXML createSQLXML() throws SQLException {
        return underlyingConnection.createSQLXML();
    }

    /**
     * Is valid boolean.
     *
     * @param i the
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isValid(int i) throws SQLException {
        return underlyingConnection.isValid(i);
    }

    /**
     * Sets client info.
     *
     * @param s  the s
     * @param s1 the s 1
     * @throws SQLClientInfoException the sql client info exception
     */
    @Override
    public void setClientInfo(String s, String s1) throws SQLClientInfoException {
        underlyingConnection.setClientInfo(s, s1);
    }

    /**
     * Sets client info.
     *
     * @param properties the properties
     * @throws SQLClientInfoException the sql client info exception
     */
    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        underlyingConnection.setClientInfo(properties);
    }

    /**
     * Gets client info.
     *
     * @param s the s
     * @return the client info
     * @throws SQLException the sql exception
     */
    @Override
    public String getClientInfo(String s) throws SQLException {
        return underlyingConnection.getClientInfo(s);
    }

    /**
     * Gets client info.
     *
     * @return the client info
     * @throws SQLException the sql exception
     */
    @Override
    public Properties getClientInfo() throws SQLException {
        return underlyingConnection.getClientInfo();
    }

    /**
     * Create array of array.
     *
     * @param s       the s
     * @param objects the objects
     * @return the array
     * @throws SQLException the sql exception
     */
    @Override
    public Array createArrayOf(String s, Object[] objects) throws SQLException {
        return underlyingConnection.createArrayOf(s, objects);
    }

    /**
     * Create struct struct.
     *
     * @param s       the s
     * @param objects the objects
     * @return the struct
     * @throws SQLException the sql exception
     */
    @Override
    public Struct createStruct(String s, Object[] objects) throws SQLException {
        return underlyingConnection.createStruct(s, objects);
    }

    /**
     * Unwrap t.
     *
     * @param <T>    the type parameter
     * @param tClass the t class
     * @return the t
     * @throws SQLException the sql exception
     */
    @Override
    public <T> T unwrap(Class<T> tClass) throws SQLException {
        return underlyingConnection.unwrap(tClass);
    }

    /**
     * Is wrapper for boolean.
     *
     * @param aClass the a class
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isWrapperFor(Class<?> aClass) throws SQLException {
        return underlyingConnection.isWrapperFor(aClass);
    }

    /**
     * Increment connection count.
     */
    private static synchronized void incrementConnectionCount() {
        numberOfConnections++;
        ////System.out.println("XXX - increment - connection count now " + numberOfConnections);
    }

    /**
     * Decrement connection count.
     */
    private static synchronized void decrementConnectionCount() {
        numberOfConnections--;
        ////System.out.println("XXX - decrement - connection count now " + numberOfConnections);
    }

    /**
     * Abort.
     *
     * @param arg0 the arg 0
     * @throws SQLException the sql exception
     */
    @Override
    public void abort(Executor arg0) throws SQLException {
        // TODO Auto-generated method stub

    }

    /**
     * Gets network timeout.
     *
     * @return the network timeout
     * @throws SQLException the sql exception
     */
    @Override
    public int getNetworkTimeout() throws SQLException {
        // TODO Auto-generated method stub
        return 0;
    }

    /**
     * Gets schema.
     *
     * @return the schema
     * @throws SQLException the sql exception
     */
    @Override
    public String getSchema() throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Sets network timeout.
     *
     * @param arg0 the arg 0
     * @param arg1 the arg 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
        // TODO Auto-generated method stub

    }

    /**
     * Sets schema.
     *
     * @param arg0 the arg 0
     * @throws SQLException the sql exception
     */
    @Override
    public void setSchema(String arg0) throws SQLException {
        // TODO Auto-generated method stub

    }
}
