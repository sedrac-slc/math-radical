package com.ei.math.radical.operation;

import com.ei.math.MathResult;
import com.ei.math.Step;
import com.ei.math.arithmetic.operator.ArithmeticSumOrSub;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.radical.Radical;
import com.ei.math.radical.RadicalConverter;
import com.ei.math.radical.RadicalRegex;
import com.ei.math.radical.text.RadicalFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ResultMap {

    private Step step;
    private Integer index;
    private boolean integers; 
    
    public ResultMap(Step step, Integer index){
        this.step = step;
        this.index= index;
        this.integers = false;
    }
    
}

@Data
public class RadicalSumOrSub {

    private ArithmeticSumOrSub arithmeticSumOrSub;
    private ArithmeticParams arithmeticParams;

    {
        arithmeticParams = new ArithmeticParams();
        arithmeticSumOrSub = new ArithmeticSumOrSub();
    }

    public MathResult resolve(Radical radicalOne, Radical radicalTwo) {
        if (radicalOne.equalsRadical(radicalTwo)) {
            return arithmeticSumOrSub.solve(radicalOne.getCoefficient(), radicalTwo.getCoefficient());
        }
        return null;
    }

    public MathResult resolve(List<Radical> radicals) {
        arithmeticSumOrSub.setArithmeticParams(arithmeticParams);
        
        long start = System.currentTimeMillis();

        Map<Radical, List<Radical>> maps = radicals.stream().collect(Collectors.groupingBy(Radical::root));

        Map<Radical, MathResult> results = new HashMap<>();

        maps.entrySet().forEach(item -> {
            MathResult solve = arithmeticSumOrSub.solve(
                    item.getValue().stream().map(Radical::getCoefficient).collect(Collectors.toList())
            );
            results.put(item.getKey(), solve);
        });

        List<ResultMap> resultMaps = new ArrayList<>();

        final int tam = results.entrySet().stream().map(mapR -> mapR.getValue().getSteps().size())
                .reduce(0, (x, y) -> x > y ? x : y);

        results.entrySet().forEach((var item) -> {
            List<Step> steps = item.getValue().getSteps();
            int size = steps.size();
            boolean integers = steps.stream().allMatch(step -> !step.getHtml().contains("denominator"));
            for (int i = 0; i < tam; i++) {
                if (i < size) {
                    Step step = RadicalFormatter.joinStepForRadical(item.getKey(), steps.get(i));
                    resultMaps.add(new ResultMap(step, i, integers));
                }else{
                    Step step = RadicalFormatter.joinStepForRadical(item.getKey(), steps.get(size-1));
                    resultMaps.add(new ResultMap(step, i, integers));
                }
            }
        });
        resultMaps.stream().forEach(System.out::println);
        Map<Integer, List<ResultMap>> resultsGroupy = resultMaps.stream().collect(
                Collectors.groupingBy(ResultMap::getIndex)
        );

        List<Step> steps = new ArrayList<>();

        resultsGroupy.entrySet().forEach((var item) -> {
            List<Step> collect = item.getValue().stream().map(ResultMap::getStep).collect(Collectors.toList());
            String text = collect.stream().map(Step::getText).collect(Collectors.joining("+"));
            String html = collect.stream().map(Step::getHtml).collect(Collectors.joining("<div>+</div>"));
            steps.add(RadicalFormatter.joinRadicalGroup(text, html));
        });
        
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("grouping")
                .status(true)
                .build();
    }

    public MathResult resolve(Radical... radicals) {
        return resolve(List.of(radicals));
    }

    public MathResult resolve(String expression) {
        if (!expression.matches(RadicalRegex.SUM_SUB)) {
            System.out.println("não passei do regex, "+ expression);
            return MathResult.builder().build();
        }
        List<Radical> list = Arrays.stream(expression.split("[\\+|\\-]"))
                .map(RadicalConverter::parse).toList();
        return resolve(list);
    }
    
}
