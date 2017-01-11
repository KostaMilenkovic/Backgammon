/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


/**
 *
 * @author milenkok
 */
public class TableField extends Button{
    private int i;
    private int j;
    private String background;
    private String border;
    private Controller controller;
    
    public TableField(int i, int j,Controller controller){
        this.controller = controller;
        this.i = i;
        this.j = j;
        background = "white";
        border = "black";
        setStyle("-fx-border-color:black; -fx-background-color: white;");
        setText("*");
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.pressedField(i,j);
            }
        });
    }
    
    public void setColorAndBorder(String background,String border){
        setStyle("-fx-background-color:" + background + ";-fx-border-color: " + border + ";");
        this.background = background;
        this.border = border;
    }
    
    
    public String getBackgroundColor(){
        return background;
    }
    
    public String getBorderColor(){
        return border;
    }
    
  
 
}
