package ru.itis.dis403.hw;

import io.nats.client.Nats;
import io.nats.client.Connection;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class NatsBenchmark {
    private final String natsUrl;
    private final String subject;
    private final int n;

    public NatsBenchmark(String natsUrl, String subject, int n) {
        this.natsUrl = natsUrl;
        this.subject = subject;
        this.n = n;
    }

    public List<Long> run(byte[] imageBytes) throws Exception {
        List<Long> times = new ArrayList<>();
        Connection nc = Nats.connect(natsUrl);

        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        for (int i = 0; i < n; i++) {
            long start = System.currentTimeMillis();
            String json = "{\"image\": \"" + imageBase64 + "\"}";
            byte[] payload = json.getBytes();
            nc.request(subject, payload, Duration.ofSeconds(10));
            long lasted = System.currentTimeMillis() - start;
            times.add(lasted);
        }

        nc.close();
        return times;
    }
}