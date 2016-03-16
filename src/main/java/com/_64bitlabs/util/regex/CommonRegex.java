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

package com._64bitlabs.util.regex;

/**
 * Expression for commonly used Regular expressions.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.0.0
 */
public enum CommonRegex {
    EMAIL_PATTERN ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[\\p{L}0-9-]+(\\.[\\p{L}0-9]+)*(\\.[\\p{L}]{2,})$"),
    DOMAIN_NAME_PATTERN ("^((?!-)[\\p{L}0-9-]{1,63}(?<!-)\\.)+([\\p{L}]{2,})+(\\.[\\p{L}]{2,})*$"),
    PHONE_PATTERN ("([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})"),
    PHONE_EPP_PATTERN("^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$"),
    ALPHA_ONLY ("\\w"),
    NUMERIC_ONLY ("\\d"),
    ALPHANUMERIC ("^[\\pL\\pN\\p{Pc}]*$");

    CommonRegex(String regex){
        this.regex = regex;
    }
    private String regex;

    @Override
    public String toString(){
        return regex;
    }

}
