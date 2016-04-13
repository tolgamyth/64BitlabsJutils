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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by tolga on 12.04.2016.
 */
public class JDBCUtil {

    /**
     * Safe close.
     *
     * @param connection the connection to be closed
     */
    public static void safeClose(Connection connection) {
        try {
            if (connection != null) {
                try {
//                  This is very useful for debugging as it makes sure the transaction is committed.
//                  if it isn't you'll get a nice big exception letting you know!
//                  if (!connection.isClosed() && !connection.isReadOnly()) {
//                      checkTransactionRecorded(connection);
//                  }

                    try {
                        if (!connection.isClosed()) {
                            connection.rollback();
                        }
                    } catch (SQLException e) {
                    }
                } finally {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            // nothing to do here
        }
    }

    /**
     * Safe close.
     *
     * @param statement the statement
     */
    public static void safeClose(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            // nothing to do here
        }
    }

    /**
     * Safe close.
     *
     * @param resultSet the result set
     */
    public static void safeClose(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            // nothing to do here
        }
    }
}
