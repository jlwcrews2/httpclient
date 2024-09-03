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
         var statusLine = in.readLine();
         response.setStatusLine(statusLine);
         String[] status = statusLine.split(" ");
         response.setHttpVersion(status[0]);
         response.setStatusCode(Integer.parseInt(status[1]));
         response.setStatusMessage(status[2]);
         parseHeaders();
         parseBody();
    }

    private void parseBody() throws IOException {
        int contentLength = Integer.parseInt(response.getHeaders().get("Content-Length"));
        StringBuilder lineBuilder = new StringBuilder();
        for (int i = 0; i < contentLength - 2; i++) {
            lineBuilder.append((char) in.read());
        }
        response.setBody(lineBuilder.toString());
    }

    private void parseHeaders() throws IOException {
        String headers = in.readLine();
        while(headers != null && !headers.isEmpty()){
            String[] header = headers.split(":");
            response.setHeaders(header[0].trim(), header[1].trim());
            headers = in.readLine();
        }

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
