//package com.example.notice;
//
//import android.util.Log;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.PrintStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.*;
//import java.io.*;
//import java.security.KeyStore;
//import java.security.cert.Certificate;
//import java.security.cert.CertificateFactory;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManagerFactory;
//
//
////URLConnector 함수로 사용하도록 만들기
//
//public class URLConnector extends Thread {
//
//    String temp;
//    public void run() {
//
//        // http 요청을 쏴서 그에 대한 결과값을 받아옵니다.
//        final String output = request("http://13.125.52.219/app_db/notice_tbl.php"); //000-default파일 못쓰게만들고 URL로 변경하기
//
//        // 결과값이 temp에 담깁니다.
//        temp = output;
//    }
//
//    public String getTemp(){
//        return temp;
//    }
//
//    private String request(String urlStr) {
//        StringBuilder output = new StringBuilder();
//        try {
//            URL url = new URL(urlStr);
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();//HttpsURLConnection
//            if (conn != null) {
//                conn.setConnectTimeout(10000);
//                conn.setRequestMethod("GET");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                int resCode = conn.getResponseCode();
//                if (resCode == HttpURLConnection.HTTP_OK) {
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())) ;
//                    String line = null;
//                    while(true) {
//                        line = reader.readLine();
//                        if (line == null) {
//                            break;
//                        }
//                        output.append(line + "\n");
//                    }
//
//                    reader.close();
//                    conn.disconnect();
//                }
//            }
//        } catch(Exception ex) {
//            Log.e("SampleHTTP", "Exception in processing response.", ex);
//            ex.printStackTrace();
//        }
//        return output.toString();
//    }
//
//}
