package com.ei.math.radical.text;

import com.ei.math.Step;
import com.ei.math.radical.Radical;

public class RadicalText {

    public static String joinRadical(Step step, Radical radical) {
     return "("+step.getText()+")"+radical.simplyText();
    }
    
}
