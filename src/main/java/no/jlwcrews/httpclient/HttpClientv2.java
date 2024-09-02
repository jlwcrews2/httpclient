package no.jlwcrews.httpclient;

import java.io.*;
import java.net.Socket;

public class HttpClientv2 {

    private Socket clientSocket;
    private HttpResponsev2 response;
    private PrintWriter out;
    private BufferedReader in;

    public HttpResponsev2 makeRequest(String host, int port, String request) throws IOException {

        openConnection(host, port);
        out.println(request);
        handleResponse();
        closeConnection();
        return response;
    }

    private void handleResponse() throws IOException {
         response = new HttpResponsev2();
         response.setStatusLine(in.readLine());
    }

    private void openConnection(String host, int port) throws IOException {
        clientSocket = new Socket(host, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private void closeConnection() throws IOException {
        clientSocket.close();
        out.close();
        in.close();
    }
}
