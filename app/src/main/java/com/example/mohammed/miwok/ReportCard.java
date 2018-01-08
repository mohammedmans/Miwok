package com.example.mohammed.miwok;

/**
 * Created by M.M.M.M on 5/30/2017.
 */


public class ReportCard  {

    private int studentId;
    private String studentName;
    private int softwareGrade;
    private int information_technologyGrade;
    private int multimediaGrade;
    private int operating_systemGrade;
    private int information_systemGrade;
    private int databaseGrade;

    // default constructor
    public ReportCard(){
        studentId = 0;
        studentName = "";
        softwareGrade = 0;
        information_systemGrade = 0;
        information_technologyGrade = 0;
        multimediaGrade = 0 ;
        operating_systemGrade = 0;
        databaseGrade = 0;
    }

    // copy constructor
    public ReportCard(int studentId, String studentName, int softwareGrade, int information_technologyGrade,
                      int multimediaGrade, int operating_systemGrade, int information_systemGrade, int databaseGrade) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.softwareGrade = softwareGrade;
        this.information_technologyGrade = information_technologyGrade;
        this.multimediaGrade = multimediaGrade;
        this.operating_systemGrade = operating_systemGrade;
        this.information_systemGrade = information_systemGrade;
        this.databaseGrade = databaseGrade;
    }


    // Setters & getters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSoftwareGrade() {
        return softwareGrade;
    }

    public void setSoftwareGrade(int softwareGrade) {
        this.softwareGrade = softwareGrade;
    }

    public int getInformation_technologyGrade() {
        return information_technologyGrade;
    }

    public void setInformation_technologyGrade(int information_technologyGrade) {
        this.information_technologyGrade = information_technologyGrade;
    }

    public int getMultimediaGrade() {
        return multimediaGrade;
    }

    public void setMultimediaGrade(int multimediaGrade) {
        this.multimediaGrade = multimediaGrade;
    }

    public int getOperating_systemGrade() {
        return operating_systemGrade;
    }

    public void setOperating_systemGrade(int operating_systemGrade) {
        this.operating_systemGrade = operating_systemGrade;
    }

    public int getInformation_systemGrade() {
        return information_systemGrade;
    }

    public void setInformation_systemGrade(int information_systemGrade) {
        this.information_systemGrade = information_systemGrade;
    }

    public int getDatabaseGrade() {
        return databaseGrade;
    }

    public void setDatabaseGrade(int databaseGrade) {
        this.databaseGrade = databaseGrade;
    }

    // calculate percentage
    public double studentPercentage(){
        double total = 0.0 ;
        total+= databaseGrade;
        total+= information_systemGrade;
        total+= information_technologyGrade;
        total+= multimediaGrade;
        total+= operating_systemGrade;
        total+= softwareGrade;

        return ((total/600)*100);
    }


    @Override
    public String toString() {
        return "ReportCard \n\n"
                +  "<---------    Student Info  ----------> " + "\n"
                + "studentId: " + studentId + "\n"
                + "studentName: " + studentName + "\n\n"
                + "<---------  Percentage: " + studentPercentage()+"%   ---------->"+"\n\n"
                + "<---------    Subject Grades  ----------> " + "\n"
                + "softwareGrade: "+ softwareGrade + "\n"
                + "information_technologyGrade: " + information_technologyGrade + "\n"
                + "multimediaGrade: "+ multimediaGrade + "\n"
                + "operating_systemGrade: " + operating_systemGrade + "\n"
                + "information_systemGrade: "+ information_systemGrade + "\n"
                + "databaseGrade: " + databaseGrade ;
    }



























}

