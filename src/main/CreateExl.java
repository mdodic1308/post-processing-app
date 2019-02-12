/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author RSMXD001
 */
//poi-4.0.0 jar from "http://poi.apache.org/download.html"
import feeder.Nodes;
import monitoring.NodesMonitoring;
import  java.io.*;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import trn_volt.Trains;

public class CreateExl{
    
    private static String fileNameTRNVOLT;
    private static HSSFWorkbook workbookTRNVOLT;
    
    public static void createExcelFEEDER(Nodes nodes, String filePath) {
        try {
            String pom=filePath.substring(0, filePath.length()-4) ;
            String fileName = pom+" results.xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Feeder");  

            HSSFRow rowhead = sheet.createRow((short)3);
            rowhead.createCell(2).setCellValue("FEED");
            rowhead.createCell(3).setCellValue("maxP1");
            rowhead.createCell(4).setCellValue("maxP30");
            rowhead.createCell(5).setCellValue("maxQ1");
            rowhead.createCell(6).setCellValue("maxQ30");
            rowhead.createCell(7).setCellValue("maxS1");
            rowhead.createCell(8).setCellValue("maxS30");
            rowhead.createCell(9).setCellValue("maxTimeS30");

            for (int i=0; i<nodes.nodes.length;i++) {
            HSSFRow row = sheet.createRow((short)i+4);
            row.createCell(2).setCellValue(nodes.nodes[i].getName());
            row.createCell(3).setCellValue(nodes.nodes[i].getMaxP1());
            row.createCell(4).setCellValue(nodes.nodes[i].getMaxP30());
            row.createCell(5).setCellValue(nodes.nodes[i].getMaxQ1());
            row.createCell(6).setCellValue(nodes.nodes[i].getMaxQ30());
            row.createCell(7).setCellValue(nodes.nodes[i].getMaxS1());
            row.createCell(8).setCellValue(nodes.nodes[i].getMaxS30());
            row.createCell(9).setCellValue(nodes.nodes[i].getMaxTimeS30());
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
    
    public static void createExcelTRNVOLT(Trains trains, String filePath) {
        try {
            workbookTRNVOLT = new HSSFWorkbook();
            String pom=filePath.substring(0, filePath.length()-12) ;
            fileNameTRNVOLT = pom+"MUV & MIV results.xls";
            HSSFSheet sheet = workbookTRNVOLT.createSheet("ALL");  
            
            HSSFRow rowUMU_sys = sheet.createRow((short)1);
            rowUMU_sys.createCell(2).setCellValue("U_MU_SYS ");
            rowUMU_sys.createCell(3).setCellValue(String.format("%.4f",trains.getuMU_sys()));

            HSSFRow rowhead = sheet.createRow((short)3);
            rowhead.createCell(2).setCellValue("Train");
            rowhead.createCell(3).setCellValue("MUV");
            rowhead.createCell(4).setCellValue("MIV");
            rowhead.createCell(5).setCellValue("TimeMoving");
            rowhead.createCell(6).setCellValue("Time");

            int i=0;
            for (int j=0; j<trains.getTrains().size();j++) {
                if (trains.getTrains().get(j).getvMin()!=0) {
                HSSFRow row = sheet.createRow((short)i+4);
                row.createCell(2).setCellValue(trains.getTrains().get(j).getName());
                row.createCell(3).setCellValue(trains.getTrains().get(j).getvLt());
                row.createCell(4).setCellValue(trains.getTrains().get(j).getvMin());
                row.createCell(5).setCellValue(trains.getTrains().get(j).getTrackTimeMoving());
                row.createCell(6).setCellValue(trains.getTrains().get(j).getTrackTime());
                i++;
                }
            }

            FileOutputStream fileOut = new FileOutputStream(fileNameTRNVOLT);
            workbookTRNVOLT.write(fileOut);
            fileOut.close();
            workbookTRNVOLT.close();
            System.out.println("Your excel file has been generated!");
            
        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
        
    public static void fillExcelTRNVOLT(Trains trains, String filePath,String zone) {
        try {
            HSSFSheet sheet = workbookTRNVOLT.createSheet("Zone "+zone);  
            
            HSSFRow rowUMU_sys = sheet.createRow((short)1);
            rowUMU_sys.createCell(2).setCellValue("U_MU_SYS "+zone);
            rowUMU_sys.createCell(3).setCellValue(String.format("%.4f",trains.getuMU_sys()));
            
            HSSFRow rowhead = sheet.createRow((short)3);
            rowhead.createCell(2).setCellValue("Train");
            rowhead.createCell(3).setCellValue("U_MU");
            rowhead.createCell(4).setCellValue("U_MIN");
            rowhead.createCell(5).setCellValue("TimeMoving");
            rowhead.createCell(6).setCellValue("Time");

            
            int i=0;
            for (int j=0; j<trains.getTrains().size();j++) {
                if (trains.getTrains().get(j).getvMin()!=0) {
                HSSFRow row = sheet.createRow((short)i+4);
                row.createCell(2).setCellValue(trains.getTrains().get(j).getName());
                row.createCell(3).setCellValue(trains.getTrains().get(j).getvLt());
                row.createCell(4).setCellValue(trains.getTrains().get(j).getvMin());
                row.createCell(5).setCellValue(trains.getTrains().get(j).getTrackTimeMoving());
                row.createCell(6).setCellValue(trains.getTrains().get(j).getTrackTime());
                i++;
                }
            }

            FileOutputStream fileOut = new FileOutputStream(fileNameTRNVOLT);
            workbookTRNVOLT.write(fileOut);
            fileOut.close();
            workbookTRNVOLT.close();
            System.out.println("Your excel file has been updated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }

    public static void createExcelMonitoring(NodesMonitoring nodes, String filePath) {
        try {
            String pom=filePath.substring(0, filePath.length()-4) ;
            String fileName = pom+".xls";
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Monitor");  

            HSSFRow rowhead = sheet.createRow((short)3);
            rowhead.createCell(2).setCellValue("Node");
            
            rowhead.createCell(3).setCellValue("maxI1S");
            rowhead.createCell(4).setCellValue("maxI10S");
            rowhead.createCell(5).setCellValue("maxI30S");
            
            rowhead.createCell(7).setCellValue("Node");
            rowhead.createCell(8).setCellValue("maxI1R");
            rowhead.createCell(9).setCellValue("maxI10R");
            rowhead.createCell(10).setCellValue("maxI30R");
            
            rowhead.createCell(12).setCellValue("Node");
            rowhead.createCell(13).setCellValue("maxI1NS");
            rowhead.createCell(14).setCellValue("maxI10NS");
            rowhead.createCell(15).setCellValue("maxI30NS");

            for (int i=0; i<nodes.nodesMonitoring.length;i++) {
                HSSFRow row = sheet.createRow((short)i+4);
                row.createCell(2).setCellValue("Supply NodeBond "+nodes.nodesMonitoring[i].getName());
                row.createCell(3).setCellValue(nodes.nodesMonitoring[i].getMaxI1S());
                row.createCell(4).setCellValue(nodes.nodesMonitoring[i].getMaxI10S());
                row.createCell(5).setCellValue(nodes.nodesMonitoring[i].getMaxI30S());
            
                row.createCell(7).setCellValue("Neg Sply NodeBond "+nodes.nodesMonitoring[i].getName());
                row.createCell(8).setCellValue(nodes.nodesMonitoring[i].getMaxI1NS());
                row.createCell(9).setCellValue(nodes.nodesMonitoring[i].getMaxI10NS());
                row.createCell(10).setCellValue(nodes.nodesMonitoring[i].getMaxI30NS());      
            
                row.createCell(12).setCellValue("Return NodeBond "+nodes.nodesMonitoring[i].getName());
                row.createCell(13).setCellValue(nodes.nodesMonitoring[i].getMaxI1R());
                row.createCell(14).setCellValue(nodes.nodesMonitoring[i].getMaxI10R());
                row.createCell(15).setCellValue(nodes.nodesMonitoring[i].getMaxI30R());
            }

            FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }
}