package calcStudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;


public class Controller {


    //Study st = new Study();
    @FXML
    private Label labelName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelStudyDiscribe;

    public void handleTFnameAction(ActionEvent actionEvent) {
    }

    public void handleGetNameButtonAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Иван");

        dialog.setTitle("Имя");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(labelName::setText);
    }

    public void handleGetLastNameButtonAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Иванов");

        dialog.setTitle("Фамилия");
        dialog.setHeaderText("Enter your lastname:");
        dialog.setContentText("Lastname:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(labelLastName::setText);
    }

    public void handleButtonStudy(ActionEvent actionEvent) throws InterruptedException {
        if(labelName.getText() == ""){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Ошибка:");
            alert.setContentText("Введите своё Имя!!!");

            alert.showAndWait();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("study.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage study = new Stage();
            study.initModality(Modality.APPLICATION_MODAL);
            study.setOpacity(1);
            study.setTitle("Обучение");
            study.setScene(new Scene(root, 450, 450));
            study.show();
        }
    }

    public void handleButtonExam(ActionEvent actionEvent) {
        if(labelName.getText() == ""){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Ошибка:");
            alert.setContentText("Введите своё Имя и Фамилию!!!");

            alert.showAndWait();
        } else {
            studyInit();
           labelStudyDiscribe.setText("123");

        }
    }
    public void studyInit(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("study.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage study = new Stage();
        study.initModality(Modality.WINDOW_MODAL);
        study.setOpacity(1);
        study.setTitle("Обучение");
        study.setScene(new Scene(root, 450, 450));
        study.showAndWait();
    }

    public void handleEnterStudyAction(ActionEvent actionEvent) {
    }

}
