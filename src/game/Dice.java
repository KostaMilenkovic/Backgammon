/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author milenkok
 */
public class Dice {
    private int dice1;
    private int dice2;
    private boolean hasMoved1;
    private boolean hasMoved2;

    public int getDice1() {
        return dice1;
    }

    public void setDice1(int dice1) {
        this.dice1 = dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public void setDice2(int dice2) {
        this.dice2 = dice2;
    }
    
    public void roll(){
        dice1 = (int)(Math.random()*1000)%6+1;
        dice2 = (int)(Math.random()*1000)%6+1;
        hasMoved1 = false;
        hasMoved2 = false;
    }

    public boolean isHasMoved1() {
        return hasMoved1;
    }

    public void setHasMoved1(boolean hasMoved1) {
        this.hasMoved1 = hasMoved1;
    }

    public boolean isHasMoved2() {
        return hasMoved2;
    }

    public void setHasMoved2(boolean hasMoved2) {
        this.hasMoved2 = hasMoved2;
    }
    
    
}
