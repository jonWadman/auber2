package com.threecubed.auber.pathfinding;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Path;
import com.badlogic.gdx.math.Vector2;
import com.sun.nio.sctp.PeerAddressChangeNotification;
import com.threecubed.auber.Utils;
import com.threecubed.auber.entities.Civilian;
import com.threecubed.auber.entities.GameEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        //No adjacent accessible cells (#1)
        PathNode testNode = new PathNode(new int[] {3,3},null,new int[]{0,0});
        assertEquals(new ArrayList<>(),testMesh.getSuccessorNodes(testNode,new int[] {7,7}));
        //Left adjacent accessible cell (#2)
        testMesh.setCell(2,3,true);
        assertEquals(generateTestingList(new int[][] {{2,3}}),testMesh.getSuccessorNodes(testNode,new int[] {1,3}));
        testMesh.setCell(2,3,false);
        //Up adjacent accessible cell (#3)
        testMesh.setCell(3,4,true);
        assertEquals(generateTestingList(new int[][] {{3,4}}),testMesh.getSuccessorNodes(testNode,new int[] {3,8}));
        testMesh.setCell(3,4,false);
        //Down adjacent accessible cell (#4)
        testMesh.setCell(3,2,true);
        assertEquals(generateTestingList(new int[][] {{3,2}}),testMesh.getSuccessorNodes(testNode,new int[] {3,0}));
        testMesh.setCell(3,2,false);
        //Right adjacent accessible cell (#5)
        testMesh.setCell(4,3,true);
        assertEquals(generateTestingList(new int[][] {{4,3}}),testMesh.getSuccessorNodes(testNode,new int[] {6,3}));
        testMesh.setCell(4,3,false);
        //Left, up and left up diagonal adjacent (#6)
        testMesh.setCell(2,3,true);
        testMesh.setCell(3,4,true);
        testMesh.setCell(2,4,true);
        assertEquals(generateTestingList(new int[][] {{2,3},{3,4},{2,4}}),testMesh.getSuccessorNodes(testNode,new int[] {1,5}));
        //All adjacent cells accessible (#1-6)
        testMesh.setCell(3,2,true);
        testMesh.setCell(4,3,true);
        testMesh.setCell(2,2,true);
        testMesh.setCell(4,4,true);
        testMesh.setCell(4,2,true);
        assertEquals(generateTestingList(new int[][] {{3, 2}, {2, 3}, {4, 3}, {3, 4}, {2, 2}, {4, 2}, {2, 4}, {4, 4}}),testMesh.getSuccessorNodes(testNode,new int[] {7,3}));
    }

    private ArrayList<PathNode> generateTestingList(int[][] coordinates){
        ArrayList<PathNode> nodes = new ArrayList<>();
        for(int i=0;i<coordinates.length;i++){
            nodes.add(new PathNode(coordinates[i],null,new int[] {0,0}));
        }
        return nodes;
    }


    @Test
    public void generateTilemapPathToPoint() {
        //No path between start and destination (#1)
        boolean throwsException = false;
        try {
            testMesh.generateTilemapPathToPoint(new int[]{6,7},new int[]{9,0});
        }catch (IllegalArgumentException e){throwsException=true;}
        assertTrue(throwsException);
        //Destination to the right of start with a direct path between them (#2)
        testMesh.setCell(6,5,true);
        testMesh.setCell(7,5,true);
        testMesh.setCell(8,5,true);
        assertTrue(listEquals(new int[][]{{6,5},{7,5},{8,5}},testMesh.generateTilemapPathToPoint(new int[]{5,5},new int[]{8,5})));
        testMesh.setCell(6,5,false);
        testMesh.setCell(7,5,false);
        testMesh.setCell(8,5,false);
        //Destination on the left,down diagonal to start, with a direct path between them(#3)
        testMesh.setCell(4,4,true);
        testMesh.setCell(3,3,true);
        testMesh.setCell(5,4,true);
        testMesh.setCell(4,5,true);
        testMesh.setCell(3,4,true);
        testMesh.setCell(4,3,true);
        assertTrue(listEquals(new int[][]{{4,4},{3,3}},testMesh.generateTilemapPathToPoint(new int[]{5,5},new int[]{3,3})));
        testMesh.setCell(4,4,false);
        testMesh.setCell(3,3,false);
        testMesh.setCell(5,4,false);
        testMesh.setCell(4,5,false);
        testMesh.setCell(3,4,false);
        testMesh.setCell(4,3,false);
        //Destination to the down and right of start, with an indirect path between them (#4)
        testMesh.setCell(5,4,true);
        testMesh.setCell(6,4,true);
        testMesh.setCell(6,3,true);
        testMesh.setCell(7,3,true);
        assertTrue(listEquals(new int[][]{{5,4},{6,4},{6,3},{7,3}},testMesh.generateTilemapPathToPoint(new int[]{5,5},new int[]{7,3})));
    }

    private boolean listEquals(int[][] arr,ArrayList<int[]> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i)[0]!=arr[i][0]||list.get(i)[1]!=arr[i][1]){
                return false;
            }
        }
        return true;
    }

    @Test
    public void generateWorldPathToPoint() {
        //No path between start and destination (#1)
        boolean throwsException = false;
        try {
            testMesh.generateWorldPathToPoint(new Vector2(6,7),new Vector2(9,0));
        }catch (IllegalArgumentException e){throwsException=true;}
        assertTrue(throwsException);
        //Destination to the right of start with a direct path between them (#2)
        testMesh.setCell(6,5,true);
        testMesh.setCell(7,5,true);
        testMesh.setCell(8,5,true);
        assertTrue(listEquals(new Vector2[]{new Vector2(6,5),new Vector2(7,5),new Vector2(8,5),new Vector2(8,5)},testMesh.generateWorldPathToPoint(new Vector2(5,5),new Vector2(8,5))));
        testMesh.setCell(6,5,false);
        testMesh.setCell(7,5,false);
        testMesh.setCell(8,5,false);
        //Destination on the left,down diagonal to start, with a direct path between them(#3)
        testMesh.setCell(4,4,true);
        testMesh.setCell(3,3,true);
        testMesh.setCell(5,4,true);
        testMesh.setCell(4,5,true);
        testMesh.setCell(3,4,true);
        testMesh.setCell(4,3,true);
        assertTrue(listEquals(new Vector2[]{new Vector2(4,4),new Vector2(3,3),new Vector2(3,3)},testMesh.generateWorldPathToPoint(new Vector2(5,5),new Vector2(3,3))));
        testMesh.setCell(4,4,false);
        testMesh.setCell(3,3,false);
        testMesh.setCell(5,4,false);
        testMesh.setCell(4,5,false);
        testMesh.setCell(3,4,false);
        testMesh.setCell(4,3,false);
        //Destination to the down and right of start, with an indirect path between them (#4)
        testMesh.setCell(5,4,true);
        testMesh.setCell(6,4,true);
        testMesh.setCell(6,3,true);
        testMesh.setCell(7,3,true);
        assertTrue(listEquals(new Vector2[]{new Vector2(5,4),new Vector2(6,4),new Vector2(6,3),new Vector2(7,3),new Vector2(7,3)},testMesh.generateWorldPathToPoint(new Vector2(5,5),new Vector2(7,3))));
    }

    private boolean listEquals(Vector2[] arr,ArrayList<Vector2> list){
        if(arr.length!=list.size()){return false;}
        for(int i=0;i<list.size();i++){
            assertEquals(list.get(i),arr[i]);
        }
        return true;
    }

    @Test
    public void getEuclidianDistance() {
        //float[] float[] version
        //x1>0, y1>0, x2>0, y2>0 (#1,#3,#5,#7)
        testDistance(5,6,7,2,20);
        //x1=0,y1=0,x2=0,y2=0 (#1, #3, #5, #7 low boundaries)
        testDistance(0,0,0,0,0);
        //x1<0, y1<0, x2<0, y2<0 (#2,#4,#6,#8)
        testDistance(-5,-10,-12,-5,74);
        //x1>0, y1>0, x2>0, y2>0, x1=x2, y1=y2 (#1,#3,#5,#7)
        testDistance(34,78,34,78,0);
    }

    @Test
    public void testGetEuclidianDistance() {
        //int[], int[] version
        //x1>0, y1>0, x2>0, y2>0 (#1,#3,#5,#7)
        testDistance(5.4f,6.87f,124.5f,0.04f,14231.4589f);
        //x1=0,y1=0,x2=0,y2=0 (#1, #3, #5, #7 low boundaries)
        testDistance(0f,0f,0f,0f,0);
        //x1<0, y1<0, x2<0, y2<0 (#2,#4,#6,#8)
        testDistance(-5.12f,-11.1f,-2.24f,-5.55f,39.0969f);
        //x1>0, y1>0, x2>0, y2>0, x1=x2, y1=y2 (#1,#3,#5,#7)
        testDistance(34.12f,45.4f,34.12f,45.4f,0);
    }

    private void testDistance(int x1,int y1, int x2, int y2, float result2){
        int[] firstPoint = {0,0}; int[] secondPoint ={0,0};
        firstPoint[0]=x1;firstPoint[1]=y1;secondPoint[0]=x2;secondPoint[1]=y2;
        assertEquals(NavigationMesh.getEuclidianDistance(firstPoint,secondPoint),Math.sqrt(result2),0.1);
    }

    private void testDistance(float x1,float y1, float x2, float y2, float result2){
        float[] firstPoint = {0,0}; float[] secondPoint ={0,0};
        firstPoint[0]=x1;firstPoint[1]=y1;secondPoint[0]=x2;secondPoint[1]=y2;
        assertEquals(NavigationMesh.getEuclidianDistance(firstPoint,secondPoint),Math.sqrt(result2),0.1);
    }
}