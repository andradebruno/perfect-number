package com.worldline.service;

import com.worldline.exception.PerfectNumberException;

import java.util.List;

/**
 * @author Bruno Andrade
 * Dia: 20/11/2019
 **/
public interface PerfectNumberService {

    /**
     * Check if a number is perfect
     * @param number
     * @return true if the number is perfect
     * @throws PerfectNumberException
     */
    boolean isPerfect(Integer number) throws PerfectNumberException;

    /**
     * Find all perfect numbers in a given range, using two integer greater than zero.
     * @param min
     * @param max
     * @return all perfect numbers in the given range
     * @throws PerfectNumberException
     */
    List<Integer> findNumbersInRange(Integer min, Integer max) throws PerfectNumberException;
}
