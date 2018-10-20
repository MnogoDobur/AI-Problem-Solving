/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aicoursework;

import java.util.*;
import cm3038.search.*;
import java.math.*;

/**
 *
 * @author Yossif Pavlov
 */
public class SlidingBlockState implements State {

    private int blank;
    public char[] board = new char[7];

    public SlidingBlockState(char[] board, int blank) {
        this.board = Arrays.copyOf(board, board.length);
        this.blank = blank;
    }

    public int getBlank() {
        return blank;
    }

    public char[] getBoard() {
        char[] temp = new char[this.board.length];
        for (int i = 0; i < this.board.length; i++) {
            temp[i] = this.board[i];
        }
        return temp;
    }

    /**
     * This method swaps the blank tile with the one to be moved.Furthermore it
     * is used to calculate the cost depending on the type of move. Classic swap
     * of 2 elements from board
     *
     * @param action
     * @return state
     */
    public SlidingBlockState applyAction(SlidingBlockAction action) {
        SlidingBlockState result = new SlidingBlockState(this.board, this.blank);
        double myCost = 0.0;
        if ((action.blankSpace - action.currentTile) > 0) { //checking elements to the left of blankSpace
            if ((action.blankSpace - action.currentTile) == 1) { // just a slide
                myCost += 1;
            }
            if ((action.blankSpace - action.currentTile) == 2) { // this means jump over one tile
                if ((action.blankSpace - 1) == action.currentTile) { // since the move is to the left of blank space I check if the -1 element is the same as the current
                    myCost += 1;
                } else {
                    myCost += 2; // if not +2
                }
            }
            if ((action.blankSpace - action.currentTile) == 3) { // jump over two tiles
                if ((action.blankSpace - 1) == action.currentTile) { // check the first one
                    myCost += 1;
                } else {
                    myCost += 2;
                }
                if ((action.blankSpace - 2) == action.currentTile) { // check the second one
                    myCost += 1;
                } else {
                    myCost += 2;
                }
            }
        }
        if ((action.blankSpace - action.currentTile) < 0) {// checking elements to the right of blankSpace
            if ((action.currentTile - action.blankSpace) == 1) { // slide
                myCost += 1;
            }
            if ((action.currentTile - action.blankSpace) == 2) {// jump over one tile
                if ((action.blankSpace + 1) == action.currentTile) { //checking the tile on the right from the blank space if it is the same as the curentTile
                    myCost += 1;
                } else {
                    myCost += 2;
                }
            }
            if ((action.currentTile - action.blankSpace) == 3) { //jump over two tiles
                if ((action.blankSpace + 1) == action.currentTile) { // check the first one if it is the same as the current tile
                    myCost += 1;
                } else {
                    myCost += 2;
                }
                if ((action.blankSpace + 2) == action.currentTile) {//check the second one
                    myCost += 1;
                } else {
                    myCost += 2;
                }
            }
        }

        action.cost = myCost;
        //apply the swap
        char temp = result.board[action.blankSpace];
        result.board[action.blankSpace] = result.board[action.currentTile];
        result.board[action.currentTile] = temp;
        return result;
    }

    /**
     * Method to find all the valid successors of the state. This method checks
     * if there are valid positions to the left or to the right of the blank
     * space
     *
     * @return a list of actionStatePairds of the current state
     */
    public List<ActionStatePair> successor() {
        // here I'm basically finding the blank space 
        for (int i = 0; i < this.board.length; i++) {
            if (board[i] == '-') {
                blank = i;
            }
        }
        List<ActionStatePair> result = new ArrayList<ActionStatePair>();
        if (this.getBlank() - 1 >= 0) { // if there is a tile to the left of to the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() - 1); //create a new action with the blank tile and the tile we want to swap the blank with
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());// create a new state 
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair); // add the action state pair to the list
        }
        if (this.getBlank() + 1 < this.board.length) { // if there is a tile to the right of the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() + 1);//create a new action with the blank tile and the tile we want to swap the blank with
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair);
        }
        if (this.getBlank() - 2 >= 0) { //if there is a tile  2 indexes to the left from the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() - 2);
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair);
        }
        if (this.getBlank() + 2 < this.board.length) { // if there is a tile 2 indexes to the right of the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() + 2);
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair);
        }
        if (this.getBlank() - 3 >= 0) { // if there is a tile  3 indexes to the left from the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() - 3);
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair);
        }
        if (this.getBlank() + 3 < this.board.length) { //if there is a tile  3 indexes to the right from the blank
            SlidingBlockAction tempAction = new SlidingBlockAction(this.getBlank(), this.getBlank() + 3);
            SlidingBlockState tempState = new SlidingBlockState(this.board, this.getBlank());
            ActionStatePair myPair = new ActionStatePair(tempAction, tempState.applyAction(tempAction));
            result.add(myPair);
        }
        return result;
    }

    /**
     * This method checks if 2 states are the same
     *
     * @param state
     * @return boolean true or false
     */
    public boolean equals(Object state) {
        if (!(state instanceof SlidingBlockState)) {
            return false;
        }
        SlidingBlockState myState = (SlidingBlockState) state;
        boolean result = true;
        char[] tempBoard = myState.getBoard();
        for (int i = 0; i < this.board.length; i++) {
            if (this.board[i] != tempBoard[i]) {
                result = false;
            }
        }
        return result;
    }


    @Override
    public int hashCode() {
        int result = 0;
        for (int i = 0; i < this.board.length; i++) {
            if (board[i] == 'B') {
                result += 3 * Math.pow(10, 6 - i);
            }
            if (board[i] == 'W') {
                result += 2 * Math.pow(10, 6 - i);
            }
            if (board[i] == '-') {
                result += 1 * Math.pow(10, 6 - i);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.board.length; i++) {
            result += this.board[i];
        }
        return "SlidingBlockState{" + "board=" + result + '}';
    }
}
