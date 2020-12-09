/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textfieldfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    String name = "";
    String sAge = "";
    int age = 0;
    @FXML
    private Label lbName;
    @FXML
    private Label lbAge;
    
    @FXML
    private TextField tfName;

    @FXML
    private TextField tfAge;
    
    @FXML
    private void handleGetNameButtonAction(ActionEvent event) {
        name = tfName.getText();
        lbName.setText(name);
        System.out.println(name);
    }

    @FXML
    private void handleTFnameAction(ActionEvent event) {
        name = tfName.getText();     
        lbName.setText(name);
        System.out.println(name);
    }

    @FXML
    private void handleGetAgeButtonAction(ActionEvent event) {
        sAge = tfAge.getText();  
        age = Integer.parseInt(sAge);
        lbAge.setText(sAge);
        System.out.println(age);
    }

    @FXML
    private void handleTFageAction(ActionEvent event) {
        sAge = tfAge.getText();  
        age = Integer.parseInt(sAge);
        lbAge.setText(sAge);
        System.out.println(age);
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
