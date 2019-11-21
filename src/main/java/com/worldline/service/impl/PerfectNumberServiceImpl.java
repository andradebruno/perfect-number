package com.worldline.service.impl;

import com.worldline.exception.PerfectNumberException;
import com.worldline.service.PerfectNumberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Andrade
 * Dia: 20/11/2019
 **/
@Service
public class PerfectNumberServiceImpl implements PerfectNumberService {


    private final ArrayList<Integer> allPerfectNumbers;

    public PerfectNumberServiceImpl() {
        this.allPerfectNumbers = findPerfectNumbers(Integer.MAX_VALUE);
    }

    @Override
    public boolean isPerfect(Integer number) throws PerfectNumberException {
        if (number<=0) {
            throw new PerfectNumberException("The number must be an integer between 1 and " + Integer.MAX_VALUE + ".");
        }
        return this.allPerfectNumbers.contains(number);
    }

    @Override
    public List<Integer> findNumbersInRange(Integer min, Integer max) throws PerfectNumberException {
        if (min<=0 || max<=0) {
            throw new PerfectNumberException("The min and max must be an integer between 1 and " + Integer.MAX_VALUE + ".");
        } else if (min>max) {
            throw new PerfectNumberException("The min must be lesser than the upper bound.");
        }
        ArrayList<Integer> perfectNumbers = new ArrayList<Integer>();
        for (Integer perfectNumber : this.allPerfectNumbers) {
            if (perfectNumber >= min && perfectNumber <= max) {
                perfectNumbers.add(perfectNumber);
            }
        }
        return perfectNumbers;
    }

    /**
     * Finds all perfect numbers that are less than a given max.
     * @param max is a non-zero positive long integer that shows the upper bound
     * @return the list of all perfect numbers that are less than max
     */
    private ArrayList<Integer> findPerfectNumbers(int max) {
        ArrayList<Integer> perfectNumbers = new ArrayList<>();
         for (int number = 2;; number++) {
             Integer possiblePerfectNumber = (int) (Math.pow(2, number-1) * (Math.pow(2, number) - 1));
            if (possiblePerfectNumber < max) {
                if (isPrime(number) && isPrime((int) (Math.pow(2, number) - 1))) {
                    perfectNumbers.add(possiblePerfectNumber);
                }
            } else {
                break;
            }
        }
        return perfectNumbers;
    }

    /**
     * Checks if a given number is prime based on the well-known theorem. A number n is prime if it is not divisible
     * by any natural number greater than 1 and less that square root of n.
     * @param number is an integer
     * @return true if number is prime
     */
    private Boolean isPrime(int number) {
        Boolean isPrime = true;
        if (number > 2) {
            for (int m = 2; m <= Math.sqrt(number)+1; m++) {
                if (Math.floorMod(number, m)==0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return isPrime;
    }
}
