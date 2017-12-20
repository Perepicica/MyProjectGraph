package OtherAlgoritms;

import Genetic.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
    private boolean[] used;
    private int[] parent;
    private int[] distance;

    public Pair<List,Integer> djk(ArrayList<Integer>[] mVertex, ArrayList<Integer>[] mDistance, int begin,int end) {
        used = new boolean[mVertex.length];
        parent = new int[mVertex.length];
        Arrays.fill(parent, -1);
        distance = new int[mVertex.length];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[begin] = 0;

        for (int i = 0; i < mVertex.length; i++) {
            int a = -1;
            for (int j = 0; j < mVertex.length; j++) {
                if (!used[j] && (a == -1 || distance[j] < distance[a]))
                    a = j;
            }
            for (int j = 0; j < mVertex[a].size(); j++) {
                if (distance[a] + (int) mDistance[a].get(j) < distance[(int) mVertex[a].get(j)]) {
                    distance[(int) mVertex[a].get(j)] = distance[a] + (int) mDistance[a].get(j);
                    parent[(int) mVertex[a].get(j)] = a;
                }
            }
            used[a] = true;
        }
        Pair<List, Integer> result = new Pair(way(end), distance[end]);
        return result;
    }

    private ArrayList<Integer> way(int end) {
        ArrayList way = new ArrayList();
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
