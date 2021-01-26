package com.threecubed.auber.tests;

import com.threecubed.auber.Utils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getMouseCoordinates() {
    }

    @Test
    void randomFloatInRangeTest() {
        //Random number generator passed into the function
        Random random = new Random();
        //random in normal range (2.4-678.92)
        float lowerBound = 2.4f;
        float upperBound = 678.92f;
        //generated number is greater than lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);

        //random in negative range (-200,-0.5)
        lowerBound = -200f;
        upperBound = -0.5f;
        //generated number is greater than lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);

        //lower bound = upper bound
        lowerBound = 45f;
        upperBound = 45f;
        //generated number is greater than lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);

        //lower bound > upper bound
        lowerBound = 115f;
        upperBound = 23f;
        //generated number is greater than lower bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomFloatInRange(random,lowerBound,upperBound)<=upperBound);
    }

    @Test
    void randomIntInRangeTest() {
        //Random number generator passed into the function
        Random random = new Random();
        //random in normal range (12-360)
        int lowerBound = 12;
        int upperBound = 360;
        //generated number is greater than lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);

        //random in negative range (-200,-5)
        lowerBound = -200;
        upperBound = -5;
        //generated number is greater than lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);

        //lower bound = upper bound
        lowerBound = 45;
        upperBound = 45;
        //generated number is greater than lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);

        //lower bound > upper bound
        lowerBound = 115;
        upperBound = 23;
        //generated number is greater than lower bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)>=lowerBound);
        //generated number is less than higher bound
        assertTrue(Utils.randomIntInRange(random,lowerBound,upperBound)<=upperBound);
    }

    @Test
    void randomListItemTest() {
        //Random used for testing
        Random random = new Random();
        //List used for testing
        List<Integer> testList = new ArrayList<Integer>();
        //Empty list
        //testing in the methods throws an exception
        boolean threwException = false;
        try{
            Utils.randomListItem(random,testList);
        }catch (IllegalArgumentException e){threwException=true;}
        assertTrue(threwException);

        //List with 1 item
        testList.add(1);
        assertEquals(testList.get(0),Utils.randomListItem(random,testList));

        //List with multiple items
        testList.add(2);  testList.add(3);testList.add(4);  testList.add(5);testList.add(6);
        assertTrue(testList.contains(Utils.randomListItem(random,testList)));
    }
}