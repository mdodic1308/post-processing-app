/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trn_volt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author RSMXD001
 */
public final class Trains {
    private ArrayList<Train> trains;
    private double uMU_sys;
    ArrayList<String[]> strArray;

    public Trains(BufferedReader bufferedReader) throws IOException  {
        createTrains(bufferedReader);
    }
    
    public Trains(BufferedReader bufferedReader,String route, String zone) throws IOException  {
        createTrains(bufferedReader, route, zone);
    }

    public void createTrains(BufferedReader bufferedReader, String route, String zone) throws IOException{
        System.out.println("Route: "+route+", Zone: "+zone);
        Filter filter=new Filter(route,zone);
        for (int i=0; i<7; i++) bufferedReader.readLine();
        trains=new ArrayList<>();
        while (true) {

                String line=bufferedReader.readLine();
                if (line==null) break;
                String[] pom=line.split("\\s+");
                
                if (!filter.check(pom[10], Integer.parseInt(pom[11]))
                       // || pom[2].compareTo("07:00:00")<0 ||
                       // pom[2].compareTo("11:00:00")>0    
                        ) continue;              

                boolean b=false;
                for (int i=0;i<trains.size();i++) {
                    if (pom[3].equals(trains.get(i).getName()))
                    {
                        trains.get(i).addLine(line);
                        b=true;
                        break;
                    }
                }
                if (!b) {
                    trains.add(new Train(line));
                }
            }
        sortByName();
        double sumUI=0;
        double sumI=0;
        for (int i=0; i<trains.size(); i++) {
            trains.get(i).calculate();
            sumUI+=trains.get(i).getSumUI();
            sumI+=trains.get(i).getSumI();
        }
        uMU_sys=sumUI/sumI;
        
        System.out.println(String.format("uMU_%s=%6.3f",zone,uMU_sys));
    }          

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public double getuMU_sys() {
        return uMU_sys;
    }
    
    public void createTrains(BufferedReader bufferedReader) throws IOException{
        for (int i=0; i<7; i++) bufferedReader.readLine();
        trains=new ArrayList<>();
        while (true) {
                String line=bufferedReader.readLine();
                if (line==null) break;
                
                String[] pom=line.split("\\s+");
                
                if (pom[1].equals("Unmapped")
                       // || pom[2].compareTo("07:30:00")<0 
                       // || pom[2].compareTo("09:00:00")>0
                        ) continue;
                
                boolean b=false;
                for (int i=0;i<trains.size();i++) {
                    if (pom[3].equals(trains.get(i).getName()))
                    {
                        trains.get(i).addLine(line);
                        b=true;
                        break;
                    }
                }
                if (!b) {
                    trains.add(new Train(line));
                }
            }
        sortByName();
        double sumUI=0;
        double sumI=0;
        for (int i=0; i<trains.size(); i++) {
            trains.get(i).calculate();
            sumUI+=trains.get(i).getSumUI();
            sumI+=trains.get(i).getSumI();
        }
        uMU_sys=sumUI/sumI;
        System.out.println(String.format("uMU_sys=%6.3f",uMU_sys));
    }         
    
    public void sortByName ( )
{
    String trainName1, trainName2;
    for (int i = 0; i < trains.size ( )-1; i++ )
    {   
        for (int j = 1 ; j < trains.size ( )-i; j++ )
        {           
            trainName1 = trains.get (j-1).getName ( );
            trainName2 = trains.get (j).getName( );
             
            if (trainName1.compareToIgnoreCase (trainName2) > 0)
            {
                Train temp = trains.get (j-1);                    
                trains.set (j-1, trains.get (j));
                trains.set (j, temp);
            }
        }
    }
}
}
