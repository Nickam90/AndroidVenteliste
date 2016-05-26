package com.f2016.dtu.androidventeliste.Utils;

public class UserSession {
    private static DemoSession demoSession;
    private static boolean userAuthenticated;
    private static String patientName;
    private static String patientCode;
    private static int patientRegId;
    private static int queueNumber;
    private static int queueLenght;
    private static int patientTriageId;
    private static int hospitalId;

    public static String getTriageName() {
        switch (patientTriageId) {
            case 0:
                return "rød";
            case 1:
                return "orange";
            case 2:
                return "gul";
            case 3:
                return "grøn";
            case 4:
                return "blå";
            default:
                return null;
        }
    }

    public static String getTriageColorCode() {
        switch (patientTriageId) {
            case 0:
                return "#ef4428";
            case 1:
                return "#FFA500";
            case 2:
                return "#ffff00";
            case 3:
                return "#00ff00";
            case 4:
                return "#008ae6";
            default:
                return null;
        }
    }

    public static void setDemoSession(DemoSession demo) {
        demoSession = demo;
    }

    public static DemoSession getDemoSession() {
        return demoSession;
    }

    public static int getPatientTriageId() {
        return patientTriageId;
    }

    public static void setPatientTriageId(int patientTriage) {
        UserSession.patientTriageId = patientTriage;
    }

    public static String getPatientName() {
        return patientName;
    }

    public static void setPatientName(String patientName) {
        UserSession.patientName = patientName;
    }

    public static String getPatientCode() {
        return patientCode;
    }

    public static void setPatientCode(String patientCode) {
        UserSession.patientCode = patientCode;
    }

    public static int getPatientRegId() {
        return patientRegId;
    }

    public static void setPatientRegId(int patientRegId) {
        UserSession.patientRegId = patientRegId;
    }

    public static int getQueueNumber() {
        return queueNumber;
    }

    public static void setQueueNumber(int queueNumber) {
        UserSession.queueNumber = queueNumber;
    }

    public static int getQueueLenght() {
        return queueLenght;
    }

    public static void setQueueLenght(int queueLenght) {
        UserSession.queueLenght = queueLenght;
    }

    public static int getHospitalId() {
        return hospitalId;
    }

    public static void setHospitalId(int hospitalId) {
        UserSession.hospitalId = hospitalId;
    }

    public static boolean getUserAuth() {
        return userAuthenticated;
    }

    public static void setUserAuth(boolean userAuth) {
        UserSession.userAuthenticated = userAuth;
    }
}
