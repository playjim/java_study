package calcStudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Ball {
    @FXML
    TextField tfNameBallCount;
    @FXML
    TextField tfNameBallA;
    @FXML
    TextField tfNameBallB;
    @FXML
    TextField tfNameBallC;
    @FXML
    Button buttonSave;
    static int percentBallA = 100;
    static int percentBallB = 80;
    static int percentBallC = 65;
    static int countBall = 10;


    public void handleButtonSave(ActionEvent actionEvent) {
        boolean state = true;
        try {
            percentBallA = Integer.parseInt(tfNameBallA.getText());
            percentBallB = Integer.parseInt(tfNameBallB.getText());
            percentBallC = Integer.parseInt(tfNameBallC.getText());
            countBall = Integer.parseInt(tfNameBallCount.getText());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Невернеый формат ввода!");
            alert.setContentText("Пожалуста введите числа.");
            alert.showAndWait();
            state = false;
        }
        if (state) {
            Stage stage = (Stage) buttonSave.getScene().getWindow();
            stage.close();
        }
    }
}
