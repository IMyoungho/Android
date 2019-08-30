package com.example.notice.App_DB;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;


// SSL 통신 시 필요
public class RestClient{
    private static final String HTTPS_STRING = "https";
    private String REST_SERVER_HTTPS_POST_URI; // Post방식으로 요청할 DB URL 변수

    public void parseDB(String DBurl){  //DB Url을 가져오는 함수
        this.REST_SERVER_HTTPS_POST_URI=DBurl;
    }

    private RestClient(){
    }
    private static RestClient instance = null;
    public static RestClient getInstance(){
        if(instance == null) {
            instance = new RestClient();
        }
        return instance;
    }

    public String postRequest() {
        String responseString = "";
        HttpClient httpClient = HTTPUtils.getNewHttpClient(REST_SERVER_HTTPS_POST_URI.startsWith(HTTPS_STRING));
        HttpResponse response = null;
        InputStream in;
        URI newURI = URI.create(REST_SERVER_HTTPS_POST_URI);
        HttpPost postMethod = new HttpPost(newURI);

        try {
            JSONObject postJSON = new JSONObject();
            postJSON.put("longUrl", "https://www.shinple.kr/"); //http로 해도 자동으로 https 리다이렉트 되도록 서버에서 설정해놓음^^
            postMethod.setEntity(new StringEntity(postJSON.toString(), HTTP.UTF_8));
            postMethod.setHeader("Content-Type", "application/json");
            response = httpClient.execute(postMethod);
            in = response.getEntity().getContent();
            responseString = convertStreamToString(in);
        } catch (Exception e) {}
        return responseString;
    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }
}