package Genetic;

import java.util.*;

public class Gene {
    Pair<Integer, Boolean>[] chromosome;
    private static List<Integer> list = new LinkedList<>();
    int sum;
    private Random random = new Random();

    public Gene(List list1) {
        chromosome = new Pair[list1.size() + 2];
        list = list1;//Список вершин
        sum = Integer.MAX_VALUE;
    }


    public void createGene(int begin, int end) {

        Collections.shuffle(list);
        chromosome[0] = new Pair(begin, true);
        for (int i = 1; i < chromosome.length - 1; i++) {
            chromosome[i] = new Pair(list.get(i - 1), random.nextBoolean());
        }
        chromosome[chromosome.length - 1] = new Pair(end, true);
    }

    public static boolean isSolution(Gene gene, ArrayList<Integer
            >[] mVertex, ArrayList<Integer>[] mDistance) { //ПРОВЕРИТЬ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int sum = 0;     // сумма, которая посчитается, если хромосома явдяется хоть каким-нибудь решением(плохим\хорошим)
        int current = 0;             //первая и следующая хромосомы которые участвую в формировании пути(если exists == true)
        int next = 1;

        while (current != gene.chromosome.length - 1) {
            while (!gene.chromosome[next].exists()) next++; //ищем слудющую участвующую хромосому
            if (mVertex[gene.chromosome[current].getVertex()].contains(gene.chromosome[next].getVertex())) { //если такая дуга есть
                int i = mVertex[gene.chromosome[current].getVertex()].indexOf(gene.chromosome[next].getVertex());
                sum += mDistance[gene.chromosome[current].getVertex()].get(i);

            } else return false;  //если есть хотябы одна несуществующуя дуга-хромосома не подходит
            current = next;
            next += 1;
        }
        gene.sum = sum;
        return true;
    }
}