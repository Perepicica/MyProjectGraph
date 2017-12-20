package OtherAlgoritms;

import Genetic.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BellmanFord {
    private int[] parent;
    int[] distance;

    public Pair<List,Integer> bf(ArrayList<Integer>[] mVertex, ArrayList<Integer>[] mDistance, int begin,int end) {
        int count = 0; //подсчитывает количество пройденных вершин чтобы выйти из цикла
        int INF = Integer.MAX_VALUE;
        distance = new int[mVertex.length];
        Arrays.fill(distance, INF);
        distance[begin] = 0;
        parent = new int[mVertex.length];
        parent[begin] = -1;
        for (int i = 0; i < mVertex.length - 1; i++) {                                 //сколько раз делаем обход
            for (int j = begin; j < mVertex.length; j = (j + 1) % mVertex.length) {    //проходимся по каждой вершине
                for (int k = 0; k < mVertex[j].size(); k++) {                          //по всем смеэным узлам
                    if (distance[j] != INF && distance[j] + mDistance[j].get(k) < distance[mVertex[j].get(k)]) {
                        distance[mVertex[j].get(k)] = distance[j] + mDistance[j].get(k);
                        parent[mVertex[j].get(k)] = j;
                    }
                }
                count++;
                if (count == mVertex.length) {
                    count = 0;
                    break;
                }
            }
        }
        Pair<List, Integer> result = new Pair(way(end), distance[end]);
        return result;
    }

    private ArrayList<Integer> way(int end) {
        ArrayList<Integer> way= new ArrayList<>();
        int e = end;
        while (parent[e] != -1) {
            way.add(e);
            e = parent[e];
        }
        way.add(e);
        Collections.reverse(way);
        return way;
    }
}
