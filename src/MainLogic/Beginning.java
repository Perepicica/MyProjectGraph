package MainLogic;

import Genetic.Genetic;
import OtherAlgoritms.BellmanFord;
import OtherAlgoritms.Dijkstra;
import Genetic.Pair;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Beginning {

    private enum AlgoritmType {
        Genetic, Dijkstra, BellmanFord
    }

    public static Pair<List,Integer> input(String file1) throws IOException {
        Pair<List, Integer> result;
        AlgoritmType algoritmType = AlgoritmType.Dijkstra;
        ArrayList<Integer>[] mVertex;
        ArrayList<Integer>[] mDistance;
        List<Integer> vertexList = new LinkedList<>();
        int n;
        int begin;
        int end;

        try {
            List<String> lines = new ArrayList<>();
            String s;
            String[] ss;
            BufferedReader reader = new BufferedReader(new FileReader(file1));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            s = lines.get(0);
            ss = s.split("\\s");

            n = Integer.parseInt(ss[0]);
            int m = Integer.parseInt(ss[1]);
            mVertex = new ArrayList[n];
            mDistance = new ArrayList[n];
            s = lines.get(1);
            ss = s.split("\\s");
            begin = Integer.parseInt(ss[0]);
            end = Integer.parseInt(ss[1]);
            for (int i = 0; i < n; i++) {
                mVertex[i] = new ArrayList();
                mDistance[i] = new ArrayList();
            }

            int i = 1;               //выбираю алгоритм
            if (n > 13) algoritmType = AlgoritmType.Genetic;
            else {
                while (i < m) {
                    s = lines.get(i + 1);
                    ss = s.split("\\s");
                    if (Integer.parseInt(ss[2]) < 0) {
                        algoritmType = AlgoritmType.BellmanFord;
                        break;
                    }
                    i++;
                }
            }

            int j = 1;    //заполняю массивы
            while (j < m) {
                s = lines.get(j + 1);
                ss = s.split("\\s");
                int v = Integer.parseInt(ss[0]);
                int w = Integer.parseInt(ss[1]);
                int z = Integer.parseInt(ss[2]);
                if (!vertexList.contains(v) && v != begin && v != end) vertexList.add(v); //для генетического
                if (!vertexList.contains(w) && w != begin && w != end) vertexList.add(w);
                mVertex[v].add(w);
                if (algoritmType != AlgoritmType.BellmanFord) mVertex[w].add(v);
                mDistance[v].add(z);
                if (algoritmType != AlgoritmType.BellmanFord) mDistance[w].add(z);
                j++;
            }

        } catch (NumberFormatException e) {
            return null;
        } catch (IndexOutOfBoundsException e){
            throw new IndexOutOfBoundsException();
        }catch (FileNotFoundException e){
            throw new IOException();
        }

        if (algoritmType == AlgoritmType.Genetic) {
            System.out.println("Генетический алгоритм: ");
            Genetic genetic = new Genetic(mVertex, mDistance, vertexList, begin, end);
            result = genetic.mainFunction();

            prn(result);
            return result;
        } else if (algoritmType == AlgoritmType.BellmanFord) {
            System.out.println("Алгоритм Баллмана-Форда:");
            BellmanFord bellmanFord = new BellmanFord();
            result = bellmanFord.bf(mVertex, mDistance, begin, end);
            return result;
        } else {
            System.out.println("Алгоритм Дейкстры:");
            Dijkstra dijkstra = new Dijkstra();
            result = dijkstra.djk(mVertex, mDistance, begin, end);
            return result;
        }
    }
    private static void prn(Pair<List,Integer> pair){
        for (int i = 0; i < pair.getVertex().size() ; i++) {
            System.out.print(pair.getVertex().get(i)+ " ");
        }
        System.out.println();
        System.out.println(pair.exists());
    }
}
