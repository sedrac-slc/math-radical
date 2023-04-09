package com.ei.math.radical.text;

import com.ei.math.Step;
import com.ei.math.radical.Radical;

public class RadicalHtml {

    private static String radicalFormat(Radical radical){
        boolean radicalTwo = radical.getExpoent().getDenominator() == 2;
        return "<div class=\"radical-group\">" +
                    ( radicalTwo ? "" : "<sup class=\"expoent\">"+radical.getExpoent().getDenominator()+"</sup>" )+
                    "<div class=\"signal-root\">&radic;</div>" +
                    "<div class=\"base\">"+radical.getBase()+"</div>" +
                "</div>";
    }
    
    private static String fractionGroupOne(Radical radical , String html){
        
        return "<div class='fraction-arithmetic-group'>" +
                    "<div class='fraction-arithmetic-group-item'>"
                        +html+
                    "</div>"
                    + radicalFormat(radical)+
                "</div>";
    }
    
    private static String verifyType(Radical radical, String html){
      return fractionGroupOne(radical, html); 
    }
    
    public static String joinRadical(Radical radical , Step step) {
        return verifyType(radical, step.getHtml());
    }
    
}
