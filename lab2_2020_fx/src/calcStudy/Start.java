package calcStudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class Start {
    @FXML
    TextField tfFirstName;
    @FXML
    TextField tfLastName;
    public static String firstName = "";
    public static String lastName = "";

    public void handleButtonSave(ActionEvent actionEvent) {
        firstName = tfFirstName.getText();
        lastName = tfLastName.getText();
    }

    public void handleButtonExam(ActionEvent actionEvent) {
        checkName("exam.fxml", "Экзамен",true);
    }

    public void handleButtonBall(ActionEvent actionEvent) {
        sceneInit("ball.fxml", "Критерий оценки", false);
    }

    public void checkName(String fxml, String title,boolean check) {
        if (firstName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Уведомление");
            alert.setHeaderText(null);
            alert.setContentText("Введите своё Имя и Фамилию!!!");
            alert.showAndWait();
        } else {
            sceneInit(fxml, title,check);
        }
    }

    public void sceneInit(String fxml, String title, boolean check) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage study = new Stage();
        study.initModality(Modality.WINDOW_MODAL);
        study.setOpacity(1);
        study.setTitle(title);
        assert root != null;
        study.setScene(new Scene(root, 700, 420));
        if(check){
        study.showAndWait();
        System.exit(0);
        } else study.show();
    }

}
