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

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;

/**
 * The type Transactional prepared statement wrapper.
 */
public class TransactionalPreparedStatementWrapper implements PreparedStatement {

    /**
     * The Connection.
     */
    private TransactionalConnectionWrapper connection;
    /**
     * The Underlying prepared statement.
     */
    private PreparedStatement underlyingPreparedStatement;

    /**
     * Instantiates a new Transactional prepared statement wrapper.
     *
     * @param dbc Database transactional connection object
     * @param ps  the ps
     */
    protected TransactionalPreparedStatementWrapper(TransactionalConnectionWrapper dbc, PreparedStatement ps) {
        this.connection = dbc;
        this.underlyingPreparedStatement = ps;
    }

    /**
     * Execute query result set.
     *
     * @return the result set
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet executeQuery() throws SQLException {
        return underlyingPreparedStatement.executeQuery();
    }

    /**
     * Execute update int.
     *
     * @return the int
     * @throws SQLException the sql exception
     */
    @Override
    public int executeUpdate() throws SQLException {
        connection.setUpdateMade();
        return underlyingPreparedStatement.executeUpdate();
    }

    /**
     * Sets null.
     *
     * @param i  the
     * @param i1 the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setNull(int i, int i1) throws SQLException {
        underlyingPreparedStatement.setNull(i, i1);
    }

    /**
     * Sets boolean.
     *
     * @param i the
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setBoolean(int i, boolean b) throws SQLException {
        underlyingPreparedStatement.setBoolean(i, b);
    }

    /**
     * Sets byte.
     *
     * @param i the
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setByte(int i, byte b) throws SQLException {
        underlyingPreparedStatement.setByte(i, b);
    }

    /**
     * Sets short.
     *
     * @param i the
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setShort(int i, short s) throws SQLException {
        underlyingPreparedStatement.setShort(i, s);
    }

    /**
     * Sets int.
     *
     * @param i  the
     * @param i1 the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setInt(int i, int i1) throws SQLException {
        underlyingPreparedStatement.setInt(i, i1);
    }

    /**
     * Sets long.
     *
     * @param i the
     * @param l currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setLong(int i, long l) throws SQLException {
        underlyingPreparedStatement.setLong(i, l);
    }

    /**
     * Sets float.
     *
     * @param i the
     * @param v the v
     * @throws SQLException the sql exception
     */
    @Override
    public void setFloat(int i, float v) throws SQLException {
        underlyingPreparedStatement.setFloat(i, v);
    }

    /**
     * Sets double.
     *
     * @param i the
     * @param v the v
     * @throws SQLException the sql exception
     */
    @Override
    public void setDouble(int i, double v) throws SQLException {
        underlyingPreparedStatement.setDouble(i, v);
    }

    /**
     * Sets big decimal.
     *
     * @param i          the
     * @param bigDecimal the big decimal
     * @throws SQLException the sql exception
     */
    @Override
    public void setBigDecimal(int i, BigDecimal bigDecimal) throws SQLException {
        underlyingPreparedStatement.setBigDecimal(i, bigDecimal);
    }

    /**
     * Sets string.
     *
     * @param i the
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setString(int i, String s) throws SQLException {
        underlyingPreparedStatement.setString(i, s);
    }

    /**
     * Sets bytes.
     *
     * @param i     the
     * @param bytes the bytes
     * @throws SQLException the sql exception
     */
    @Override
    public void setBytes(int i, byte[] bytes) throws SQLException {
        underlyingPreparedStatement.setBytes(i, bytes);
    }

    /**
     * Sets date.
     *
     * @param i    the
     * @param date the date
     * @throws SQLException the sql exception
     */
    @Override
    public void setDate(int i, Date date) throws SQLException {
        underlyingPreparedStatement.setDate(i, date);
    }

    /**
     * Sets time.
     *
     * @param i    the
     * @param time the time
     * @throws SQLException the sql exception
     */
    @Override
    public void setTime(int i, Time time) throws SQLException {
        underlyingPreparedStatement.setTime(i, time);
    }

    /**
     * Sets timestamp.
     *
     * @param i         the
     * @param timestamp the timestamp
     * @throws SQLException the sql exception
     */
    @Override
    public void setTimestamp(int i, Timestamp timestamp) throws SQLException {
        underlyingPreparedStatement.setTimestamp(i, timestamp);
    }

    /**
     * Sets ascii stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param i1          the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setAsciiStream(int i, InputStream inputStream, int i1) throws SQLException {
        underlyingPreparedStatement.setAsciiStream(i, inputStream, i1);
    }

    /**
     * Sets unicode stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param i1          the 1
     * @throws SQLException the sql exception
     */
    @SuppressWarnings("deprecated")
    @Override
    public void setUnicodeStream(int i, InputStream inputStream, int i1) throws SQLException {
        underlyingPreparedStatement.setUnicodeStream(i, inputStream, i1);
    }

    /**
     * Sets binary stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param i1          the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setBinaryStream(int i, InputStream inputStream, int i1) throws SQLException {
        underlyingPreparedStatement.setBinaryStream(i, inputStream, i1);
    }

    /**
     * Clear parameters.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearParameters() throws SQLException {
        underlyingPreparedStatement.clearParameters();
    }

    /**
     * Sets object.
     *
     * @param i  the
     * @param o  the o
     * @param i1 the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setObject(int i, Object o, int i1) throws SQLException {
        underlyingPreparedStatement.setObject(i, o, i1);
    }

    /**
     * Sets object.
     *
     * @param i the
     * @param o the o
     * @throws SQLException the sql exception
     */
    @Override
    public void setObject(int i, Object o) throws SQLException {
        underlyingPreparedStatement.setObject(i, o);
    }

    /**
     * Execute boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean execute() throws SQLException {
        connection.setUpdateMade();
        return underlyingPreparedStatement.execute();
    }

    /**
     * Add batch.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void addBatch() throws SQLException {
        underlyingPreparedStatement.addBatch();
    }

    /**
     * Sets character stream.
     *
     * @param i      the
     * @param reader the reader
     * @param i1     the 1
     * @throws SQLException the sql exception
     */
    @Override
    public void setCharacterStream(int i, Reader reader, int i1) throws SQLException {
        underlyingPreparedStatement.setCharacterStream(i, reader, i1);
    }

    /**
     * Sets ref.
     *
     * @param i   the
     * @param ref the ref
     * @throws SQLException the sql exception
     */
    @Override
    public void setRef(int i, Ref ref) throws SQLException {
        underlyingPreparedStatement.setRef(i, ref);
    }

    /**
     * Sets blob.
     *
     * @param i    the
     * @param blob the blob
     * @throws SQLException the sql exception
     */
    @Override
    public void setBlob(int i, Blob blob) throws SQLException {
        underlyingPreparedStatement.setBlob(i, blob);
    }

    /**
     * Sets clob.
     *
     * @param i    the
     * @param clob the clob
     * @throws SQLException the sql exception
     */
    @Override
    public void setClob(int i, Clob clob) throws SQLException {
        underlyingPreparedStatement.setClob(i, clob);
    }

    /**
     * Sets array.
     *
     * @param i     the
     * @param array the array
     * @throws SQLException the sql exception
     */
    @Override
    public void setArray(int i, Array array) throws SQLException {
        underlyingPreparedStatement.setArray(i, array);
    }

    /**
     * Gets meta data.
     *
     * @return the meta data
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return underlyingPreparedStatement.getMetaData();
    }

    /**
     * Sets date.
     *
     * @param i        the
     * @param date     the date
     * @param calendar the calendar
     * @throws SQLException the sql exception
     */
    @Override
    public void setDate(int i, Date date, Calendar calendar) throws SQLException {
        underlyingPreparedStatement.setDate(i, date, calendar);
    }

    /**
     * Sets time.
     *
     * @param i        the
     * @param time     the time
     * @param calendar the calendar
     * @throws SQLException the sql exception
     */
    @Override
    public void setTime(int i, Time time, Calendar calendar) throws SQLException {
        underlyingPreparedStatement.setTime(i, time, calendar);
    }

    /**
     * Sets timestamp.
     *
     * @param i         the
     * @param timestamp the timestamp
     * @param calendar  the calendar
     * @throws SQLException the sql exception
     */
    @Override
    public void setTimestamp(int i, Timestamp timestamp, Calendar calendar) throws SQLException {
        underlyingPreparedStatement.setTimestamp(i, timestamp, calendar);
    }

    /**
     * Sets null.
     *
     * @param i  the
     * @param i1 the 1
     * @param s  the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setNull(int i, int i1, String s) throws SQLException {
        underlyingPreparedStatement.setNull(i, i1, s);
    }

    /**
     * Sets url.
     *
     * @param i   the
     * @param url the url
     * @throws SQLException the sql exception
     */
    @Override
    public void setURL(int i, URL url) throws SQLException {
        underlyingPreparedStatement.setURL(i, url);
    }

    /**
     * Gets parameter meta data.
     *
     * @return the parameter meta data
     * @throws SQLException the sql exception
     */
    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        return underlyingPreparedStatement.getParameterMetaData();
    }

    /**
     * Sets row id.
     *
     * @param i     the
     * @param rowId the row id
     * @throws SQLException the sql exception
     */
    @Override
    public void setRowId(int i, RowId rowId) throws SQLException {
        underlyingPreparedStatement.setRowId(i, rowId);
    }

    /**
     * Sets n string.
     *
     * @param i the
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setNString(int i, String s) throws SQLException {
        underlyingPreparedStatement.setNString(i, s);
    }

    /**
     * Sets n character stream.
     *
     * @param i      the
     * @param reader the reader
     * @param l      currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setNCharacterStream(int i, Reader reader, long l) throws SQLException {
        underlyingPreparedStatement.setNCharacterStream(i, reader, l);
    }

    /**
     * Sets n clob.
     *
     * @param i     the
     * @param nClob the n clob
     * @throws SQLException the sql exception
     */
    @Override
    public void setNClob(int i, NClob nClob) throws SQLException {
        underlyingPreparedStatement.setNClob(i, nClob);
    }

    /**
     * Sets clob.
     *
     * @param i      the
     * @param reader the reader
     * @param l      currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setClob(int i, Reader reader, long l) throws SQLException {
        underlyingPreparedStatement.setClob(i, reader, l);
    }

    /**
     * Sets blob.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param l           currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setBlob(int i, InputStream inputStream, long l) throws SQLException {
        underlyingPreparedStatement.setBlob(i, inputStream, l);
    }

    /**
     * Sets n clob.
     *
     * @param i      the
     * @param reader the reader
     * @param l      currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setNClob(int i, Reader reader, long l) throws SQLException {
        underlyingPreparedStatement.setNClob(i, reader, l);
    }

    /**
     * Sets sqlxml.
     *
     * @param i      the
     * @param sqlxml the sqlxml
     * @throws SQLException the sql exception
     */
    @Override
    public void setSQLXML(int i, SQLXML sqlxml) throws SQLException {
        underlyingPreparedStatement.setSQLXML(i, sqlxml);
    }

    /**
     * Sets object.
     *
     * @param i  the
     * @param o  the o
     * @param i1 the 1
     * @param i2 the 2
     * @throws SQLException the sql exception
     */
    @Override
    public void setObject(int i, Object o, int i1, int i2) throws SQLException {
        underlyingPreparedStatement.setObject(i, o, i1, i2);
    }

    /**
     * Sets ascii stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param l           currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setAsciiStream(int i, InputStream inputStream, long l) throws SQLException {
        underlyingPreparedStatement.setAsciiStream(i, inputStream, l);
    }

    /**
     * Sets binary stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @param l           currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setBinaryStream(int i, InputStream inputStream, long l) throws SQLException {
        underlyingPreparedStatement.setBinaryStream(i, inputStream, l);
    }

    /**
     * Sets character stream.
     *
     * @param i      the
     * @param reader the reader
     * @param l      currently operating login
     * @throws SQLException the sql exception
     */
    @Override
    public void setCharacterStream(int i, Reader reader, long l) throws SQLException {
        underlyingPreparedStatement.setCharacterStream(i, reader, l);
    }

    /**
     * Sets ascii stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @throws SQLException the sql exception
     */
    @Override
    public void setAsciiStream(int i, InputStream inputStream) throws SQLException {
        underlyingPreparedStatement.setAsciiStream(i, inputStream);
    }

    /**
     * Sets binary stream.
     *
     * @param i           the
     * @param inputStream the input stream
     * @throws SQLException the sql exception
     */
    @Override
    public void setBinaryStream(int i, InputStream inputStream) throws SQLException {
        underlyingPreparedStatement.setBinaryStream(i, inputStream);
    }

    /**
     * Sets character stream.
     *
     * @param i      the
     * @param reader the reader
     * @throws SQLException the sql exception
     */
    @Override
    public void setCharacterStream(int i, Reader reader) throws SQLException {
        underlyingPreparedStatement.setCharacterStream(i, reader);
    }

    /**
     * Sets n character stream.
     *
     * @param i      the
     * @param reader the reader
     * @throws SQLException the sql exception
     */
    @Override
    public void setNCharacterStream(int i, Reader reader) throws SQLException {
        underlyingPreparedStatement.setNCharacterStream(i, reader);
    }

    /**
     * Sets clob.
     *
     * @param i      the
     * @param reader the reader
     * @throws SQLException the sql exception
     */
    @Override
    public void setClob(int i, Reader reader) throws SQLException {
        underlyingPreparedStatement.setClob(i, reader);
    }

    /**
     * Sets blob.
     *
     * @param i           the
     * @param inputStream the input stream
     * @throws SQLException the sql exception
     */
    @Override
    public void setBlob(int i, InputStream inputStream) throws SQLException {
        underlyingPreparedStatement.setBlob(i, inputStream);
    }

    /**
     * Sets n clob.
     *
     * @param i      the
     * @param reader the reader
     * @throws SQLException the sql exception
     */
    @Override
    public void setNClob(int i, Reader reader) throws SQLException {
        underlyingPreparedStatement.setNClob(i, reader);
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
        return underlyingPreparedStatement.executeQuery(s);
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
        return underlyingPreparedStatement.executeUpdate(s);
    }

    /**
     * Close.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void close() throws SQLException {
        underlyingPreparedStatement.close();
    }

    /**
     * Gets max field size.
     *
     * @return the max field size
     * @throws SQLException the sql exception
     */
    @Override
    public int getMaxFieldSize() throws SQLException {
        return underlyingPreparedStatement.getMaxFieldSize();
    }

    /**
     * Sets max field size.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setMaxFieldSize(int i) throws SQLException {
        underlyingPreparedStatement.setMaxFieldSize(i);
    }

    /**
     * Gets max rows.
     *
     * @return the max rows
     * @throws SQLException the sql exception
     */
    @Override
    public int getMaxRows() throws SQLException {
        return underlyingPreparedStatement.getMaxRows();
    }

    /**
     * Sets max rows.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setMaxRows(int i) throws SQLException {
        underlyingPreparedStatement.setMaxRows(i);
    }

    /**
     * Sets escape processing.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setEscapeProcessing(boolean b) throws SQLException {
        underlyingPreparedStatement.setEscapeProcessing(b);
    }

    /**
     * Gets query timeout.
     *
     * @return the query timeout
     * @throws SQLException the sql exception
     */
    @Override
    public int getQueryTimeout() throws SQLException {
        return underlyingPreparedStatement.getQueryTimeout();
    }

    /**
     * Sets query timeout.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setQueryTimeout(int i) throws SQLException {
        underlyingPreparedStatement.setQueryTimeout(i);
    }

    /**
     * Cancel.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void cancel() throws SQLException {
        underlyingPreparedStatement.cancel();
    }

    /**
     * Gets warnings.
     *
     * @return the warnings
     * @throws SQLException the sql exception
     */
    @Override
    public SQLWarning getWarnings() throws SQLException {
        return underlyingPreparedStatement.getWarnings();
    }

    /**
     * Clear warnings.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearWarnings() throws SQLException {
        underlyingPreparedStatement.clearWarnings();
    }

    /**
     * Sets cursor name.
     *
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void setCursorName(String s) throws SQLException {
        underlyingPreparedStatement.setCursorName(s);
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
        return underlyingPreparedStatement.execute(s);
    }

    /**
     * Gets result set.
     *
     * @return the result set
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet getResultSet() throws SQLException {
        return underlyingPreparedStatement.getResultSet();
    }

    /**
     * Gets update count.
     *
     * @return the update count
     * @throws SQLException the sql exception
     */
    @Override
    public int getUpdateCount() throws SQLException {
        return underlyingPreparedStatement.getUpdateCount();
    }

    /**
     * Gets more results.
     *
     * @return the more results
     * @throws SQLException the sql exception
     */
    @Override
    public boolean getMoreResults() throws SQLException {
        return underlyingPreparedStatement.getMoreResults();
    }

    /**
     * Sets fetch direction.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setFetchDirection(int i) throws SQLException {
        underlyingPreparedStatement.setFetchDirection(i);
    }

    /**
     * Gets fetch direction.
     *
     * @return the fetch direction
     * @throws SQLException the sql exception
     */
    @Override
    public int getFetchDirection() throws SQLException {
        return underlyingPreparedStatement.getFetchDirection();
    }

    /**
     * Sets fetch size.
     *
     * @param i the
     * @throws SQLException the sql exception
     */
    @Override
    public void setFetchSize(int i) throws SQLException {
        underlyingPreparedStatement.setFetchSize(i);
    }

    /**
     * Gets fetch size.
     *
     * @return the fetch size
     * @throws SQLException the sql exception
     */
    @Override
    public int getFetchSize() throws SQLException {
        return underlyingPreparedStatement.getFetchSize();
    }

    /**
     * Gets result set concurrency.
     *
     * @return the result set concurrency
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetConcurrency() throws SQLException {
        return underlyingPreparedStatement.getResultSetConcurrency();
    }

    /**
     * Gets result set type.
     *
     * @return the result set type
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetType() throws SQLException {
        return underlyingPreparedStatement.getResultSetType();
    }

    /**
     * Add batch.
     *
     * @param s the s
     * @throws SQLException the sql exception
     */
    @Override
    public void addBatch(String s) throws SQLException {
        underlyingPreparedStatement.addBatch(s);
    }

    /**
     * Clear batch.
     *
     * @throws SQLException the sql exception
     */
    @Override
    public void clearBatch() throws SQLException {
        underlyingPreparedStatement.clearBatch();
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
        return underlyingPreparedStatement.executeBatch();
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
        return underlyingPreparedStatement.getMoreResults(i);
    }

    /**
     * Gets generated keys.
     *
     * @return the generated keys
     * @throws SQLException the sql exception
     */
    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return underlyingPreparedStatement.getGeneratedKeys();
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
        return underlyingPreparedStatement.executeUpdate(s, i);
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
        return underlyingPreparedStatement.executeUpdate(s, ints);
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
        return underlyingPreparedStatement.executeUpdate(s, strings);
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
        return underlyingPreparedStatement.execute(s, i);
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
        return underlyingPreparedStatement.execute(s, ints);
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
        return underlyingPreparedStatement.execute(s, strings);
    }

    /**
     * Gets result set holdability.
     *
     * @return the result set holdability
     * @throws SQLException the sql exception
     */
    @Override
    public int getResultSetHoldability() throws SQLException {
        return underlyingPreparedStatement.getResultSetHoldability();
    }

    /**
     * Is closed boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isClosed() throws SQLException {
        return underlyingPreparedStatement.isClosed();
    }

    /**
     * Sets poolable.
     *
     * @param b the b
     * @throws SQLException the sql exception
     */
    @Override
    public void setPoolable(boolean b) throws SQLException {
        underlyingPreparedStatement.setPoolable(b);
    }

    /**
     * Is poolable boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    @Override
    public boolean isPoolable() throws SQLException {
        return underlyingPreparedStatement.isPoolable();
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
        return underlyingPreparedStatement.unwrap(tClass);
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
        return underlyingPreparedStatement.isWrapperFor(aClass);
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

