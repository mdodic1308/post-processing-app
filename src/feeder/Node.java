/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package feeder;

import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public class Node {
    private ArrayList<String[]> arrayList;
    private String name;
    private double maxP1;
    private double maxP30;
    private double maxQ1;    
    private double maxQ30;    
    private double maxS1;   
    private double maxS30;
    private String maxTimeS30;

     
    public void callMaxMethods(){
        findName();
        findMaxP1();
        findMaxP30();
        findMaxQ1();
        findMaxQ30();
        findMaxS1();
        findMaxSAndTime30();
    }

    public String getName() {
        return name;
    }

    
    public double getMaxP1() {
        return maxP1;
    }

    public double getMaxP30() {
        return maxP30;
    }

    public double getMaxQ1() {
        return maxQ1;
    }

    public double getMaxQ30() {
        return maxQ30;
    }

    public double getMaxS1() {
        return maxS1;
    }

    public double getMaxS30() {
        return maxS30;
    }

    public String getMaxTimeS30() {
        return maxTimeS30;
    }
    
    public Node() {
        arrayList = new ArrayList<>();
    }

    private void findMaxP1(){ maxP1= findMax(4,60); }
    private void findMaxP30(){ maxP30= findMax(4,1800); }
    private void findMaxQ1(){ maxQ1= findMax(5,60); }
    private void findMaxQ30(){ maxQ30= findMax(5,1800); }
    private void findMaxS1(){ maxS1= findMax(6,60); }
    public void findName(){ name= String.format("%4s %3s",arrayList.get(0)[1],arrayList.get(0)[2]); }
    
    public void addLine(String line){
        String[] pom=new String[12];    
        if  (line.split("\\s+").length==12) pom=line.split("\\s+");
        else if (line.split("\\s+").length==11)
        {
            String[] str=line.split("\\s+");
            pom[0]=str[0];
            pom[1]=str[1];
            pom[2]="";
            for (int i=3; i<12;i++)
                pom[i]=str[i-1];
        }
        arrayList.add(pom);
    }
        
    public double findMax(int pos, int len){
        double sum=0;
        for (int i=0; i<len;i++){
            sum+=Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2);
        }
        double max=Math.sqrt(sum/len);
        for (int i=len; i<arrayList.size(); i++) {
            sum-=Math.pow(Double.parseDouble(arrayList.get(i-len)[pos]),2);
            sum+=Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2);
            if (Math.sqrt(sum/len)>max)
                    max=Math.sqrt(sum/len);
        }
        return max; 
    }
    
    public void findMaxSAndTime30(){
        int len=1800;
        int pos=6;
        double sum=0;
        String time="";
        for (int i=0; i<len;i++){
            sum+=Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2);
        }
        double max=Math.sqrt(sum/len);
        for (int i=len; i<arrayList.size(); i++) {
            sum-=Math.pow(Double.parseDouble(arrayList.get(i-len)[pos]),2);
            sum+=Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2);
            if (Math.sqrt(sum/len)>max) {
                    max=Math.sqrt(sum/len);
                    time=arrayList.get(i)[3];
            }
        }
        maxS30=max;
        maxTimeS30=time;
    }

    @Override
    public String toString() {
        return String.format("Node{name=%8s, maxP1=%6.2f, maxP30=%6.2f, maxQ1=%6.2f, "
                + "maxQ30=%6.2f, maxS1=%6.2f, maxS30=%6.2f, maxTimeS30=%8s}"
                ,name,maxP1,maxP30,maxQ1,maxQ30,maxS1,maxS30,maxTimeS30);
    }

}
