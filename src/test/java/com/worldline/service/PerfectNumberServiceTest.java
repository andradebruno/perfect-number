package com.worldline.service;

import com.worldline.exception.PerfectNumberException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Bruno Andrade
 * Dia: 20/11/2019
 **/
@SpringBootTest
public class PerfectNumberServiceTest {

    @Autowired
    private PerfectNumberService perfectNumberService;


    @Test
    public void testIfGivenNumberIsPerfect() throws PerfectNumberException {
        boolean number5 = perfectNumberService.isPerfect(5);
        boolean number6 = perfectNumberService.isPerfect(6);


        assertThat(number5).isEqualTo(false);
        assertThat(number6).isEqualTo(true);
    }

    @Test
    public void testIfGivenNumberRange() throws PerfectNumberException {
        List<Integer> number120 = perfectNumberService.findNumbersInRange(1, 20);
        List<Integer> number1200 = perfectNumberService.findNumbersInRange(1, 200);


        assertThat(number120).contains(6);
        assertThat(number1200).contains(6,28);
    }

    @Test
    public void testNumberNegativeTrowPerfectNumberException() throws PerfectNumberException {
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.isPerfect(-1));
    }

    @Test
    public void testMinNegativeTrowPerfectNumberException() throws PerfectNumberException {
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.findNumbersInRange(-1, 2));
    }

    @Test
    public void testMaxNegativeTrowPerfectNumberException() throws PerfectNumberException {
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.findNumbersInRange(5, -2));
    }

    @Test
    public void testMinGreaterThanMaxTrowPerfectNumberException() throws PerfectNumberException {
        assertThrows(PerfectNumberException.class, () -> perfectNumberService.findNumbersInRange(20, 5));
    }
}
