package gui;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.awt.*;

public class GraphResult extends JPanel{
    public GraphResult(){
        setSize(500,500);
        mxGraph graph=new mxGraph();
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
       //graphComponent.setPreferredSize(new Dimension(400,400));
        //getContentPane
        Object parent = graph.getDefaultParent();
        graph.getModel().beginUpdate();
        graph.insertVertex(parent,null,"1",30,80,100,50);
        graph.getModel().endUpdate();
        setVisible(true);
    }
}

