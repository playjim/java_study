package calcStudy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Exam {
    @FXML
    private Label labelStudyStart;
    @FXML
    private Label labelStudy;
    @FXML
    private Button buttonStartStudy;
    @FXML
    private Button buttonEnter;
    @FXML
    private TextField tfEnter;
    private static int fromCount = 0;
    private static int sum;
    private static int arightCount = 0;

    public void handleButtonStartStudy(ActionEvent actionEvent) {
        String text = String.format("%s %s, Добро пожаловать на экзамен!%n" +
                        "Чтобы получить требуемую оценку тебе нужно решить:%n" +
                        "%d из %d примеров верно для оценки 5%n" +
                        "%d из %d примеров верно для оценки 4%n" +
                        "%d из %d примеров верно для оценки 3%n" +
                        "Удачи!",
                Start.lastName, Start.firstName,
                counting(Ball.percentBallA), Ball.countBall,
                counting(Ball.percentBallB), Ball.countBall,
                counting(Ball.percentBallC), Ball.countBall);
        showInfo(text, "Информация");
        buttonStartStudy.setDisable(true);
        outExample(fromCount, Ball.countBall);
        buttonEnter.setDisable(false);
    }

    public int counting(int percentBall) {
        return (Ball.countBall * percentBall) / 100;
    }

    public void outExample(int from, int to) {
        if (from <= to - 1) {
            int a = Gen_Random();
            int b = Gen_Random();
            sum = a * b;
            labelStudy.setText(a + " * " + b + " = ");
            fromCount++;
            labelStudyStart.setText(String.format("Пример %d из %d:", fromCount, Ball.countBall));
        } else {
            switch (countResultBall()) {
                case 5:
                    showInfo(String.format("Поздравляю тебя %s!%n" +
                            "Твоя оценка 5!%n" +
                            "Твои знания таблицы умножения поразительны! ", Start.firstName), "Экзамен окончен!");
                    break;
                case 4:
                    showInfo(String.format("Поздравляю тебя %s!%n" +
                            "Твоя оценка 4!%n" +
                            "У тебя хорошие знания таблицы умножения.", Start.firstName), "Экзамен окончен!");
                    break;
                case 3:
                    showInfo(String.format("%s%n" +
                            "Твоя оценка 3.%n" +
                            "Тебе следует улучшить свои знания таблицы умножения.", Start.firstName), "Экзамен окончен!");
                    break;
                case 2:
                    showInfo(String.format("%s%n" +
                            "Твоя оценка 2!%n" +
                            "Твои знания таблицы умножения отвратительны!", Start.firstName), "Экзамен окончен!");
                    break;
            }
            labelStudy.setText("Экзамен окончен!");
            buttonEnter.setDisable(true);
        }
    }

    private int countResultBall() {
        if (arightCount >= counting(Ball.percentBallA)) {
            return 5;
        } else if (arightCount >= counting(Ball.percentBallB)) {
            return 4;
        } else if (arightCount >= counting(Ball.percentBallC)) {
            return 3;
        } else return 2;
    }

    private void checkResult(int answerStudent) {
        if (sum == answerStudent) {
            System.out.println("Верно!");
            arightCount += 1;
            System.out.println("Правильных ответов " + arightCount);
        }
    }

    private void showInfo(String text, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private int Gen_Random() {
        int max = 9, min = 1;
        max -= min;
        double gen = (Math.random() * ++max) + min;
        return (int) gen;
    }

    public void handleButtonEnter(ActionEvent actionEvent) {
        boolean state = true;
        try {
            checkResult(Integer.parseInt(tfEnter.getText()));
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка!");
            alert.setHeaderText("Невернеый формат ввода!");
            alert.setContentText("Пожалуста введите числа.");
            alert.showAndWait();
            state = false;
        }
        if (state) {
            tfEnter.setText("");
            outExample(fromCount, Ball.countBall);
        }
    }
}
