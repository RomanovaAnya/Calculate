package ru.neoflex.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.neoflex.practice.entity.CalculationResult;
import ru.neoflex.practice.repository.CalculationResultRepository;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class CalcControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CalculationResultRepository calculationResultRepository;

    @Before
    public void setup() {
        CalcController calcController = new CalcController(calculationResultRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(calcController).build();

        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setA(5);
        calculationResult.setB(10);
        calculationResult.setResult(15);
        Mockito.when(calculationResultRepository.findAll()).thenReturn(List.of(calculationResult));
    }

    @Test
    public void testPlusEndpoint() throws Exception {
        int a = 5;
        int b = 10;
        int expectedResult = a + b;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}", a, b)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int actualResult = Integer.parseInt(content);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testMinusEndpoint() throws Exception {
        int a = 10;
        int b = 5;
        int expectedResult = a - b;

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        int actualResult = Integer.parseInt(content);
        Assert.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void testCalculationResultsEndpoint() throws Exception {
        List<CalculationResult> expectedResults = new ArrayList<>();
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setA(5);
        calculationResult.setB(10);
        calculationResult.setResult(15);
        expectedResults.add(calculationResult);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedResultsJson = objectMapper.writeValueAsString(expectedResults);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResultsJson))
                .andReturn();
    }
}