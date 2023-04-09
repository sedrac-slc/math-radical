package com.ei.math.radical;

import com.ei.math.fraction.FractionRegex;

public final class RadicalRegex {
    public static final String SEPARATOR = "[v|V|c|C]";
    private static final String FRACTION = FractionRegex.FRACTION;
    public static final String EXPOENT = "[\\+|\\-]?\\d+/[\\+|\\-]?\\d+";
    public static final String EXPOENT_SIGNAL  = "[\\^|e]";
    
    
    public static final String RADICAL_COEF_RAD = FRACTION+SEPARATOR+FRACTION;
    public static final String RADICAL_RAD_EXP = FRACTION+EXPOENT_SIGNAL+EXPOENT;
    
    public static final String RADICAL = "("+FRACTION+SEPARATOR+")?"+FRACTION+"("+EXPOENT_SIGNAL+EXPOENT+")?";
    
    public static final String SEPARATOR_SIG_ALL = SEPARATOR.substring(0, SEPARATOR.length()-1)
            +"|"+EXPOENT_SIGNAL.substring(1);
    
    
    public static final String SUM_SUB = "[\\+|\\-]?"+RADICAL+"([\\+|\\-]"+RADICAL+")+";
    
}
