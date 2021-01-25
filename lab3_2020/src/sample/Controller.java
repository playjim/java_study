package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Controller {
    public Button buttonSave;
    public Button buttonRead;
    public Button buttonStart;
    public Button buttonAdd;
    public Button buttonDelete;
    public TextField tfName;
    public TextField tfRelationship;
    public TextField tfAge;
    public TableColumn<Family, String> tcRelationship;
    public TableColumn<Family, String> tcName;
    public TableColumn<Family, String> tcAge;
    public TableView<Family> tvTable = new TableView<>(member);
    static ObservableList<Family> member = FXCollections.observableArrayList(
            new Family("мама", "Елена", 55),
            new Family("папа", "Яков", 60),
            new Family("сын", "Дмитрий", 23),
            new Family("дочь", "Анастасия", 15)
    );

    public void handleButtonAdd(ActionEvent actionEvent) {
        member.add(new Family(tfRelationship.getText(), tfName.getText(), Integer.parseInt(tfAge.getText())));
        tfRelationship.setText(null);
        tfName.setText(null);
        tfAge.setText(null);
    }

    public void handleButtonDelete(ActionEvent actionEvent){
        int selectedIndex = tvTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tvTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }

    public void handleButtonExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void handleButtonStart(ActionEvent actionEvent) {
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tcRelationship.setCellValueFactory(new PropertyValueFactory<>("relationship"));
        ObservableList<Family> list = member;
        tvTable.setItems(list);
        String text = "Моя программа реализует список членов семьи.\n" +
                "В него входят атрибуты: родство, имя, возраст.\n" +
                "По умолчанию список заполнен 4-мя членами семьи:\n" +
                "папа, мама, дочь и сын.";
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Информация");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
        buttonStart.setDisable(true);
        buttonAdd.setDisable(false);
        buttonDelete.setDisable(false);
        buttonSave.setDisable(false);
        buttonRead.setDisable(false);
    }

    public void handleButtonRead(ActionEvent actionEvent) throws IOException {
        File f;
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter;
        extFilter = new FileChooser.ExtensionFilter("txt", "*.txt");
        fc.getExtensionFilters().addAll(extFilter);
        f = fc.showOpenDialog(null);
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8.name());
        BufferedReader br = new BufferedReader(isr);
        String str = "";
        String relationship;
        String name;
        String age;
        member.clear();
        while (str != null) {
            str = br.readLine();
            if (str == null) break;
            Scanner sc = new Scanner(str);
            relationship = sc.next();
            name = sc.next();
            age = sc.next();
            member.add(new Family(relationship, name, Integer.parseInt(age)));
        }
        br.close();
        isr.close();
        fis.close();
    }

    public void handleButtonSave(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt", "*.txt");
        fc.getExtensionFilters().addAll(extFilter);
        File fOut = fc.showSaveDialog(null);
        FileOutputStream fos = new FileOutputStream(fOut);
        OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8.name());
        BufferedWriter bw = new BufferedWriter(osr);
        for (Family s : member) {
            String text = s.toString() + "\n";
            bw.write(text);
        }
        bw.close();
        osr.close();
        fos.close();
    }
}