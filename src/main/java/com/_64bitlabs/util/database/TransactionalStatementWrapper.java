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

/**
 * The type Transactional statement wrapper.
 */
public class TransactionalStatementWrapper implements Statement {

    /**
     * The Connection.
     */
    private TransactionalConnectionWrapper connection;
    /**
     * The Underlying statement.
     */
    private Statement underlyingStatement;

    /**
     * Instantiates a new Transactional statement wrapper.
     *
     * @param dbc Database transactional connection object
     * @param st  the st
     */
    protected TransactionalStatementWrapper(TransactionalConnectionWrapper dbc, Statement st) {
        this.connection = dbc;
        this.underlyingStatement = st;
    }

    /**
     * Execute query result set.
     *
     * @param s the s
     * @return the result set
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet executeQuery(String s) throws SQLException {
        return underlyingStatement.executeQuery(s);
    }

    /**
     * Execute update int.
     *
     * @param s the s
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int executeUpdate(String s) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.executeUpdate(s);
    }

    /**
     * Close.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void close() throws SQLException {
        underlyingStatement.close();
    }

    /**
     * Gets max field size.
     *
     * @return the max field size
     * @throws SQLException the sql exception
     */
    @Override
    public int getMaxFieldSize() throws SQLException {
        return underlyingStatement.getMaxFieldSize();
    }

    /**
     * Sets max field size.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setMaxFieldSize(int i) throws SQLException {
        underlyingStatement.setMaxFieldSize(i);
    }

    /**
     * Gets max rows.
     *
     * @return the max rows
     * @throws SQLException the sql exception
     */
    @Override
    public int getMaxRows() throws SQLException {
        return underlyingStatement.getMaxRows();
    }

    /**
     * Sets max rows.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setMaxRows(int i) throws SQLException {
        underlyingStatement.setMaxRows(i);
    }

    /**
     * Sets escape processing.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setEscapeProcessing(boolean b) throws SQLException {
        underlyingStatement.setEscapeProcessing(b);
    }

    /**
     * Gets query timeout.
     *
     * @return the query timeout
     * @throws SQLException the sql exception
     */
    @Override
    public int getQueryTimeout() throws SQLException {
        return underlyingStatement.getQueryTimeout();
    }

    /**
     * Sets query timeout.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setQueryTimeout(int i) throws SQLException {
        underlyingStatement.setQueryTimeout(i);
    }

    /**
     * Cancel.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void cancel() throws SQLException {
        underlyingStatement.cancel();
    }

    /**
     * Gets warnings.
     *
     * @return the warnings
     * @throws SQLException the sql exception
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return underlyingStatement.getWarnings();
    }

    /**
     * Clear warnings.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearWarnings() throws SQLException {
        underlyingStatement.clearWarnings();
    }

    /**
     * Sets cursor name.
     *
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setCursorName(String s) throws SQLException {
        underlyingStatement.setCursorName(s);
    }

    /**
     * Execute boolean.
     *
     * @param s the s
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean execute(String s) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.execute(s);
    }

    /**
     * Gets result set.
     *
     * @return the result set
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet getResultSet() throws SQLException {
        return underlyingStatement.getResultSet();
    }

    /**
     * Gets update count.
     *
     * @return the update count
     * @throws SQLException the sql exception
     */
    @Override
    public int getUpdateCount() throws SQLException {
        return underlyingStatement.getUpdateCount();
    }

    /**
     * Gets more results.
     *
     * @return the more results
     * @throws SQLException the sql exception
     */
    @Override
    public boolean getMoreResults() throws SQLException {
        return underlyingStatement.getMoreResults();
    }

    /**
     * Sets fetch direction.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setFetchDirection(int i) throws SQLException {
        underlyingStatement.setFetchDirection(i);
    }

    /**
     * Gets fetch direction.
     *
     * @return the fetch direction
     * @throws SQLException the sql exception
     */
    @Override
    public int getFetchDirection() throws SQLException {
        return underlyingStatement.getFetchDirection();
    }

    /**
     * Sets fetch size.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setFetchSize(int i) throws SQLException {
        underlyingStatement.setFetchSize(i);
    }

    /**
     * Gets fetch size.
     *
     * @return the fetch size
     * @throws SQLException the sql exception
     */
    @Override
    public int getFetchSize() throws SQLException {
        return underlyingStatement.getFetchSize();
    }

    /**
     * Gets result set concurrency.
     *
     * @return the result set concurrency
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetConcurrency() throws SQLException {
        return underlyingStatement.getResultSetConcurrency();
    }

    /**
     * Gets result set type.
     *
     * @return the result set type
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetType() throws SQLException {
        return underlyingStatement.getResultSetType();
    }

    /**
     * Add batch.
     *
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void addBatch(String s) throws SQLException {
        underlyingStatement.addBatch(s);
    }

    /**
     * Clear batch.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearBatch() throws SQLException {
        underlyingStatement.clearBatch();
    }

    /**
     * Execute batch int [ ].
     *
     * @return the int [ ]
     * @throws SQLException the sql exception
     */
    @Override
    public int[] executeBatch() throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.executeBatch();
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    @Override
    public Connection getConnection() throws SQLException {
        return connection;
    }

    /**
     * Gets more results.
     *
     * @param i the
     * @return the more results
     * @throws SQLException the sql exception
     */
    @Override
    public boolean getMoreResults(int i) throws SQLException {
        return underlyingStatement.getMoreResults(i);
    }

    /**
     * Gets generated keys.
     *
     * @return the generated keys
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return underlyingStatement.getGeneratedKeys();
    }

    /**
     * Execute update int.
     *
     * @param s the s
     * @param i the
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int executeUpdate(String s, int i) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.executeUpdate(s, i);
    }

    /**
     * Execute update int.
     *
     * @param s    the s
     * @param ints the ints
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int executeUpdate(String s, int[] ints) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.executeUpdate(s, ints);
    }

    /**
     * Execute update int.
     *
     * @param s       the s
     * @param strings the strings
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int executeUpdate(String s, String[] strings) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.executeUpdate(s, strings);
    }

    /**
     * Execute boolean.
     *
     * @param s the s
     * @param i the
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean execute(String s, int i) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.execute(s, i);
    }

    /**
     * Execute boolean.
     *
     * @param s    the s
     * @param ints the ints
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean execute(String s, int[] ints) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.execute(s, ints);
    }

    /**
     * Execute boolean.
     *
     * @param s       the s
     * @param strings the strings
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean execute(String s, String[] strings) throws SQLException {
        connection.setUpdateMade();
        return underlyingStatement.execute(s, strings);
    }

    /**
     * Gets result set holdability.
     *
     * @return the result set holdability
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetHoldability() throws SQLException {
        return underlyingStatement.getResultSetHoldability();
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isClosed() throws SQLException {
        return underlyingStatement.isClosed();
    }

    /**
     * Sets poolable.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setPoolable(boolean b) throws SQLException {
        underlyingStatement.setPoolable(b);
    }

    /**
     * Is poolable boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isPoolable() throws SQLException {
        return underlyingStatement.isPoolable();
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
        return underlyingStatement.unwrap(tClass);
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
        return underlyingStatement.isWrapperFor(aClass);
    }

    /**
     * Close on completion.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void closeOnCompletion() throws SQLException {
        // TODO Auto-generated method stub

    }

    /**
     * Is close on completion boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}

