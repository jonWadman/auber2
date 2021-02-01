package com.threecubed.auber.pathfinding;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PathNodeTest {

    @Test
    public void testToString() {
        //Positive x, positive y (#1,#2)
        int[] testPos = {24,15};
        PathNode testNode = new PathNode(testPos,null,testPos);
        assertEquals(Arrays.toString(testPos),testNode.toString());
        //Negative x, positive y(#1 low boundary,#2)
        testPos[0] = -56;
        testNode = new PathNode(testPos,null,testPos);
        assertEquals(Arrays.toString(testPos),testNode.toString());
        //Negative x, negative y(#1 low boundary,#2 low boundary)
        testPos[1] = -102;
        testNode = new PathNode(testPos,null,testPos);
        assertEquals(Arrays.toString(testPos),testNode.toString());
        //Positive x, negative y (#1,#2 low boundary)
        testPos[0] = 76;
        testNode = new PathNode(testPos,null,testPos);
        assertEquals(Arrays.toString(testPos),testNode.toString());
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
        //Both objects are PathNodes with equal coordinates (#1,#3)
        int[] testPos = {45,67};
        Object obj1 = new PathNode(testPos,null,testPos);
        Object obj2 = new PathNode(testPos,null,testPos);
        assertTrue(obj1.equals(obj2));
        //Both objects are PathNodes with unequal coordinates (#1,#4)
        int[] testPos2 = {12,7};
        obj2 = new PathNode(testPos2,null,testPos2);
        assertFalse(obj1.equals(obj2));
        //Obj 2 is an Integer, Obj1 is a pathnode (#2)
        obj2 = new Integer(89);
        assertFalse(obj1.equals(obj2));
    }
}