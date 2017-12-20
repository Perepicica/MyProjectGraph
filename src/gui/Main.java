package gui;

import Genetic.Pair;
import MainLogic.Beginning;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private Input input;
    private int Width = 200;
    private int Height = 200;

    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 400);
        input = new Input(Width, Height, this);
        add(input);
        setVisible(true);

    }

    public void getInput(String file) {
        try {
            if (Beginning.input(file) == null) {                   //если в файле неверно указаны данныe
                JDialog dialog = new Dialog(this, "Проверьте данные в файле");
                dialog.setVisible(true);
            }else {
                Pair<List,Integer> result = Beginning.input(file);
            }
        } catch (IndexOutOfBoundsException e) {                    //если файл пустой
            JDialog dialog = new Dialog(this, "Файл пуст");
            dialog.setVisible(true);
        } catch (IOException e) {                                   //если путь указан неверно
            JDialog dialog = new Dialog(this, "Неверно указан путь");
            dialog.setVisible(true);
        }
    }


    public static void main(String[] args) {
        Main main = new Main();
    }
}
