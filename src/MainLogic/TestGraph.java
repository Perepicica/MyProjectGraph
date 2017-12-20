package MainLogic;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TestGraph { //вывод в файл   //oтветы не инты
    private List<Integer> Dikstratest1 = Arrays.asList(0, 3, 4, 5);   //от 0 до 5
    private List<Integer> Dikstratest2 = Arrays.asList(3, 4, 5, 8); //от 3 до 8
    private List<Integer> Dikstratest3 = Arrays.asList(1, 2, 5, 7);   //от 6 до 4
    private List<Integer> BFtest1 = Arrays.asList(5, 4, 3, 0, 2);//от 5 до 2
    private List<Integer> BFtest2 = Arrays.asList(4, 3, 0, 2, 1);//от 4 до 1
    private List<Integer> BFtest3 = Arrays.asList(3, 0, 2);//от 3 до 2

/*    @Test
    public void Dijkstra() throws IOException {
        assertEquals(Dikstratest1, Beginning.input("src\\inputD", 0, 5));
        assertEquals(Dikstratest2, Beginning.input("src\\inputD", 3, 8));
        assertEquals(Dikstratest3, Beginning.input("src\\inputD", 1, 7));
    }

    @Test
    public void BellmanFord() throws IOException {
        assertEquals(BFtest1, Beginning.input("src\\inputBF", 5, 2));
        assertEquals(BFtest2, Beginning.input("src\\inputBF", 4, 1));
        assertEquals(BFtest3, Beginning.input("src\\inputBF", 3, 2));
    }

    @Test
    public void Genetic() throws IOException{
       // System.out.println(Beginning.input("src\\inputG", 1,5));

    }*/

}
