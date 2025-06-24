/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author Noor
 */
public class Report {
    private int reportID;
    private String reportDate;
    private String reportInfo;
    private String reportCreator;
    private String reportTitle;

    public Report() {
    }

    public Report(int reportID, String reportDate, String reportInfo, String reportCreator, String reportTitle) {
        this.reportID = reportID;
        this.reportDate = reportDate;
        this.reportInfo = reportInfo;
        this.reportCreator = reportCreator;
        this.reportTitle = reportTitle;
    }
    

    //Getters
    public int getReportID() {
        return reportID;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public String getReportCreator() {
        return reportCreator;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    //Setters
    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public void setReportCreator(String reportCreator) {
        this.reportCreator = reportCreator;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }
    
    
//    should this display a specific report or all of them 
   public void generateReport(Report report){      // Not yet
        DB.getInstance().GenerateReport(report);
    }
    
    
}
