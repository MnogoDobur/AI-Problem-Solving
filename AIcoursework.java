/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aicoursework;

import cm3038.search.Path;
import cm3038.search.*;
import java.util.List;

/**
 *
 * @author Yossif Pavlov
 */
public class AIcoursework {

    public static void main(String[] args) {
        char[] startBoard = {'W', 'W', 'W', 'B', 'B', 'B', '-'};
        SlidingBlockState startState = new SlidingBlockState(startBoard, 6);
        char[] goalBoard = {'-', 'B', 'B', 'B', 'W', 'W', 'W'};
        SlidingBlockState goalState = new SlidingBlockState(goalBoard, 6);
        
        char[] extendedBoard = {'B','W','B','W','B','W','B','W','B','W','-'};
        SlidingBlockState extendedStartState = new SlidingBlockState(extendedBoard, 6);
        char[] extendedGoalBoard = {'W','B','W','B','W','B','W','B','W','B','-'};
        SlidingBlockState extendedGoalState = new SlidingBlockState(extendedGoalBoard, 6);
        

     // System.out.println(startState.hashCode());

        SlidingBlockProblem basic = new SlidingBlockProblem(startState, goalState);
        SlidingBlockProblem extended = new SlidingBlockProblem(extendedStartState,extendedGoalState);
       
        Path path = basic.search();
     // Path generalProblem = extended.search();

        path.print();
        System.out.println("Nodes visited: " + basic.nodeVisited);
        System.out.println("Cost: " + path.cost + "\n");

    }

}
