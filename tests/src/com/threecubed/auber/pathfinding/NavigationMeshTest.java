package com.threecubed.auber.pathfinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.threecubed.auber.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class NavigationMeshTest {

    NavigationMesh testMesh;
    //Random used for generating test values
    Random random;

    @Before
    public void setUp() throws Exception {
        TiledMapTileLayer tileMap = new TiledMapTileLayer(10,10,1,1);
        testMesh = new NavigationMesh(tileMap);
        random = new Random();
    }

    @Test
    public void setCell(){
        //x>0, y>0 (#1,#4)
        int x = Utils.randomIntInRange(random,0,9);
        int y = Utils.randomIntInRange(random,0,9);
        testCell(x,y,true);
        //x=0,y=0(#1 low boundary, #4 low boundary)
        x=0;y=0;
        testCell(x,y,true);
        //X = (width/tileWidth), y = (height/tileHeight) (#1 high boundary, #4 high boundary)
        x=9;y=9;
        testCell(x,y,true);
        //x<0, y>0 (#2,#4)
        x= Utils.randomIntInRange(random,-9,-1); y = Utils.randomIntInRange(random,0,9);
        checkThrowsSetCell(x,y,true);
        //x>0,y<0 (#1,#5)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,-9,-1);
        checkThrowsSetCell(x,y,true);
        //x > (width/tileWidth) , y>0 (#3,#4)
        x= Utils.randomIntInRange(random,10,20); y = Utils.randomIntInRange(random,0,9);
        checkThrowsSetCell(x,y,true);
        //x>0, y > (height/tileHeight) (#1,#6)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,10,20);
        checkThrowsSetCell(x,y,true);
        //x>0,y>0,value = false (#1,#4,#8)
        x = Utils.randomIntInRange(random,0,9);
        y = Utils.randomIntInRange(random,0,9);
        testCell(x,y,false);
    }

    //assigns a value to a cell and then checks that value
    private void testCell(int x,int y,boolean value){
        testMesh.setCell(x,y,value);
        assertEquals(testMesh.cellAccessible(x,y),value);
    }

    //checks in assigning a value to a cell throws an exception
    private void checkThrowsSetCell(int x,int y,boolean value){
        boolean throwsException = false;
        try{
            testMesh.setCell(x,y,value);
        }catch (IllegalArgumentException e){throwsException = true;}
        assertTrue(throwsException);
    }

    @Test
    public void cellAccessible(){
        //x>0, y>0 (#1,#4)
        int x = Utils.randomIntInRange(random,0,9);
        int y = Utils.randomIntInRange(random,0,9);
        testCell(x,y,true);
        //x=0,y=0(#1 low boundary, #4 low boundary)
        x=0;y=0;
        testCell(x,y,true);
        //X = (width/tileWidth), y = (height/tileHeight) (#1 high boundary, #4 high boundary)
        x=9;y=9;
        testCell(x,y,true);
        //x<0, y>0 (#2,#4)
        x= Utils.randomIntInRange(random,-9,-1); y = Utils.randomIntInRange(random,0,9);
        checkThrowsCellAccessible(x,y);
        //x>0,y<0 (#1,#5)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,-9,-1);
        checkThrowsCellAccessible(x,y);
        //x > (width/tileWidth) , y>0 (#3,#4)
        x= Utils.randomIntInRange(random,10,20); y = Utils.randomIntInRange(random,0,9);
        checkThrowsCellAccessible(x,y);
        //x>0, y > (height/tileHeight) (#1,#6)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,10,20);
        checkThrowsCellAccessible(x,y);
        //x>0,y>0,cell inaccessible (#1,#4,#8)
        x = Utils.randomIntInRange(random,0,9);
        y = Utils.randomIntInRange(random,0,9);
        testCell(x,y,false);
    }

    private void checkThrowsCellAccessible(int x,int y){
        boolean throwsException = false;
        try{
            testMesh.cellAccessible(x,y);
        }catch (IllegalArgumentException e){throwsException = true;}
        assertTrue(throwsException);
    }

    @Test
    public void getWorldCoordinates() {
        //x>0, y>0 (#1,#4)
        int x = Utils.randomIntInRange(random,0,9);
        int y = Utils.randomIntInRange(random,0,9);
        testWorldCoords(x,y);
        //x=0,y=0(#1 low boundary, #4 low boundary)
        x=0;y=0;
        testWorldCoords(x,y);
        //X = (width/tileWidth), y = (height/tileHeight) (#1 high boundary, #4 high boundary)
        x=9;y=9;
        testWorldCoords(x,y);
        //x<0, y>0 (#2,#4)
        x= Utils.randomIntInRange(random,-9,-1); y = Utils.randomIntInRange(random,0,9);
        checkThrowsWorldCoords(x,y);
        //x>0,y<0 (#1,#5)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,-9,-1);
        checkThrowsWorldCoords(x,y);
        //x > (width/tileWidth) , y>0 (#3,#4)
        x= Utils.randomIntInRange(random,10,20); y = Utils.randomIntInRange(random,0,9);
        checkThrowsWorldCoords(x,y);
        //x>0, y > (height/tileHeight) (#1,#6)
        x= Utils.randomIntInRange(random,0,9); y = Utils.randomIntInRange(random,10,20);
        checkThrowsWorldCoords(x,y);
    }

    private void testWorldCoords(int x,int y){
        assertEquals(testMesh.getWorldCoordinates(x,y),new Vector2(x,y));
    }

    private void checkThrowsWorldCoords(int x, int y){
        boolean throwsException = false;
        try{
            testMesh.getWorldCoordinates(x,y);
        }catch (IllegalArgumentException e){throwsException = true;}
        assertTrue(throwsException);
    }

    @Test
    public void getTilemapCoordinates() {
        //x>0, y>0 (#1,#4)
        float x = Utils.randomFloatInRange(random,0,9);
        float y = Utils.randomFloatInRange(random,0,9);
        testTileMapCoords(x,y);
        //x=0,y=0(#1 low boundary, #4 low boundary)
        x=0;y=0;
        testTileMapCoords(x,y);
        //X = (width/tileWidth), y = (height/tileHeight) (#1 high boundary, #4 high boundary)
        x=9;y=9;
        testTileMapCoords(x,y);
        //x<0, y>0 (#2,#4)
        x= Utils.randomFloatInRange(random,-9,-1); y = Utils.randomFloatInRange(random,0,9);
        checkThrowsTileCoords(x,y);
        //x>0,y<0 (#1,#5)
        x= Utils.randomFloatInRange(random,0,9); y = Utils.randomFloatInRange(random,-9,-1);
        checkThrowsTileCoords(x,y);
        //x > (width/tileWidth) , y>0 (#3,#4)
        x= Utils.randomFloatInRange(random,10,20); y = Utils.randomFloatInRange(random,0,9);
        checkThrowsTileCoords(x,y);
        //x>0, y > (height/tileHeight) (#1,#6)
        x= Utils.randomFloatInRange(random,0,9); y = Utils.randomFloatInRange(random,10,20);
        checkThrowsTileCoords(x,y);
    }

    private void testTileMapCoords(float x,float y){
        int[] coordinates = testMesh.getTilemapCoordinates(x,y);
        assertEquals(coordinates[0],(int) Math.floor(x));
        assertEquals(coordinates[1],(int) Math.floor(y));
    }

    private void checkThrowsTileCoords(float x,float y){
        boolean throwsException = false;
        try{
            testMesh.getTilemapCoordinates(x,y);
        }catch (IllegalArgumentException e){throwsException = true;}
        assertTrue(throwsException);
    }

    @Test
    public void getSuccessorNodes() {
    }

    @Test
    public void generateTilemapPathToPoint() {
    }

    @Test
    public void generateWorldPathToPoint() {
    }

    @Test
    public void getFurthestPointFromEntity() {
    }

    @Test
    public void getEuclidianDistance() {
    }

    @Test
    public void testGetEuclidianDistance() {
    }
}