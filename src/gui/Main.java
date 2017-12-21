package gui;

import Genetic.Pair;
import MainLogic.Beginning;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private Input input;
    private int Width = 400;
    private int Height = 400;
    GraphResult graphResult = new GraphResult(Width, Height);

    public Main() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,20,Width,Height);
    //    setSize(Width, Height);
        input = new Input(Width, Height, this);
        add(graphResult);
        graphResult.setVisible(false);
        add(input);
        repaint();
        setVisible(true);
    }

    public void getInput(String file) {
        try {
            Pair<List, Integer> result = Beginning.input(file);
            if (result == null) {                   //если в файле неверно указаны данныe
                JDialog dialog = new Dialog(this, "Проверьте данные в файле");
                dialog.setVisible(true);
            } else {
                graphResult.printGraph(result,DatesForGraph.dates(file));
                input.setVisible(false);
                graphResult.setVisible(true);
                repaint();
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
