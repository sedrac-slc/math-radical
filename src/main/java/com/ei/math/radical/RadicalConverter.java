package com.ei.math.radical;

import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.FractionRegex;
import com.ei.math.radical.exception.RadicalRegexException;

public class RadicalConverter {
    
    public static Radical parse(String expression){
        if(!expression.matches(RadicalRegex.RADICAL))
            throw new RadicalRegexException();
 
        if(expression.matches(FractionRegex.FRACTION))
            return Radical.builder().base(FractionConverter.parse(expression)).build();
        if(expression.matches(RadicalRegex.RADICAL_COEF_RAD)){
            String[] split = expression.split(RadicalRegex.SEPARATOR);
            return Radical.builder()
                          .coefficient(FractionConverter.parse(split[0]))
                          .base(FractionConverter.parse(split[1]))
                          .build();
        }
        if(expression.matches(RadicalRegex.RADICAL_RAD_EXP)){
            String[] split = expression.split(RadicalRegex.EXPOENT_SIGNAL);
            return Radical.builder()
                          .base(FractionConverter.parse(split[0]))
                          .expoent(FractionConverter.parse(split[1]))
                          .build();
        }        
        String[] split = expression.split(RadicalRegex.SEPARATOR_SIG_ALL);
        return Radical.builder()
                .coefficient(FractionConverter.parse(split[0]))
                .base(FractionConverter.parse(split[1]))
                .expoent(FractionConverter.parse(split[2]))
                .build();
    } 
    
}
