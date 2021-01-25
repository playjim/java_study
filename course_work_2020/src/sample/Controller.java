package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeSet;

public class Controller {
    public Button buttonRead;
    public Button buttonSelect;
    public Label labelDir;
    public TextField textField;
    public Button buttonShow;
    public Button buttonGetFile;
    public TextArea textAreaInput;
    public TextArea textAreaOut;
    static MyTokenTree tree = new MyTokenTree();
    File f;
    BufferedReader br;
    static int count = 0;
    public void handleSelectFile(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter;
        extFilter = new FileChooser.ExtensionFilter("txt", "*.txt");
        fc.getExtensionFilters().addAll(extFilter);
        f = fc.showOpenDialog(null);
        labelDir.setText(f.toString());
    }


    public void handleReadFile(ActionEvent actionEvent) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8.name());
        br = new BufferedReader(isr);
        String str = "";
        while (str != null) {
            str = br.readLine();
            if (str == null) break;
            parserLine(str);
            str += "\n";
            textAreaInput.appendText(str);
            //System.out.println(str);
        }
        br.close();
        isr.close();
        fis.close();
    }

    public void parserLine(String str) {
        String token;
        str = str.toLowerCase();
        Scanner sc = new Scanner(str);
        while (true) {
            try {
                token = sc.next();
                if (filter(token) != null) {
                    MyToken mt = new MyToken(filter(token));
                    tree.add(mt);
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }
    }

    public String filter(String token) {
        StringBuilder str = new StringBuilder();
        if (token.length() < 2) {
            return null;
        }
        char[] result = token.toCharArray();
        ArrayList<Character> chr = new ArrayList<>();
        for (char c : result) {
            chr.add(c);
        }
        for (int i = 0; i < 2; i++) {
            if ((int) chr.get(0) < 97 || (int) chr.get(0) > 122) {
                chr.remove(0);
                if (chr.size() == 0) {
                    return null;
                }
            }
            if ((int) chr.get(chr.size() - 1) < 97 || (int) chr.get(chr.size() - 1) > 122) {
                chr.remove(chr.size() - 1);
                if (chr.size() == 0) {
                    return null;
                }
            }
        }
        if (chr.size() < 2) {
            return null;
        }
        for (char c : chr) {
            if ((int) c < 97 || (int) c > 122) {
                return null;
            }
        }
        for (char c : chr) {
            str.append(c);
        }
        return str.toString();
    }


    static class MyToken implements Comparable<MyToken> {
        String token;

        MyToken(String token) {
            this.token = token;
        }

        public String toString() {
            return token;
        }

        @Override
        public int compareTo(MyToken o) {
            return token.compareTo(o.token);
        }
    }

    static class MyTokenTree {
        TreeSet<MyToken> tokens = new TreeSet<>();

        void add(MyToken md) {
            tokens.add(new MyToken(md.token));
        }

        void show(TextArea textArea) {
            for (MyToken o : tokens) {
                count++;
                //System.out.println(o.toString());
                textArea.appendText(o.toString());
                textArea.appendText("\n");
            }
        }
    }

    public void handleShow(ActionEvent actionEvent) {
        tree.show(textAreaOut);
        textField.setText(Integer.toString(count));
    }

    public void handleGetFile(ActionEvent actionEvent) throws IOException {
        FileChooser fc = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt", "*.txt");
        fc.getExtensionFilters().addAll(extFilter);
        File fOut = fc.showSaveDialog(null);
        FileOutputStream fos = new FileOutputStream(fOut);
        OutputStreamWriter osr = new OutputStreamWriter(fos, StandardCharsets.UTF_8.name());
        BufferedWriter bw = new BufferedWriter(osr);
        String text = textAreaOut.getText();
        bw.write(text);
        bw.close();
        osr.close();
        fos.close();
    }
}
