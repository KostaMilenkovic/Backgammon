/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 *
 * @author milenkok
 */
public class View {
    private Scene scene;
    private GridPane mainGrid;
    private Label logLabel;
    private Button startButton;
    private Button rollDiceButton;
    private Label dice1Label;
    private Label dice2Label;
    private Label turnLabel;
    private Label redToken;
    private Label blueToken;
    private Label redPoints;
    private Label bluePoints;
    private TableField[][] tableField;
    private Button computerPlayButton;
    
    
    private Controller controller;
    
    public View(Application app, Stage stage,Controller controller){
        this.controller = controller;
        Parent root = null;
        try {
            root = FXMLLoader.load(app.getClass().getResource("FXMLDocument.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        scene = new Scene(root);

        setStartScene();

        stage.setScene(scene);
        stage.show();
    }
    
    public void setStartScene(){
        mainGrid = (GridPane)scene.lookup("#mainGridPane");
        logLabel = (Label)scene.lookup("#logLabel");
        startButton =  (Button)scene.lookup("#startButton");
        rollDiceButton = (Button)scene.lookup("#rollDice");
        dice1Label = (Label)scene.lookup("#dice1");
        dice2Label = (Label)scene.lookup("#dice2");
        turnLabel = (Label)scene.lookup("#turn");
        redToken = (Label)scene.lookup("#redToken");
        blueToken = (Label)scene.lookup("#blueToken");
        computerPlayButton = (Button)scene.lookup("#computerPlayButton");
        redPoints = (Label)scene.lookup("#redPointsLabel");
        bluePoints = (Label)scene.lookup("#bluePointsLabel");
        
        turnLabel.setText("red player turn");
        logLabel.setText("");
        dice1Label.setText("0");
        dice2Label.setText("0");
        redToken.setStyle("-fx-border-color: red;");
        blueToken.setStyle("-fx-border-color: blue;");
        redToken.setText("0");
        blueToken.setText("0");
        
        
        computerPlayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.computerPlay();
            }
        });
        
        rollDiceButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.rollDice();
            }
        });
        
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.startPressed();
            }
        });
        
        tableField = new TableField[10][];
        for(int i=0;i<10;i++)
            tableField[i] = new TableField[12];
        
        for(int i=0;i<11;i++){
            for(int j=0;j<13;j++){
                if(i!=5 && j!=6){
                    int row = i;
                    int col = j;
                    if(row>5)row--;
                    if(col>6)col--;
                    tableField[row][col] = new TableField(row,col,controller);
                    mainGrid.add(tableField[row][col], j,i);
                }
                
            }
        }
        
        setStart();
  
    }
    
    public void setLog(String message){
        logLabel.setText(message);
    }
    
    
   public TableField getField(int i,int j){
       return tableField[i][j];
   }
    
   
   public void setDice1Label(int value){
       dice1Label.setText("" + value);
   }
   
   public void setDice2Label(int value){
       dice2Label.setText("" + value);
   }
   
   public void setTurnLabel(String message){
       turnLabel.setText(message);
   }
   
   public void setEatenTokenBlue(int num){
       blueToken.setText("" + num);
   }
   
   public void setEatenTokenRed(int num){
       redToken.setText("" + num);
   }
   
   public void setPointsRed(int points){
       redPoints.setText("" + points);
   }
   
   public void setPointsBlue(int points){
       bluePoints.setText("" + points);
   }
   
   public void setStart(){
        for(int i=0;i<10;i++)
            for(int j=0;j<12;j++)
                getField(i,j).setColorAndBorder("white", "black");
       
        this.getField(5, 0).setColorAndBorder("red","black");

        this.getField(6, 0).setColorAndBorder("red","black");
        this.getField(7, 0).setColorAndBorder("red","black");
        this.getField(8, 0).setColorAndBorder("red","black");
        this.getField(9, 0).setColorAndBorder("red","black");

        this.getField(8, 11).setColorAndBorder("red","black");
        this.getField(9, 11).setColorAndBorder("red","black");

        this.getField(0, 6).setColorAndBorder("red","black");
        this.getField(1, 6).setColorAndBorder("red","black");
        this.getField(2, 6).setColorAndBorder("red","black");
        this.getField(3, 6).setColorAndBorder("red","black");
        this.getField(4, 6).setColorAndBorder("red","black");

        this.getField(0, 4).setColorAndBorder("red","black");
        this.getField(1, 4).setColorAndBorder("red","black");
        this.getField(2, 4).setColorAndBorder("red","black");


        //set blue
        this.getField(5, 6).setColorAndBorder("blue","black");
        this.getField(6, 6).setColorAndBorder("blue","black");
        this.getField(7, 6).setColorAndBorder("blue","black");
        this.getField(8, 6).setColorAndBorder("blue","black");
        this.getField(9, 6).setColorAndBorder("blue","black");

        this.getField(0, 11).setColorAndBorder("blue","black");
        this.getField(1, 11).setColorAndBorder("blue","black");

        this.getField(0, 0).setColorAndBorder("blue","black");
        this.getField(1, 0).setColorAndBorder("blue","black");
        this.getField(2, 0).setColorAndBorder("blue","black");
        this.getField(3, 0).setColorAndBorder("blue","black");
        this.getField(4, 0).setColorAndBorder("blue","black");

        this.getField(7, 4).setColorAndBorder("blue","black");
        this.getField(8, 4).setColorAndBorder("blue","black");
        this.getField(9, 4).setColorAndBorder("blue","black");
        
        blueToken.setText("0");
        redToken.setText("0");
        dice1Label.setText("0");
        dice2Label.setText("0");
        logLabel.setText("");
        turnLabel.setText("red player");
        redPoints.setText("167");
        bluePoints.setText("167");
        
        
        
        
   }
 
}
