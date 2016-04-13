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
/**
 * The type Db pool unavailable exception.
 */
public class DBPoolUnavailableException extends Exception {
    /**
     * Instantiates a new Db pool unavailable exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public DBPoolUnavailableException(String message, Throwable cause) {
        super(message, cause);

        // OK - we need to destroy the DB pool here somehow.  Prevent anything more being written to the DB.
        DBFactory.disableDBFactory();
    }
}
