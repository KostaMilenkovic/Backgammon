/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etf.backgammon.mk130130d.controller;


import etf.backgammon.mk130130d.game.Dice;
import etf.backgammon.mk130130d.game.Field;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Background;
import etf.backgammon.mk130130d.view.TableField;
import etf.backgammon.mk130130d.view.View;

/**
 *
 * @author milenkok
 */
public class Controller {
    private View view;
    private Dice dice;
    private Field pressedField = null;
    private List<Field> possibleFields = new ArrayList<Field>();
    private List<Field> activeFields;
    private int currentPlayer = 1;
    private boolean rolledDice = false;
    private int moveCount = 0;
    private int eatenBlue = 0;
    private int eatenRed = 0;
    private int pointsRed = 167;
    private int pointsBlue = 167;
    
    public Controller(){
        
        dice = new Dice();
       
    }
    
    public void setView(View view){
        this.view = view;
    }
  
    public boolean isValidMove(){
        return true;
    }
    
    
    
    //==========================================================================
    
    public List calculateNewTokenFields(int player){
        List<Field> possibleNewFields = new ArrayList<Field>();
        
        int dice1 = dice.getDice1();
        int dice2 = dice.getDice2();
        if(dice.isHasMoved1() && dice.isHasMoved2())
            return possibleNewFields;
        
        if(dice.isHasMoved1())
            dice2 = dice1;
        if(dice.isHasMoved2())
            dice1 = dice2;
        
        if(player==1){
            for(int i=11;i>5;i--){
                if(12 - dice1 != i && 12 - dice2 != i)continue;
                if(view.getField(9, i).getBackgroundColor()=="red"){
                        //to put on other
                        for(int k=8;k>4;k--)
                            if(view.getField(k, i).getBackgroundColor()!="red"){
                                possibleNewFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if(view.getField(9, i).getBackgroundColor()=="blue"){
                    if(view.getField(8, i).getBackgroundColor()!="blue")
                        possibleNewFields.add(new Field(9,i,0,0));
                }
                else
                    possibleNewFields.add(new Field(9,i,0,0));
            }
        }else{
            for(int i=11;i>5;i--){
                if(12 - dice1 != i && 12 - dice2 != i)continue;
                if(view.getField(0, i).getBackgroundColor()=="blue"){
                        //to put on other
                        for(int k=1;k<5;k++)
                            if(view.getField(k, i).getBackgroundColor()!="blue"){
                                possibleNewFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if(view.getField(0, i).getBackgroundColor()=="red"){
                    if(view.getField(1, i).getBackgroundColor()!="red")
                        possibleNewFields.add(new Field(0,i,0,0));
                }
                else
                    possibleNewFields.add(new Field(0,i,0,0));
            }
        }
        
        
        return possibleNewFields;
    }
    
    //==========================================================================
    
    public List calculatePossibleFields(Field pressedField,int player){
        List<Field> possibleFields = new ArrayList<Field>();
        
        int row = pressedField.getI();
        int col = pressedField.getJ();
        
        int dice1 = dice.getDice1();
        int dice2 = dice.getDice2();
        
        if(dice.isHasMoved1())
            dice1 = dice2;
        else if(dice.isHasMoved2())
            dice2 = dice1;
        
        
        if(pressedField.getPlayer()!=player)
            return possibleFields;
        //========================RED PLAYER====================================
        if(row<5 && player == 1){
            int i=col+dice1;
            
            
            if(i < 12)
                if(view.getField(0, i).getBackgroundColor()=="red"){
                        //to put on other
                        for(int k=0;k<5;k++)
                            if(view.getField(k, i).getBackgroundColor()!="red"){
                                possibleFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if(view.getField(0, i).getBackgroundColor()=="blue"){
                    if(view.getField(1, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(0,i,0,0));
                }
                else
                    possibleFields.add(new Field(0,i,0,0));
            
            
            i=col+dice2;
            if(i < 12)
                if(view.getField(0, i).getBackgroundColor()=="red"){
                        //to put on other
                        for(int k=0;k<5;k++)
                            if(view.getField(k, i).getBackgroundColor()!="red"){
                                possibleFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if(view.getField(0, i).getBackgroundColor()=="blue"){
                    if(view.getField(1, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(0,i,0,0));
                }
                else
                    possibleFields.add(new Field(0,i,0,0));
        }
        
        if(row>=5 && player == 1){
            int i = col - dice1;
            if(i>=0)
                if(view.getField(9,i).getBackgroundColor()=="red"){
                    for(int k=8;k>4;k--)
                        if(view.getField(k, i).getBackgroundColor()!="red"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(9, i).getBackgroundColor()=="blue"){
                    if(view.getField(8, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(9,i,0,0));
                }else
                    possibleFields.add(new Field(9,i,0,0));
            
            i = col - dice2;
            if(i>=0)
                if(view.getField(9,i).getBackgroundColor()=="red"){
                    for(int k=8;k>4;k--)
                        if(view.getField(k, i).getBackgroundColor()!="red"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(9, i).getBackgroundColor()=="blue"){
                    if(view.getField(8, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(9,i,0,0));
                }else
                    possibleFields.add(new Field(9,i,0,0));
            
            i = dice1 - col - 1;
            if(i>=0)
                if(view.getField(0, i).getBackgroundColor()=="red"){
                    for(int k=1;k<5;k++)
                        if(view.getField(k, i).getBackgroundColor()!="red"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if (view.getField(0, i).getBackgroundColor()=="blue"){
                    if(view.getField(1, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(0,i,0,0));
                }
                else possibleFields.add(new Field(0,i,0,0));
            
            i = dice2 - col -1;
            if(i>=0)
                if(view.getField(0, i).getBackgroundColor()=="red"){
                    for(int k=1;k<5;k++)
                        if(view.getField(k, i).getBackgroundColor()!="red"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if (view.getField(0, i).getBackgroundColor()=="blue"){
                    if(view.getField(1, i).getBackgroundColor()!="blue")
                        possibleFields.add(new Field(0,i,0,0));
                }
                else possibleFields.add(new Field(0,i,0,0));
            
        }
        
        //========================BLUE PLAYER===================================
        if(row>=5 && player == 2){
            int i = col + dice1;
            if(i<12)
                if(view.getField(9, i).getBackgroundColor()=="blue"){
                        for(int k=8;k>4;k--)
                            if(view.getField(k, i).getBackgroundColor()!="blue"){
                                possibleFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if (view.getField(9, i).getBackgroundColor()=="red"){
                    if(view.getField(8, i).getBackgroundColor()!="red")
                                possibleFields.add(new Field(9,i,0,0));
                }
                else possibleFields.add(new Field(9,i,0,0));
            
            i = col + dice2;
            if(i<12)
                if(view.getField(9, i).getBackgroundColor()=="blue"){
                        for(int k=8;k>4;k--)
                            if(view.getField(k, i).getBackgroundColor()!="blue"){
                                possibleFields.add(new Field(k,i,0,0));
                                break;
                            }
                    }
                else if (view.getField(9, i).getBackgroundColor()=="red"){
                    if(view.getField(8, i).getBackgroundColor()!="red")
                                possibleFields.add(new Field(9,i,0,0));
                }
                else possibleFields.add(new Field(9,i,0,0));
            
        }
        
        if(row<5 && player == 2){
            
            int i = col - dice1;
            if(i>=0)
                if(view.getField(0,i).getBackgroundColor()=="blue"){
                    for(int k=1;k<5;k++)
                        if(view.getField(k, i).getBackgroundColor()!="blue"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(0,i).getBackgroundColor()=="red"){
                    if(view.getField(1, i).getBackgroundColor()!="red")
                                possibleFields.add(new Field(0,i,0,0));
                }
                else possibleFields.add(new Field(0,i,0,0));
            
            i = col - dice2;
            if(i>=0)
                if(view.getField(0,i).getBackgroundColor()=="blue"){
                    for(int k=1;k<5;k++)
                        if(view.getField(k, i).getBackgroundColor()!="blue"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(0,i).getBackgroundColor()=="red"){
                    if(view.getField(1, i).getBackgroundColor()!="red")
                                possibleFields.add(new Field(0,i,0,0));
                }
                else possibleFields.add(new Field(0,i,0,0));
            
            i = dice1 - col - 1;
            if(i>=0)
                if(view.getField(9, i).getBackgroundColor()=="blue"){
                    for(int k=9;k>4;k--)
                        if(view.getField(k, i).getBackgroundColor()!="blue"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(9,i).getBackgroundColor()=="red"){
                    if(view.getField(8, i).getBackgroundColor()!="red")
                                possibleFields.add(new Field(9,i,0,0));
                }
                else possibleFields.add(new Field(9,i,0,0));
            
            i = dice2 - col - 1;
            if(i>=0)
                if(view.getField(9, i).getBackgroundColor()=="blue"){
                    for(int k=9;k>4;k--)
                        if(view.getField(k, i).getBackgroundColor()!="blue"){
                            possibleFields.add(new Field(k,i,0,0));
                            break;
                        }
                }
                else if(view.getField(9,i).getBackgroundColor()=="red"){
                        if(view.getField(8, i).getBackgroundColor()!="red")
                                    possibleFields.add(new Field(9,i,0,0));
                    }
                else possibleFields.add(new Field(9,i,0,0));
            
        }
        //======================================================================

        
        return possibleFields;
    }
    
    //==========================================================================
    
    public boolean canSpawnNewToken(int i,int j,int player){
        if(player==1){
            for(Field pField : possibleFields){
                if(pField.getI()==i && pField.getJ()==j){
                    
                    return true;
                }
            }
        }else{ //player 2
            for(Field pField : possibleFields){
                if(pField.getI()==i && pField.getJ()==j){
                    
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public void clearField(){
        if(pressedField == null)
            view.setLog("select field");
        else{
            //check if all fields are in quarter
            if(currentPlayer == 1){
                if(eatenRed != 0){
                    view.setLog("cannot clear field");
                    return;
                }
                for(int i=0;i<10;i++){
                    for(int j=0;j<12;j++){
                        if(!(i<5 && j>5))
                            if(view.getField(i, j).getBackgroundColor().equals("red")){
                                view.setLog("cannot clear field");
                                return;
                            }
                    }
                }
                int dice1 = dice.getDice1();
                int dice2 = dice.getDice2();
                if(dice.isHasMoved1())
                    dice1 = dice2;
                else if (dice.isHasMoved2())
                    dice2 = dice1;

                if(dice1 >= 12 - pressedField.getJ() || dice2 >= 12 - pressedField.getJ()){
                    view.getField(pressedField.getI(), pressedField.getJ()).setColorAndBorder("white", "black");
                    pointsRed -= 12 - pressedField.getJ();
                    view.setPointsRed(pointsRed);
                    nextPlayer();
                    return;
                }
                else{
                    view.setLog("cannot clear field");
                    return;
                }
                
                
            
            }else{
                //current player blue
                if(eatenBlue != 0){
                    view.setLog("cannot clear field");
                    return;
                }
                for(int i=0;i<10;i++){
                    for(int j=0;j<12;j++){
                        if(!(i>5 && j>5))
                            if(view.getField(i, j).getBackgroundColor().equals("blue")){
                                view.setLog("cannot clear field");
                                return;
                            }
                    }
                }
                
                int dice1 = dice.getDice1();
                int dice2 = dice.getDice2();
                if(dice.isHasMoved1())
                    dice1 = dice2;
                else if (dice.isHasMoved2())
                    dice2 = dice1;
                
                if(dice1 >= 12 - pressedField.getJ() || dice2 >= 12 - pressedField.getJ()){
                    view.getField(pressedField.getI(), pressedField.getJ()).setColorAndBorder("white", "black");
                    pointsBlue -= 12 - pressedField.getJ();
                    view.setPointsBlue(pointsBlue);
                    nextPlayer();
                    return;
                }
                else{
                    view.setLog("cannot clear field");
                    return;
                }
                
               
            }
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    public void pressedField(int i, int j){
        if(!rolledDice){
            view.setLog("roll dice first");
            return;
        }
        
        //check are there eaten tokens
        if(currentPlayer==1){
            if(eatenRed!=0){
                boolean canSpawn = canSpawnNewToken(i,j,currentPlayer);
                if(!canSpawn)return;
                eatenRed--;
                view.setEatenTokenRed(eatenRed);
                
               
                for(int index=0;index<possibleFields.size();index++){
                    if(possibleFields.get(index).getJ()==j){
                        if(view.getField(i, j).getBackgroundColor().equals("blue"))
                        {
                            eatenBlue++;
                            view.setEatenTokenBlue(eatenBlue);
                            if(i>5){
                                pointsBlue += 12 + j + 1;
                                view.setPointsBlue(pointsBlue);
                            }else{
                                pointsBlue += 12 - j;
                                view.setPointsBlue(pointsBlue);
                            }
                        }
                        view.getField(i, j).setColorAndBorder("red", "black");
                        possibleFields.remove(index);
                        pointsRed -= (12 - j);
                        view.setPointsRed(pointsRed);
                        break;
                    }
                }
                
                if(eatenRed==0){
                    for(int index=0;index<possibleFields.size();index++){
                       view.getField(possibleFields.get(index).getI(), possibleFields.get(index).getJ()).setColorAndBorder(view.getField(possibleFields.get(index).getI(), possibleFields.get(index).getJ()).getBackgroundColor(), "black");
                    }
                    
                }
                
                nextPlayer();
                return;
            }
        }else{
            if(eatenBlue!=0){
                boolean canSpawn = canSpawnNewToken(i,j,currentPlayer);
                if(!canSpawn)return;
                eatenBlue--;
                view.setEatenTokenBlue(eatenBlue);
                
                
                for(int index=0;index<possibleFields.size();index++){
                    if(possibleFields.get(index).getJ()==j){
                        if(view.getField(i, j).getBackgroundColor().equals("red"))
                        {
                            eatenRed++;
                            view.setEatenTokenRed(eatenRed);
                            if(i<5){
                                pointsRed += 12 + j + 1;
                                view.setPointsRed(pointsRed);
                            }else{
                                pointsRed += 12 - j;
                                view.setPointsRed(pointsRed);
                            }
                        }
                        view.getField(i, j).setColorAndBorder("blue", "black");
                        possibleFields.remove(index);
                        pointsBlue -= (12 - j);
                        view.setPointsBlue(pointsBlue);
                        break;
                    }
                }

                if(eatenBlue==0){
                    for(int index=0;index<possibleFields.size();index++){
                        view.getField(possibleFields.get(index).getI(), possibleFields.get(index).getJ()).setColorAndBorder(view.getField(possibleFields.get(index).getI(), possibleFields.get(index).getJ()).getBackgroundColor(), "black");
                    }
                    possibleFields = new ArrayList<Field>();
                }
                
                nextPlayer();
                return;
            }
        }
        //======================================================================
       
        
        //remove higlight from previous pressed field and his possible moves
        if(pressedField!=null){
            TableField field = view.getField(pressedField.getI(),pressedField.getJ());
            field.setColorAndBorder(field.getBackgroundColor(),"black");
            field.setText("*");
            for(Field posField : possibleFields){
                int row = posField.getI();
                int col = posField.getJ();
                view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "black");
            }
            
            
        }
        
     
        
        //move player if he selected correct field and quit
        if(possibleFields!=null)
        for(Field posField : possibleFields){
            if(posField.getI() == i && posField.getJ() == j){
                //make a move
                int prevI = pressedField.getI();
                int prevJ = pressedField.getJ();
                view.getField(prevI, prevJ).setColorAndBorder("white", "black");
                //eat blue player
                if(currentPlayer == 1){
                    if(view.getField(i, j).getBackgroundColor()=="blue"){
                        if(i<5){
                            pointsBlue += 12 - j;
                            view.setPointsBlue(pointsBlue);
                        }else{
                            pointsBlue += 12 + j + 1;
                            view.setPointsBlue(pointsBlue);
                        }
                        eatenBlue++;
                        view.setEatenTokenBlue(eatenBlue);
                    }
                    view.getField(i,j).setColorAndBorder("red", "black");
                }
                else{
                    //eat red player
                    if(view.getField(i, j).getBackgroundColor()=="red"){
                        if(i<5){
                            pointsRed += 12 + j + 1;
                            view.setPointsRed(pointsRed);
                        }else{
                            pointsRed += 12 - j;
                            view.setPointsRed(pointsRed);
                        }
                        eatenRed++;
                        view.setEatenTokenRed(eatenRed);
                    }
                    view.getField(i,j).setColorAndBorder("blue", "black");
                }
                    
                
                //remove dice that is selected from possible fields
                    int points = 0;
                    if(Math.abs(prevI-i)<5 )
                        if (Math.abs(prevJ - j)==dice.getDice1()){
                            dice.setHasMoved1(true);
                            points = dice.getDice1();
                            
                            if(currentPlayer == 1)
                                if(eatenRed!=0)
                                    possibleFields = calculateNewTokenFields(currentPlayer);
                                else
                                    possibleFields = calculatePossibleFields(pressedField, currentPlayer);
                            else if(eatenBlue!=0)
                                    possibleFields = calculateNewTokenFields(currentPlayer);
                                else
                                    possibleFields = calculatePossibleFields(pressedField, currentPlayer);

                        }else {
                            dice.setHasMoved2(true);
                            points = dice.getDice2();
                            possibleFields = calculatePossibleFields(pressedField, currentPlayer);
                        }
                    else
                        if (Math.abs(prevJ - j - 1)==dice.getDice1()){
                            dice.setHasMoved1(true);
                            points = dice.getDice1();
                            possibleFields = calculatePossibleFields(pressedField, currentPlayer);

                        }else {
                            dice.setHasMoved2(true);
                            points = dice.getDice2();
                            possibleFields = calculatePossibleFields(pressedField, currentPlayer);
                        }
                
                    
                if(currentPlayer == 1){
                    pointsRed -= points;
                    view.setPointsRed(pointsRed);
                }else{
                    pointsBlue -= points;
                    view.setPointsBlue(pointsBlue);
                }
                
                nextPlayer();
                
                    
                
                return;
            }
               
        }
        
        
        //set new pressed field data
        pressedField = new Field(i,j,0,0);
        if(view.getField(i, j).getBackgroundColor()=="red")
            pressedField.setPlayer(1);
        else if(view.getField(i, j).getBackgroundColor()=="blue")
            pressedField.setPlayer(2);
        
        //if pressed field not field of current player return
        if(pressedField.getPlayer()!=currentPlayer)
            return;
        
        //if pressed field has field of same color on top of it 
        String color;
        if(currentPlayer == 1)
            color = "red";
        else
            color = "blue";
        
        if(i<4){
            if(view.getField(i + 1, j).getBackgroundColor().equals(color))
                return;
        }
        else if(i>5 && i<9)
            if(view.getField(i - 1, j).getBackgroundColor().equals(color))
                return;
        
        //get all possible fields for pressed field
        possibleFields = calculatePossibleFields(pressedField,currentPlayer);
        
        
        //color all possible field for pressed field
        for(Field posField : possibleFields){
            
            int row = posField.getI();
            int col = posField.getJ();
            
            if(currentPlayer == 1)
                view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "red");
            else
                view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "blue");
            
            
        }
    }


    public void nextPlayer(){
        //next player
        if(++moveCount == 2){
            currentPlayer = currentPlayer%2 + 1;
            if(currentPlayer == 1)
                view.setTurnLabel("red player turn");
            else 
                view.setTurnLabel("blue player turn");
            //reset dice
            moveCount = 0;
            rolledDice = false;

            view.setDice1Label(0);
            view.setDice2Label(0);
            for(Field field : possibleFields){
                if(!view.getField(field.getI(), field.getJ()).getBackgroundColor().equals("red") || !view.getField(field.getI(), field.getJ()).getBackgroundColor().equals("blue"))
                view.getField(field.getI(), field.getJ()).setColorAndBorder("white", "black");
            }
            possibleFields = new ArrayList<Field>();
        }
    }
    
    public void rollDice(){
        dice.roll();
        view.setDice1Label(dice.getDice1());
        view.setDice2Label(dice.getDice2());
        rolledDice = true;
        
        
        //reset to black border
        for(Field pField : possibleFields){
            int row = pField.getI();
            int col = pField.getJ();
            view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "black");
        }
        
        //get all possible fields if player is eaten
        if(currentPlayer==1){
            if(eatenRed!=0){
                possibleFields = calculateNewTokenFields(currentPlayer);
            }
        }else{
            if(eatenBlue!=0){
                possibleFields = calculateNewTokenFields(currentPlayer);
            }
        }

        //color all possible field for respawn field
        for(Field pField : possibleFields){

            int row = pField.getI();
            int col = pField.getJ();

            if(currentPlayer == 1)
                view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "red");
            else
                view.getField(row,col).setColorAndBorder(view.getField(row,col).getBackgroundColor(), "blue");


        }
        
    }
    
    public void startPressed(){       
        eatenRed = 0;
        eatenBlue = 0;
        possibleFields = new ArrayList<Field>();
        moveCount = 0;
        dice = new Dice();
        rolledDice = false;
        pressedField = null;
        currentPlayer = 1;
        pointsBlue = 167;
        pointsRed = 167;
        
        view.setStart();
    }
    
    //==========================================================================
    
    public void computerPlay(){
        if(currentPlayer==1){
            view.setLog("red players turn");
            return;
        }
        
        boolean cleared = clearComputerField();
        if(cleared){
            nextPlayer();
            return;
        }

        
        List<Field> activeFields = getActiveFields();
        List<Field> allPossibleFields = getAllPossibleFields(activeFields);
        possibleFields = allPossibleFields;
        Field chosenField = chooseFieldExpectiMax(allPossibleFields);
        pressedField = chosenField.getParentField();
        pressedField(chosenField.getI(),chosenField.getJ());
        
        
    }
    
    public boolean clearComputerField(){
        if(eatenBlue != 0)
            return false;
        
        for(int i=0;i<10;i++){
            for(int j=0;j<12;j++){
                if(!(i>=5 && j>5))
                    if(view.getField(i, j).getBackgroundColor().equals("blue")){
                        return false;
                    }
            }
        }
                
                int dice1 = dice.getDice1();
                int dice2 = dice.getDice2();
                if(dice.isHasMoved1())
                    dice1 = dice2;
                else if (dice.isHasMoved2())
                    dice2 = dice1;
                
                for(int j=6;j<12;j++)
                    for(int i=5;i<10;i++){
                        if(view.getField(i, j).getBackgroundColor().equals("blue")){
                            if(dice1 >= 12 - j || dice2 >= 12 - j){
                                view.getField(i,j).setColorAndBorder("white", "black");
                                pointsBlue -= 12 - j;
                                view.setPointsBlue(pointsBlue);
                                return true;
                            }else
                                continue;
                        }
                    }
                return false;
    }
    
    public Field chooseFieldExpectiMax(List<Field> allPossibleFields){
        int eatingFieldsMaxIndex = 0;
        int eatingFieldsMax = 0;
        int coveringFieldsMaxIndex = 0;
        int coveringFieldsMax = 0;
        List<Field> coveringFields = new ArrayList<Field>();
        List<Field> eatingFields = new ArrayList<Field>();
        //greedy algorithm
        for(Field field : allPossibleFields){
            int i = field.getI();
            int j = field.getJ();

            if(i==8)
                if(view.getField(9, j).getBackgroundColor().equals("blue")){
                    for(int k=j;k<12;k++)
                        if(view.getField(9, k).getBackgroundColor().equals("red"))
                            coveringFields.add(field);
                }
                    
            if(i==1)
                 if(view.getField(0, j).getBackgroundColor().equals("blue"))
                    coveringFields.add(field);
            
            if(view.getField(i, j).getBackgroundColor().equals("red"))
                eatingFields.add(field);
        }            
        int index = 0;
        for(Field field : coveringFields){
            int i = field.getI();
            int j = field.getJ();
            
            if(i<=5){
                if(coveringFieldsMax < j){
                    coveringFieldsMaxIndex = index;
                    coveringFieldsMax = j;
                }
            }else{
                if(coveringFieldsMax < 12 + j){
                    coveringFieldsMaxIndex = index;
                    coveringFieldsMax = 12 + j;
                }
            }
            index++;
        }
        index = 0;
        for(Field field : eatingFields){
            int i = field.getI();
            int j = field.getJ();
            
            if(i>5){
                if(eatingFieldsMax < j){
                    eatingFieldsMaxIndex = index;
                    eatingFieldsMax = j;
                }
            }else{
                if(eatingFieldsMax < 12 + j){
                    eatingFieldsMaxIndex = index;
                    eatingFieldsMax = 12 + j;
                }
            }
            index++;
        }
        if(eatingFieldsMax + coveringFieldsMax == 0)
            return chooseFieldRandom(allPossibleFields);
        
        if(eatingFieldsMax < coveringFieldsMax)
            return coveringFields.get(coveringFieldsMaxIndex);
        else 
            return eatingFields.get(eatingFieldsMaxIndex);
    }
    
    public Field chooseFieldRandom(List<Field> allPossibleFields){
        int index = ((int)(Math.random()*allPossibleFields.size()));
        return allPossibleFields.get(index);
    }
    
    public List getActiveFields(){
        List<Field> activeFields = new ArrayList<Field>();
        
        if(eatenBlue==0){
            //find all active fields - fileds from where you can move
            for(int col=0;col<12;col++){

                    for(int row=0;row<5;row++)
                        if(view.getField(row, col).getBackgroundColor().equals("blue")){
                            if(row!=4){
                                if(view.getField(row + 1, col).getBackgroundColor().equals("blue"))
                                    continue;
                            }
                            activeFields.add(new Field(row,col,2,0));
                        }

                    for(int row=9;row>=5;row--)
                        if(view.getField(row, col).getBackgroundColor().equals("blue")){
                            if(row!=5){
                                if(view.getField(row - 1, col).getBackgroundColor().equals("blue"))
                                    continue;
                            }
                            activeFields.add(new Field(row,col,2,0));
                        }
            }
        }
        
        return activeFields;
    }
    
    public List<Field> getAllPossibleFields(List<Field> activeFields){
        List<Field> allPossibleFields = new ArrayList<Field>();
        
        if(eatenBlue==0){
            for(Field activeField : activeFields){
                List<Field> possibleFields = calculatePossibleFields(activeField, 2);
                for(Field possibleField : possibleFields){
                    possibleField.setParentField(activeField);
                    allPossibleFields.add(possibleField);
                }
            }
        }else{
            allPossibleFields = calculateNewTokenFields(2);
        }
        return allPossibleFields;
    }
    
    //==========================================================================
}
