package ru.itis.dis403.hw;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpBenchmark {
    private final String url;
    private final int n;

    public HttpBenchmark(String url, int n) {
        this.url = url;
        this.n = n;
    }

    public List<Long> run(byte[] imageBytes) throws Exception {
        List<Long> times = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();

        for (int i = 0; i < n; i++) {
            long start = System.currentTimeMillis();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
                    .build();
            client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            long lasted = System.currentTimeMillis() - start;
            times.add(lasted);
        }
        return times;
    }
}