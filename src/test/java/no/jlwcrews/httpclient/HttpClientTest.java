package no.jlwcrews.httpclient;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpClientTest {

    private static final String host = "httpbin.org";
    private static final int port = 80;
    private static final String blankLine = "\r\n";

    @Test
    void shouldMakeRequest() throws IOException {
        String request = "GET /html HTTP/1.1" +
                blankLine +
                "Host: " + host +
                blankLine + blankLine;

        HttpClientv2 client = new HttpClientv2();
        var response = client.makeRequest(host, port, request);
        System.out.println(response.getStatusLine());
        response.getHeaders().forEach((key, value) -> System.out.println(key + " : " + value));
        assert(response.getHeaders().get("Content-Type").equals("text/html; charset=utf-8"));
        assert(response.getStatusCode() == 200);
        assert(response.getStatusMessage().equals("OK"));
        System.out.println(response.getBody());
    }
}
