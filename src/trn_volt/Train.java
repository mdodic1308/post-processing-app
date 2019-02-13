/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trn_volt;

import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public final class Train {
    private ArrayList<String[]> arrayList;
    private String name;
    private double vLt;
    private double vMin;
    private double sumUI;
    private double sumI;
    private String trackTime;
    private String trackTimeMoving;
    
    public String getTrackTime() {
        return trackTime;
    }

    public String getTrackTimeMoving() {
        return trackTimeMoving;
    }

    public String getName() {
        return name;
    }
        
    public double getvLt() {
        return vLt;
    }

    public double getvMin() {
        return vMin;
    }

    public double getSumUI() {
        return sumUI;
    }

    public double getSumI() {
        return sumI;
    }
    
    private Train(){}
    
    public Train(String s) {
        arrayList = new ArrayList<>();
        addLine(s);
        name= String.format(arrayList.get(0)[3]);
    }

    public void calculate(){
        trackTime=findStringTime(arrayList.size());
        trackTimeMoving=findStringTime(findTrackTimeMovingSec());
        double vMin=Double.MAX_VALUE;
        double sumUI=0;
        double sumI=0;
        for (int i =0 ;i<arrayList.size();i++) {
            if(arrayList.get(i)[12].equals("Brk2l") || arrayList.get(i)[12].equals("Brk2s")) continue;
            double current=Math.abs(Double.parseDouble(arrayList.get(i)[6]));
            double voltage=Double.parseDouble(arrayList.get(i)[5]);
            if (voltage<vMin && voltage>0) vMin=voltage;
            sumUI+=voltage*current;
            sumI+=current;
        }
        if (vMin==Double.MAX_VALUE) vMin=0;
        this.vMin=vMin;
        vLt=sumUI/sumI;
        this.sumUI=sumUI;
        this.sumI=sumI;
    }

    
    public String findStringTime(int second){
        int minInt=second/60;
        int hourInt=minInt/60;
        String min= Integer.toString(minInt-hourInt*60);
        String hour= Integer.toString(hourInt);
        if (min.length()==1) min="0"+min;
        if (hour.length()==1) hour="0"+hour;
        String sec=Integer.toString(second-minInt*60);
        if (sec.length()==1) sec="0"+sec;
        return hour+":"+min+":"+sec;
    }
    
    public int findTrackTimeMovingSec(){
        try{
        int trackTimeMovingInt=0;
        for (int i =1 ;i<arrayList.size();i++) {
            if (Integer.parseInt(arrayList.get(i)[11])!=
                    Integer.parseInt(arrayList.get(i-1)[11]))
                trackTimeMovingInt++;
        }
        return trackTimeMovingInt;
        } 
        catch (Exception e){
            return -1;
        }
    }
    
    
    public void addLine(String line){
        arrayList.add(line.split("\\s+"));
    }
        
    @Override
    public String toString() {
        return String.format("Node{name=%12s, vLt=%6.3f, vMin=%6.3f, trackTimeMoving=%6s, trackTime=%6s}"
                ,name,vLt,vMin,trackTimeMoving,trackTime);
    }

}
