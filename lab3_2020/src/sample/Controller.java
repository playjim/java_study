package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    @FXML
    Button buttonStart;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonPrint;
    @FXML
    Button buttonDelete;
    @FXML
    TextArea textArea;
    @FXML
    TextField tfName;
    @FXML
    TextField tfRelationship;
    @FXML
    TextField tfAge;
    static ArrayList<Familly> member = new ArrayList<>();

    public void handleButtonAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Stage deleteStage = new Stage();
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setOpacity(1);
        deleteStage.setTitle("Добавление объекта из списка");
        assert root != null;
        deleteStage.setScene(new Scene(root, 600, 400));
        deleteStage.showAndWait();
        printMembers();
    }

    public void handleButtonDelete(ActionEvent actionEvent) throws IOException, InterruptedException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete.fxml"));
        Parent root;
        root = fxmlLoader.load();
        Stage deleteStage = new Stage();
        deleteStage.initModality(Modality.APPLICATION_MODAL);
        deleteStage.setOpacity(1);
        deleteStage.setTitle("Удаление объекта из списка");
        assert root != null;
        deleteStage.setScene(new Scene(root, 600, 400));
        deleteStage.showAndWait();
        printMembers();
    }

    public void handleButtonPrint(ActionEvent actionEvent) {
        printMembers();
    }

    public void printMembers() {
        textArea.clear();
        textArea.appendText(String.format("Список семьи:%n"));
        for (int i = 0; i < member.size(); i++) {
            System.out.println(member.get(i));
            textArea.appendText(String.format(" %d) %s%n", i, member.get(i)));
        }
    }

    public void handleButtonExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleButtonStart(ActionEvent actionEvent) {
        member.add(new Familly("mother", "Alena", 48));
        member.add(new Familly("father", "Ivan", 49));
        member.add(new Familly("son", "Dmitry", 23));
        member.add(new Familly("daughter", "Nastya", 24));

        String text = "Моя программа реализует список членов семьи.\n" +
                "В него входят атрибуты: родство, имя, возраст.\n" +
                "По умолчанию список заполнен 4-мя членами семьи: папа, мама, дочь и сын";
        showInfo(text, "Информация");
        buttonStart.setDisable(true);
        buttonAdd.setDisable(false);
        buttonDelete.setDisable(false);
        buttonPrint.setDisable(false);
    }

    private void showInfo(String text, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void handleButtonDeleteMember(ActionEvent actionEvent) {
        for (int i = 0; i < member.size(); i++) {
            if (member.get(i).name.equals(tfName.getText()) && member.get(i).relationship.equals(tfRelationship.getText()) && member.get(i).age == Integer.parseInt(tfAge.getText())) {
                member.remove(i);
            }
        }
        printMembers();
    }

    public void handleButtonDeletePrint(ActionEvent actionEvent) {
        printMembers();
    }

    public void handleButtonAddMember(ActionEvent actionEvent) {
        member.add(new Familly(tfRelationship.getText(), tfName.getText(), Integer.parseInt(tfAge.getText())));
        printMembers();
    }
}