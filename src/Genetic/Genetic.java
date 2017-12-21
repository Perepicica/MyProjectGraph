package Genetic;

import java.io.IOException;
import java.util.*;

public class Genetic {

    private Gene[] population;
    private Random random = new Random();
    private ArrayList<Integer>[] mVertex;
    private ArrayList<Integer>[] mDistance;
    private List<Integer> vertexList;
    private int begin;
    private int end;

    public Genetic(ArrayList<Integer>[] mVertex1, ArrayList<Integer>[] mDistance1, List<Integer> vertexLisr1, int begin1, int end1) {
        mVertex = mVertex1;
        mDistance = mDistance1;
        vertexList = vertexLisr1;
        begin = begin1;
        end = end1;
    }

    public Pair<List, Integer> mainFunction() {

        population = new Gene[(vertexList.size() + 2) * 2];
        int generationsNumber = mVertex.length * 4;
        createPopulation();
        int i = 0;
        while (i < generationsNumber) {
            nextPopulation();
            mutation();
            i++;
        }
        sort();
        Pair<List, Integer> result = new Pair(getWay(population[0]), population[0].sum);
        return result;
    }

    private void createPopulation() {
        int count = 0;
        while (count < population.length) {
            Gene gene = new Gene(vertexList);
            gene.createGene(begin, end);
            if (Gene.isSolution(gene, mVertex, mDistance)) { //проверяем каждую хромосому, может ли она вообще быть решением
                population[count] = gene;
                count++;
            }
        }
    }


    private void nextPopulation() {
        sort();                                                   //сортирую по сумме, лучшие особи наверх
        int current = 0;                                          // буду скрещивать этот и следующий
        while (current < population.length) {
            for (int i = 0; i < 2; i++) {                          // генерирую двоих детей
                Gene child = crossing(population[current], population[current + 1]);
                if (child.sum < population[current].sum)
                    population[current] = child;                    //если ребенок лучше чем хотябы один из родителей-он его заменет
                else if (child.sum < population[current + 1].sum) population[current + 1] = child;
            }
            current += 2;
        }

    }

    private void sort() {
        int begin = 1;
        Gene intermediate;                                           //просто промежуточная переменная
        while (begin != population.length - 1) {
            int index = begin - 1;                                   //индекс хромосмы с минимальной суммой
            int minSumSort = population[begin - 1].sum;              // хромосома с которой сравниваем и будем менять
            for (int j = begin; j < population.length; j++) {
                if (population[j].sum < minSumSort) {
                    minSumSort = population[j].sum;
                    index = j;
                }
            }
            if (index != begin - 1) {                                //замена на минимальный
                intermediate = population[begin - 1];
                population[begin - 1] = population[index];
                population[index] = intermediate;
            }
            begin++;
        }
    }

    private Gene crossing(Gene current, Gene next) {
        List<Integer> allVertex = new ArrayList<>();
        allVertex.addAll(vertexList);
        List<Integer> addedVertex = new LinkedList<>();
        int momHead = 0;
        int momTail = mVertex.length - 1;
        int fatherHead = 0;
        int fatherTail = mVertex.length - 1;
        Gene child = new Gene(vertexList);
        int childPositionHead = 0;
        int childPositionTail = mVertex.length - 1;

        while (current.chromosome[momHead].getVertex() == next.chromosome[fatherHead].getVertex() && current.chromosome[momHead].getVertex() != end && next.chromosome[fatherHead].getVertex() != end) {
            child.chromosome[childPositionHead++] = new Pair(current.chromosome[momHead].getVertex(), true);
            if (current.chromosome[momHead].getVertex() != begin)
                addedVertex.add(current.chromosome[momHead].getVertex());
            momHead++;
            while (!current.chromosome[momHead].exists() && momHead != mVertex.length - 1)
                momHead++;//ищу совпадение в начале пути
            fatherHead++;
            while (!next.chromosome[fatherHead].exists() && fatherHead != mVertex.length - 1) fatherHead++;
        }
        if (current.chromosome[momHead].getVertex() != end && next.chromosome[fatherHead].getVertex() != end) {
            while (current.chromosome[momTail].getVertex() == next.chromosome[fatherTail].getVertex()) {
                child.chromosome[childPositionTail--] = new Pair(current.chromosome[momTail].getVertex(), true);
                if (current.chromosome[momTail].getVertex() != end)
                    addedVertex.add(current.chromosome[momTail].getVertex());
                momTail--;
                while (!current.chromosome[momTail].exists())
                    momTail--;                        //ищу совпадение в конце пути пути
                fatherTail--;
                while (!next.chromosome[fatherTail].exists()) fatherTail--;
            }
        } else child.chromosome[childPositionTail--] = new Pair(end, true);

        for (int i = 0; i < addedVertex.size(); i++) {
            allVertex.remove(addedVertex.get(i));
        }
        child = completeChild(childPositionHead, childPositionTail, allVertex, child);

        return child;
    }

    private Gene completeChild(int childPositionHead, int childPositionTail, List<Integer> allVertex, Gene child) {

        while (true) {
            Collections.shuffle(allVertex);
            int count = 0;
            if (childPositionHead == childPositionTail)
                child.chromosome[childPositionHead] = new Pair(allVertex.get(count), random.nextBoolean());
            else {
                for (int i = childPositionHead; i <= childPositionTail; i++) {
                    child.chromosome[i] = new Pair(allVertex.get(count++), random.nextBoolean());
                }
            }
            if (Gene.isSolution(child, mVertex, mDistance)) break;
        }


        return child;
    }

    private void mutation() {
        int procent = (2 * population.length) / 10;
        int indexGene = random.nextInt(mVertex.length);
        while (procent!=0) {
            while (true) {
                population[indexGene].chromosome[random.nextInt(mVertex.length - 2) + 1].setExistence(random.nextBoolean());
                if (Gene.isSolution(population[indexGene], mVertex, mDistance)) break;
            }
            procent--;
        }
    }

    private void printPopulation(Gene[] popul) {
        for (int i = 0; i < popul.length; i++) {
            Gene gene = popul[i];
            for (int j = 0; j < gene.chromosome.length; j++) {
                System.out.print("{" + gene.chromosome[j].getVertex() + " , " + gene.chromosome[j].exists() + "}");
            }
            System.out.print(" sum= " + gene.sum);
            System.out.println();
        }
    }

    private static void printArray(ArrayList[] ms) {
        for (int i = 0; i < ms.length; i++) {
            System.out.print(i + "->");
            for (Object n : ms[i]) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

    private List<Integer> getWay(Gene gene) {
        List<Integer> way = new LinkedList<>();
        for (int i = 0; i < gene.chromosome.length; i++) {
            if (gene.chromosome[i].exists()) way.add(gene.chromosome[i].getVertex());
        }
        return way;
    }
}
