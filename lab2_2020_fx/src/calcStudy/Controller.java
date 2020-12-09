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
    public Label labelName;
    @FXML
    private Label labelLastName;
    @FXML
    private Label labelStudyDiscribe;
    @FXML
    private Label labelStudyStart;
    @FXML
    private Label labelStudy;
    public static boolean stateStudy = true;//чек статус начала обучения.
    public static String firstName;
    public static String lastName;
    public static int count;
    public static int c;
    public void handleTFnameAction(ActionEvent actionEvent) {
    }

    public void handleGetNameButtonAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Иван");
        dialog.setTitle("Имя");
        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(labelName::setText);
        firstName = labelName.getText();
    }

    public void handleGetLastNameButtonAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("Иванов");
        dialog.setTitle("Фамилия");
        dialog.setHeaderText("Enter your lastname:");
        dialog.setContentText("Lastname:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(labelLastName::setText);
        lastName = labelLastName.getText();
    }

    public void handleButtonStudy(ActionEvent actionEvent){
        if (labelName.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Уведомление");
            alert.setHeaderText(null);
            alert.setContentText("Сначала введите Имя");
            alert.showAndWait();
        } else {
            System.out.println(stateStudy);
            studyInit("study.fxml", "Режим обучения");
            stateStudy = false;
            System.out.println(stateStudy);
        }
    }

    public void handleButtonExam(ActionEvent actionEvent) {
        if (labelName.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Ошибка:");
            alert.setContentText("Введите своё Имя и Фамилию!!!");
            alert.showAndWait();
        } else {
            studyInit("study.fxml", "Обучение");
        }
    }

    public void studyInit(String fxml, String title) {
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
        study.setScene(new Scene(root, 700, 420));
        study.show();
    }

    public void handleEnterStudyAction(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog("10");
        dialog.setTitle("Количество примеров");
        dialog.setHeaderText(firstName + ", введи количество примеров:");
        dialog.setContentText("Количество:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(labelStudyDiscribe::setText);
        count = Integer.parseInt(labelStudyDiscribe.getText());
    }

    public void handleButtonGetInfo(ActionEvent actionEvent) {
        System.out.println(stateStudy);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Test Connection");
        alert.setHeaderText(null);
        alert.setContentText("Connect to the database successfully!");
        alert.showAndWait();
    }

    public void handleButtonStartStudy(ActionEvent actionEvent) {
        labelStudyStart.setText( firstName + ", тебе предстоит решить " + count + " примеров!");
        //public void startStudy(int count) {
//            for (int i = 0; i < count; i++) {
//                int a = Gen_Random();
 //               int b = Gen_Random();
 //               c = a * b;
 //               labelStudyStart.setText("Сколько будет: " + a + " * " + b + " = ");
  //              Scanner scanner = null;
 //               //https://coderoad.ru/38766656/JavaFX-%D0%BE%D0%B6%D0%B8%D0%B4%D0%B0%D0%BD%D0%B8%D1%8F-%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%BE%D0%B3%D0%BE-%D0%B2%D0%B2%D0%BE%D0%B4%D0%B0-%D1%82%D0%B5%D0%BA%D1%81%D1%82%D0%B0-%D0%B1%D0%B5%D0%B7-%D0%BA%D0%B0%D0%BA%D0%B8%D1%85-%D0%BB%D0%B8%D0%B1%D0%BE-%D1%82%D0%B5%D0%BA%D1%81%D1%82%D0%BE%D0%B2%D0%BE%D0%B5-%D0%BF%D0%BE%D0%BB%D0%B5
  //              int sum = Integer.parseInt(scanner.nextLine());
  //              if (sum == c) {
   //                 System.out.println("Верно!");
   //                 study.right += 1;
   //             } else {
   //                 System.out.println("Не верно! Правильный ответ: " + c);
  //                  study.wrong += 1;
  //              }
  //          }
 //       }
    }
    private int Gen_Random() {
        int max = 9, min = 1;
        max -= min;
        double gen = (Math.random() * ++max) + min;
        return (int) gen;
    }
}
