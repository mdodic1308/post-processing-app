/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public final class NodesMonitoring {
    public NodeMonitoring [] nodesMonitoring;
    private int nodeNumber;
    ArrayList<String[]> strArray;

    public NodesMonitoring(BufferedReader bufferedReader) throws IOException  {
        createNodes(bufferedReader);
    }
    private NodesMonitoring(){}

    public void createNodes(BufferedReader bufferedReader) throws IOException{
        for (int i =0; i<7;i++) bufferedReader.readLine();
        
        nodeNumber = getNodeNumber(bufferedReader);
        
        nodesMonitoring= new NodeMonitoring[nodeNumber];
        for (int i=0; i<nodesMonitoring.length; i++) {
            nodesMonitoring[i]=new NodeMonitoring();
        }
        boolean b=true;
        while (b) {
            for (NodeMonitoring node : nodesMonitoring) {
                String line=bufferedReader.readLine();
                if (line==null) {
                    b=false;
                    break;
                }
                node.addLine(line);
            }
        }            
        for (NodeMonitoring node: nodesMonitoring){
            node.callMaxMethods();
        }
    }
    
    
    public int getNodeNumber(BufferedReader br) throws IOException{
        //BufferedReader reader=new BufferedReader(bufferedReader);
        String first;
        nodeNumber=1;
        br.mark(0);
        String[]tmp=br.readLine().split("\\s+");
        first = (tmp.length==10)? tmp[4]: tmp[5];
        while (true) {
            tmp=br.readLine().split("\\s+");
            String tek=(tmp.length==10)? tmp[4]: tmp[5];
            if (tek.equals(first)) break;
            nodeNumber++;
        }
        br.reset();
        return nodeNumber;
    }
}

