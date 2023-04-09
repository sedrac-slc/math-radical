package com.ei.math.radical.text;

import com.ei.math.Step;
import com.ei.math.radical.Radical;

public class RadicalFormatter {
    
    public static Step joinStepForRadical(Radical radical,Step step){
        String text = RadicalText.joinRadical(step,radical);
        String html = RadicalHtml.joinRadical(radical,step);
        return Step.builder().text(text).html(html).build();
    }
    
    public static Step joinRadicalGroup(String text, String html,int cod) {
        String exp = "<div class=\"fraction-group-radical\">"+html+"</div>";
        if(!html.contains("denominator"))
            exp = "<div class=\"fraction-group-radical signal-simple\">"+html+"</div>";
        return new Step(cod, text, exp,"");
    }
    
    public static Step joinRadicalGroup(String text, String html) {
        return  joinRadicalGroup(text, html, 0);
    }
        
}
