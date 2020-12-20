package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    Button buttonStart;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonPrint;
    @FXML
    Button buttonDelete;

    public void handleButtonAdd(ActionEvent actionEvent) {
    }

    public void handleButtonDelete(ActionEvent actionEvent) {
    }

    public void handleButtonPrint(ActionEvent actionEvent) {
    }

    public void handleButtonExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleButtonStart(ActionEvent actionEvent) {

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
}
