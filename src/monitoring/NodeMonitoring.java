/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring;

import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public class NodeMonitoring {
    private ArrayList<String[]> arrayList;
    private String name;
    private double maxI1S;
    private double maxI10S;
    private double maxI30S;
    private double maxI1R;
    private double maxI10R;
    private double maxI30R;
    private double maxI1NS;
    private double maxI10NS;
    private double maxI30NS;
    

     
    public void callMaxMethods(){
        findName();
        findMaxI1S();
        findMaxI10S();
        findMaxI30S();
        findMaxI1R();
        findMaxI10R();
        findMaxI30R();
        findMaxI1NS();
        findMaxI10NS();
        findMaxI30NS();
    }

    public double getMaxI1S() {
        return maxI1S;
    }

    public double getMaxI10S() {
        return maxI10S;
    }

    public double getMaxI30S() {
        return maxI30S;
    }

    public double getMaxI1R() {
        return maxI1R;
    }

    public double getMaxI10R() {
        return maxI10R;
    }

    public double getMaxI30R() {
        return maxI30R;
    }

    public double getMaxI1NS() {
        return maxI1NS;
    }

    public double getMaxI10NS() {
        return maxI10NS;
    }

    public double getMaxI30NS() {
        return maxI30NS;
    }

    public String getName() {
        return name;
    }

    
    public NodeMonitoring() {
        arrayList = new ArrayList<>();
    }

    private void findMaxI1S(){ maxI1S= findMax(6,60,"Supply"); }
    private void findMaxI10S(){ maxI10S= findMax(6,600,"Supply"); }
    private void findMaxI30S(){ maxI30S= findMax(6,1800,"Supply"); }
    private void findMaxI1R(){ maxI1R= findMax(6,60,"Return"); }
    private void findMaxI10R(){ maxI10R= findMax(6,600,"Return"); }
    private void findMaxI30R(){ maxI30R= findMax(6,1800,"Return"); }
    private void findMaxI1NS(){ maxI1NS= findMax(6,60,"NegSply"); }
    private void findMaxI10NS(){ maxI10NS= findMax(6,600,"NegSply"); }
    private void findMaxI30NS(){ maxI30NS= findMax(6,1800,"NegSply"); }
    public void findName(){ name= String.format("%4s",arrayList.get(0)[4]); }
    
    public void addLine(String line){
        String[] pom=new String[10];    
        if  (line.split("\\s+").length==10) pom=line.split("\\s+");
        else if (line.split("\\s+").length==11)
        {
            String[] str=line.split("\\s+");
            pom[0]=str[0];
            pom[1]=str[1];
            pom[2]=str[2]+str[3];
            for (int i=4; i<10;i++)
                pom[i]=str[i+1];
        }
        arrayList.add(pom);
    }
        
    public double findMax(int pos, int len,String path){
        double sum=0;
        int tmp=0;
        int lenPom=0;
        ArrayList<Double> arr=new ArrayList<>();
        
        while(lenPom<len && tmp<arrayList.size()){
            if (arrayList.get(tmp)[2].equals(path)){
            sum+=Math.pow(Double.parseDouble(arrayList.get(tmp)[pos]),2);
            arr.add(Math.pow(Double.parseDouble(arrayList.get(tmp)[pos]),2));
            lenPom++;}
            tmp++;
        }
        
        double max=Math.sqrt(sum/len);
        for (int i=tmp; i<arrayList.size(); i++) {
            if (arrayList.get(i)[2].equals(path)) {
                sum-=arr.get(0);
                sum+=Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2);
                arr.remove(0);
                arr.add(Math.pow(Double.parseDouble(arrayList.get(i)[pos]),2));
                if (Math.sqrt(sum/len)>max)
                    max=Math.sqrt(sum/len);
            }
        }
        return max*1000; 
    }

    @Override
    public String toString() {
        return String.format("Node{name=%8s, maxI1S=%6.2f, maxI10S=%6.2f, maxI30S=%6.2f, "
                + "maxI1R=%6.2f, maxI10R=%6.2f, maxI30R=%6.2f, "
                +"maxI1R=%6.2f, maxI10R=%6.2f, maxI30R=%6.2f"
                ,name,maxI1S,maxI10S,maxI30S,maxI1R,maxI10R,maxI30R,maxI1NS,maxI10NS,maxI30NS);
    }
}
