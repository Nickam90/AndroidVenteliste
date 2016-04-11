package com.f2016.dtu.androidventeliste.Utils;

import android.app.Application;

public class UserSession extends Application{
    private static String patientName;
    private static String patientCode;
    private static int queueNumber;
    private static int queueLenght;
    private static String patientTriage;

    public static String getPatientTriage() {
        return patientTriage;
    }

    public static void setPatientTriage(String patientTriage) { UserSession.patientTriage = patientTriage; }

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

    public static int getQueueNumber() {
        return queueNumber;
    }

    public static void setQueueNumber(int queueNumber) { UserSession.queueNumber = queueNumber; }

    public static int getQueueLenght() {
        return queueLenght;
    }

    public static void setQueueLenght(int queueLenght) {
        UserSession.queueLenght = queueLenght;
    }
}
