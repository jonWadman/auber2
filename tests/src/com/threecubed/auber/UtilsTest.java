package com.threecubed.auber;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class UtilsTest {
    Random random;

/*    @Before
    public void setUp() throws Exception {
        //Random used for testing
        random = new Random();
    }

    @Test
    public void getMouseCoordinates() {
    }

    @Test
    public void randomFloatInRange() {
        //upper bound > lower bound
        float upperBound = 40;
        float lowerBound = 10;
        //gte to lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //lte to upper bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);

        //upper bound = lower bound
        upperBound = 40;
        lowerBound = 40;
        //gte to lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //lte to upper bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);

        //upper bound < lower bound
        upperBound = 10;
        lowerBound = 40;
        //asserting an exception is thrown
        boolean threwException = false;
        try{
            Utils.randomFloatInRange(random,lowerBound,upperBound);
        }catch (IllegalArgumentException e){threwException=true;}
        assertTrue(threwException);
    }

    @Test
    public void randomIntInRange() {
        //upper bound > lower bound
        int upperBound = 40;
        int lowerBound = 10;
        //gte to lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //lte to upper bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);

        //upper bound = lower bound
        upperBound = 40;
        lowerBound = 40;
        //gte to lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //lte to upper bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);

        //upper bound < lower bound
        upperBound = 10;
        lowerBound = 40;
        //asserting an exception is thrown
        boolean threwException = false;
        try{
            Utils.randomIntInRange(random,lowerBound,upperBound);
        }catch (IllegalArgumentException e){threwException=true;}
        assertTrue(threwException);
    }

    @Test
    public void randomListItem() {
        //testing list
        List<Integer> testList = new ArrayList<Integer>();

        //empty list
        //asserting an exception is thrown
        boolean threwException = false;
        try{
            Utils.randomListItem(random,testList);
        }catch (IllegalArgumentException e){threwException=true;}
        assertTrue(threwException);
        //length = 1
        testList.add(1);
        assertEquals(testList.get(0),Utils.randomListItem(random,testList));
        //length > 1
        testList.add(2); testList.add(3); testList.add(4); testList.add(5); testList.add(6);
        assertTrue(testList.contains(Utils.randomListItem(random,testList)));
    }*/
}