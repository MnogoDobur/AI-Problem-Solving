/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aicoursework;

import cm3038.search.Node;
import cm3038.search.Path;
import cm3038.search.State;
import cm3038.search.informed.BestFirstSearchProblem;

/**
 *
 * @author Yossif Pavlov
 * 1301599
 */
public class SlidingBlockProblem extends BestFirstSearchProblem {

    public SlidingBlockProblem(State start, State goal) {
        super(start, goal); //super constructor
    }

    @Override
    public double evaluation(Node node) {
        //System.out.println(node.state);
        return node.getCost() + heuristic(node.state); // a* search algorithm
    }

    /**
     * This method creates a state from the parameter and a goalstate and checks
     * their elements. If the [i] element from this state and the [i] element
     * from the goal state are the same result gets +1 In the return I subtract
     * the board length from the result If all the elements are correct it will
     * return 0
     *
     * @param state
     * @return double (the number of wrong possitions)
     */
    public double heuristic(State state) {
        SlidingBlockState thisState = (SlidingBlockState) state;
        SlidingBlockState goalState = (SlidingBlockState) this.goalState;
        double result = 0;
        for (int i = 0; i < thisState.board.length; i++) {
            if (thisState.board[i] == goalState.board[i]) {
                result += 1;
            }
        }
        return thisState.board.length - result;
    }

    /**
     * This method checks if the current state is equal to the goal state
     *
     * @param state
     * @return
     */
    @Override
    public boolean isGoal(State state) {
        SlidingBlockState thisState = (SlidingBlockState) state;
        return thisState.equals(this.goalState);

    }

}
