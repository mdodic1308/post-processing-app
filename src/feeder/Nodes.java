/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feeder;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public final class Nodes {
    public Node [] nodes;
    private int nodeNumber;
    ArrayList<String[]> strArray;

    public Nodes(BufferedReader bufferedReader) throws IOException  {
        createNodes(bufferedReader);
    }
    private Nodes(){}

    public void createNodes(BufferedReader bufferedReader) throws IOException{
        for (int i =0; i<7;i++) bufferedReader.readLine();
        
        nodeNumber = getNodeNumber(bufferedReader);
        
        nodes= new Node[nodeNumber];
        for (int i=0; i<nodes.length; i++) {
            nodes[i]=new Node();
        }
        boolean b=true;
        while (b) {
            for (Node node : nodes) {
                String line=bufferedReader.readLine();
                if (line==null) {
                    b=false;
                    break;
                }
                node.addLine(line);
            }
        }            
        for (Node node: nodes){
            node.callMaxMethods();
        }
    }
    
    
    public int getNodeNumber(BufferedReader br) throws IOException{
        //BufferedReader reader=new BufferedReader(bufferedReader);
        String first;
        nodeNumber=1;
        br.mark(0);
        first = br.readLine().split("\\s+")[1];
        while (true) {
            if (br.readLine().split("\\s+")[1].equals(first)) break;
            nodeNumber++;
        }
        br.reset();
        return nodeNumber;
    }
}
