package ru.neoflex.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.practice.entity.CalculationResult;
import ru.neoflex.practice.repository.CalculationResultRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class CalcController {

    private final CalculationResultRepository calculationResultRepository;


    @GetMapping("/plus/{a}/{b}")
    public Integer plus(@PathVariable Integer a, @PathVariable Integer b){
        Integer result = a + b;
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setA(a);
        calculationResult.setB(b);
        calculationResult.setResult(result);
        calculationResultRepository.save(calculationResult);
        return result;
    }

    @GetMapping("/minus/{a}/{b}")
    public Integer minus(@PathVariable Integer a, @PathVariable Integer b){
        Integer result = a - b;
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setA(a);
        calculationResult.setB(b);
        calculationResult.setResult(result);
        calculationResultRepository.save(calculationResult);
        return result;
    }

    @GetMapping("/all")
    public List<CalculationResult> calculationResults(){
        return calculationResultRepository.findAll();
    }
}
