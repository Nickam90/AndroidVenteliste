package com.f2016.dtu.androidventeliste.Utils;

/**
 * Created by nicka on 17-03-2016.
 */
import android.os.AsyncTask;
import android.util.Log;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DataAccess {
    private static String standardUrl = "https://130.226.195.241:8181/RestApiAndroidVenteListe-1/webresources/dtu.restapiandroidventeliste.";
    private static String hospQueueUrl = "queue/getHospQueue/"; ///getHospQueue/{hospId}
    private static String patientHospUrl = "queue/getPatientHosp/"; //getPatientHosp/{regId}
    private static String patientInQueueUrl = "queue/getPatientInQueue/"; //getPatientInQueue/{regId}
    private static String loginUrl = "patientreg/login/";

    public void loginUser(final String code) {
        final String userCode = code;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String data = hentUrlForXML(standardUrl + loginUrl + userCode);
                    if (data != null) {
                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        factory.setNamespaceAware(true);
                        XmlPullParser xpp = factory.newPullParser();
                        xpp.setInput(new StringReader(data));
                        int eventType = xpp.getEventType();
                        while (eventType != XmlPullParser.END_DOCUMENT) {

                            if (eventType == XmlPullParser.START_TAG) {
                                if (xpp.getName().equalsIgnoreCase("regId")) {
                                    eventType = xpp.next();
                                    if (eventType == XmlPullParser.TEXT) {
                                        UserSession.setPatientRegId(Integer.valueOf(xpp.getText()));
                                    }
                                } else if (xpp.getName().equalsIgnoreCase("state")) {
                                    eventType = xpp.next();
                                    if (eventType == XmlPullParser.TEXT) {
                                        UserSession.setPatientTriageId(Integer.valueOf(xpp.getText()));
                                    }
                                } else if (xpp.getName().equalsIgnoreCase("ciferCode")) {
                                    eventType = xpp.next();
                                    if (eventType == XmlPullParser.TEXT) {
                                        UserSession.setPatientCode(xpp.getText());
                                    }
                                } else if (xpp.getName().equalsIgnoreCase("FName")) {
                                    eventType = xpp.next();
                                    if (eventType == XmlPullParser.TEXT) {
                                        UserSession.setPatientName(xpp.getText());
                                    }
                                }
                            }
                            eventType = xpp.next();
                        }
                        if (UserSession.getPatientRegId() != 0) {
                            updateData();
                        }
                    }
                } catch (Exception e) {
                    Log.d("Data", e.getMessage());
                }
                return null;
            }
        }.execute();
    }

    public boolean checkUserActive(String code) {
        final String userCode = code;
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    String data = hentUrlForXML(standardUrl + loginUrl + userCode);
                    if (data != null) {
                        UserSession.setUserAuth(true);
                    }

                } catch (Exception e) {
                    UserSession.setUserAuth(false);
                    Log.d("Data", e.getMessage());
                }
                return null;
            }
        }.execute();
        return false;
    }

    public void updateData() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    getHospId();
                    updateHospQueue();
                    updatePatientInQueue();
                    UserSession.setUserAuth(true);
                } catch (Exception e) {
                    Log.d("Data", e.getMessage());
                    UserSession.setUserAuth(false);
                }
                return null;
            }
        }.execute();
    }

    public void getHospId() throws IOException {
        String regId = String.valueOf(UserSession.getPatientRegId());
        String data = hentUrl(standardUrl + patientHospUrl + regId);
        UserSession.setHospitalId(Integer.valueOf(data));
    }

    public void updateHospQueue() throws IOException {
        String hospId = String.valueOf(UserSession.getHospitalId());
        String data = hentUrl(standardUrl + hospQueueUrl + hospId);
        UserSession.setQueueLenght(Integer.valueOf(data));
    }

    public void updatePatientInQueue() throws IOException {
        String regId = String.valueOf(UserSession.getPatientRegId());
        String data = hentUrl(standardUrl + patientInQueueUrl + regId);
        UserSession.setQueueNumber(Integer.valueOf(data));
    }

    public String hentUrlForXML(String weburl) throws IOException {
        URL url = new URL(weburl);
        HttpURLConnection http = null;
        if (url.getProtocol().toLowerCase().equals("https")) {
            trustAllHosts();
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);
            http = https;
        } else {
            http = (HttpURLConnection) url.openConnection();
        }
        InputStream in = http.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String linje = br.readLine();
        while (linje != null) {
            sb.append(linje + "\n");
            linje = br.readLine();
        }
        return sb.toString();
    }

    public String hentUrl(String weburl) throws IOException {
        URL url = new URL(weburl);
        HttpURLConnection http = null;
        if (url.getProtocol().toLowerCase().equals("https")) {
            trustAllHosts();
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.setHostnameVerifier(DO_NOT_VERIFY);
            http = https;
        } else {
            http = (HttpURLConnection) url.openConnection();
        }
        InputStream in = http.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        return br.readLine();
    }

    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        }};

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
