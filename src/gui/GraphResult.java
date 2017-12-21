package gui;

import Genetic.Pair;
import org.jgraph.JGraph;
import org.jgraph.graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class GraphResult extends JPanel {
    public GraphResult(int width, int height) {
        setSize(width, height);
        setVisible(true);
    }

    public void printGraph(Pair<List, Integer> result, Pair<ArrayList<Integer>, ArrayList<Integer>> dates) {
        GraphModel model = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        JGraph graph = new JGraph(model, view);
        int aboutX = 0;
        int aboutY = 0;
        int s = dates.getVertex().size();
        System.out.println("s="+s);
        DefaultGraphCell[] cells = new DefaultGraphCell[dates.getVertex().size()+1];

        for (int i = 0; i < 4; i++) {                                    //вершины
            cells[i] = new DefaultGraphCell(i);
            GraphConstants.setBounds(cells[i].getAttributes(), new Rectangle2D.Double((3 * aboutX + 1) * 20, (aboutY + 1) * 20, 20, 20));
            GraphConstants.setGradientColor(cells[i].getAttributes(), Color.orange);
            GraphConstants.setOpaque(cells[i].getAttributes(), true);
            DefaultPort port0 = new DefaultPort();
            cells[i].add(port0);
            aboutX++;
            if (i > 1) {
                aboutX = 0;
                aboutY += 2;
            }
        }


        DefaultEdge edge = new DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        cells[4] = edge;
        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);


        graph.getGraphLayoutCache().insert(cells);
        add(new JScrollPane(graph));

    }
}

