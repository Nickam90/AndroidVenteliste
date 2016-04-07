package com.f2016.dtu.androidventeliste;

import android.app.Application;

/**
 * Created by nicka on 12-03-2016.
 */
public class UserSession extends Application{
    private static String patientName;
    private static String patientCode;
    private static int queueNumber;
    private static int queueLenght;


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
