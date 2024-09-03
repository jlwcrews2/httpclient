package no.jlwcrews.httpclient;

import java.util.HashMap;

public class HttpResponsev2 {

    private String httpVersion;
    private int statusCode;
    private String statusMessage;
    private String statusLine;
    private final HashMap<String, String> headers;
    private String body;

    public HttpResponsev2() {
        this.statusCode = 0;
        this.statusMessage = "";
        this.statusLine = "";
        this.body = "";
        this.headers = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(String headerName, String headerValue) {
        this.headers.put(headerName, headerValue);
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
