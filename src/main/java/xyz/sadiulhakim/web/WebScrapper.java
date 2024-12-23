package xyz.sadiulhakim.web;

import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import xyz.sadiulhakim.util.JsonUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class WebScrapper {

    private static final HttpClient CLIENT = HttpClient.newBuilder().build();

    private WebScrapper() {
    }

    public static Optional<String> scrap(String uri) {

        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
                    .GET()
                    .build();

            var response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return Optional.of(response.body());
        } catch (Exception ignore) {
        }

        return Optional.empty();
    }

    public static Optional<JsonNode> scrapJson(String uri) {

        try {
            HttpRequest request = HttpRequest.newBuilder(URI.create(uri))
                    .GET()
                    .header("Content-Type", "application/json")
                    .build();

            var response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode node = JsonUtil.MAPPER.readTree(response.body());
            return Optional.of(node);
        } catch (Exception ignore) {
        }

        return Optional.empty();
    }

    public static Optional<Document> scrapHtml(String uri) {

        try {
            Document document = Jsoup.connect(uri).get();
            return Optional.of(document);
        } catch (Exception ignore) {
            return Optional.empty();
        }
    }
}
