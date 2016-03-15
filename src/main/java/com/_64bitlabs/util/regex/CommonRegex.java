package com._64bitlabs.util.regex;

/**
 * Expression for commonly used Regular expressions.
 * More information about this class is available from <a target="_top" href=
 * "http://64bitlabs.com/utils/CommonRegex.html">64bitlabs.com</a>.
 *
 * @author Tolga Yilmaz info@64bitlabs.com
 * @since 64bitlabsutils 1.0.0
 */
public enum CommonRegex {
    EMAIL_PATTERN ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[\\p{L}0-9-]+(\\.[\\p{L}0-9]+)*(\\.[\\p{L}]{2,})$"),
    DOMAIN_NAME_PATTERN ("^((?!-)[\\p{L}0-9-]{1,63}(?<!-)\\.)+([\\p{L}]{2,})+(\\.[\\p{L}]{2,})*$"),
    PHONE_PATTERN ("([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})|([\\+(]?(\\d){2,}[)]?[- \\.]?(\\d){2,}[- \\.]?(\\d){2,})"),
    PHONE_EPP_PATTERN("^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$"),
    ALPHA_ONLY (""),
    NUMERIC_ONLY (""),
    ALPHANUMERIC ("");

    private CommonRegex(String regex){
        this.regex = regex;
    }
    private String regex;

    @Override
    public String toString(){
        return regex;
    }

}
