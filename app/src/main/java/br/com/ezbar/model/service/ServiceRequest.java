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

import br.com.ezbar.model.service.impl.LoginService;


public class ServiceRequest<M> extends AsyncTask<Void, Void, String> {

    public enum RequestMethod {
        get,
        post
    }

    private Service service;
    private RequestMethod requestMethod;
    private IServiceCallback listener;
    private M model;

    public ServiceRequest(RequestMethod requestMethod, Service service, IServiceCallback listener, M model) {
        this.requestMethod = requestMethod;
        this.service = service;
        this.listener = listener;
        this.model = model;
    }

    @Override
    protected String doInBackground(Void... params) {
        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Requesting service from webserivce...");
        try {
            return requestMethod(service.getUrl(), service.getRequestParams());
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
        service.requestDone(data, listener, model);
        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Webservice events triggered.");
    }

    private String requestMethod(String requestURL, HashMap<String, String> postDataParams) {

        if (android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        URL url;
        String response = "";

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

                // Escreve parametos
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
                        response += line;
                    }
                } else {
                    service.requestError(service.getClass().getSimpleName() + ": HTTP Responde Code: " + serviceCode, listener);
                    response = "";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestMethod == RequestMethod.get) {
            try {
                response = doGetRequest(requestURL);
            } catch (Exception e) {
                e.printStackTrace();
                service.requestError(e.getMessage(), listener);
            }
        }

        Log.d("MyAppNow", service.getClass().getSimpleName() + ": Webservice service: \n" + service);
        return response;
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
                service.requestError("HTTP Responde Code: " + httpUrlConnection.getResponseCode(), listener);
                return null;
            }
        } catch (Exception e) {
            service.requestError(e.getMessage(), listener);
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
            URL endereco;
            InputStream inputStream;
            Bitmap imagem;
            endereco = new URL(url);
            inputStream = endereco.openStream();
            //imagem = Bitmap. (inputStream);
            inputStream.close();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

