package com.ei.math.radical;

import com.ei.math.fraction.Fraction;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Radical implements Serializable{
    @Builder.Default
    private Fraction coefficient = Fraction.of();
    private Fraction base;
    @Builder.Default
    private Fraction expoent = Fraction.of(1,2);
    
    public static Radical of(long base) {
        return Radical.builder().base(Fraction.of(base)).build();
    }
    
    public static Radical of(long coefficient, long base) {
        return Radical.builder()
                      .coefficient(Fraction.of(coefficient))
                      .base(Fraction.of(base))
            .build();
    }
    
    public static Radical of(long base, double expoent) {
        return Radical.builder()
                      .base(Fraction.of(base))
                      .expoent(Fraction.of(expoent))
            .build();
    }    
    
    public static Radical of(long coefficient, long base, double expoent) {
        return Radical.builder()
                      .coefficient(Fraction.of(coefficient))
                      .base(Fraction.of(base))
                      .expoent(Fraction.of(expoent))
            .build();
    }    
    
    public static Radical of(Fraction base) {
        return Radical.builder().base(base).build();
    }
    
    public static Radical of(Fraction coefficient, Fraction base) {
        return Radical.builder().coefficient(coefficient).base(base).build();
    }
    
    public static Radical of(Fraction coefficient, Fraction base, Fraction expoent) {
        return Radical.builder().coefficient(coefficient).base(base).expoent(expoent).build();
    }    
    
    public boolean equalsRadical(Radical radical){
        if(!expoent.equals(radical.getExpoent())) return false;
        return base.equals(radical.getBase());
    }
    
    public Radical root() {
        return new Radical(Fraction.of(), base, expoent);
    }
    
    public String simplyText(){
        return String.format("%s^%s", base,expoent.text());
    }
    
}
