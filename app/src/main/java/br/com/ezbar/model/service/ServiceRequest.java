package br.com.ezbar.model.service;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class ServiceRequest<M> extends AsyncTask<Void, Void, String> {

    public enum RequestMethod {
        get,
        post
    }

    private Service service;
    private RequestMethod requestMethod;

    public ServiceRequest(RequestMethod requestMethod, Service service) {
        this.requestMethod = requestMethod;
        this.service = service;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Requesting service from webservice...");
        try {
            return requestMethod(service.getUrl(), service.getServiceProtocol().getParams());
        } catch (Exception e) {
            Log.d("MyAppNow", e.getMessage());
            //service.event.onClientError(e);
            return "";
        }
    }

    @Override
    protected void onPostExecute(String data) {
        if (android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Triggering webservice done service event...");
        service.done(data);
        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Webservice events triggered.");
    }

    private String requestMethod(String requestURL, HashMap<String, String> postDataParams) {

        if (android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        URL url;
        StringBuilder response = new StringBuilder();

        if (requestMethod == RequestMethod.post) {
            try {
                url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestProperty("Authorization", service.getServiceProtocol().getServiceAuth().getAuthToken());
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                // Escreve parametros
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();

                int serviceCode = conn.getResponseCode();
                if (serviceCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                } else {
                    service.error(new ServiceException(service, service.getClass().getSimpleName() + ": HTTP Response Code: " + serviceCode));
                    response.delete(0, response.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestMethod == RequestMethod.get) {
            try {
                response.append(doGetRequest(requestURL));
            } catch (Exception e) {
                e.printStackTrace();
                service.error(new ServiceException(service, e));
            }
        }

        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Webservice service: \n" + service);
        return response.toString();
    }

    private String doGetRequest(String request) {
        HttpURLConnection httpUrlConnection = null;
        StringBuilder requestResult;

        try {
            URL url = new URL(request);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestProperty("Authorization", service.getServiceProtocol().getServiceAuth().getAuthToken());
            InputStream in = new BufferedInputStream(httpUrlConnection.getInputStream());

            //verifying if connection result was ok
            if (httpUrlConnection.getResponseCode() == 200) {

                //reading stream
                requestResult = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String line;

                while ((line = reader.readLine()) != null)
                    requestResult.append(line);
            } else {
                service.error(new ServiceException(service, "HTTP Response Code: " + httpUrlConnection.getResponseCode()));
                return null;
            }
        } catch (Exception e) {
            service.error(new ServiceException(service, e));
            return null;
        } finally {
            if (httpUrlConnection != null)
                httpUrlConnection.disconnect();
        }

        return requestResult.toString();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {

        boolean first = true;
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    private Bitmap baixarImagem(String url) {
        try {
            URL path;
            InputStream inputStream;
            Bitmap imagem;
            path = new URL(url);
            inputStream = path.openStream();
            //imagem = Bitmap. (inputStream);
            inputStream.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

