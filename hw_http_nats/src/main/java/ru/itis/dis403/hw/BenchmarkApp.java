package ru.itis.dis403.hw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BenchmarkApp {
    public static void main(String[] args) throws Exception {
        byte[] imageBytes = Files.readAllBytes(Path.of("hw_http_nats/src/main/resources/image.jpg"));
        int n = 50;

        List<Long> httpTimes = new HttpBenchmark("http://localhost:5001/grayscale", n)
                .run(imageBytes);
        new Stats(httpTimes).print("HTTP");

        List<Long> natsTimes = new NatsBenchmark("nats://localhost:4222", "request.image.mirror", n)
                .run(imageBytes);
        new Stats(natsTimes).print("NATS");
    }

}
