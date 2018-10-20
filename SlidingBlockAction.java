/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aicoursework;

import cm3038.search.*;
import java.util.Arrays;

/**
 *
 * @author Yossif Pavlov
 */
public class SlidingBlockAction extends Action {

    public int blankSpace;
    public int currentTile;

    public SlidingBlockAction(int blankSpace, int currentTile) {
        this.blankSpace = blankSpace;
        this.currentTile = currentTile;
    }

    @Override
    public String toString() {
        return "Moving " + currentTile + " to blank space " + blankSpace + " [cost] = " + this.cost;
    }

}
