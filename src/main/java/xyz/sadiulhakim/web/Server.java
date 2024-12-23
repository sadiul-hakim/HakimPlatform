package xyz.sadiulhakim.web;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import xyz.sadiulhakim.util.JsonUtil;
import xyz.sadiulhakim.util.SettingUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {

    private static final String DELAY_PROPERTY = "delay";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final int CODE_200 = 200;

    private static final Map<String, Object> setting = SettingUtil.getSetting();

    private Server() {
    }

    public static void start() {
        Integer port = (Integer) setting.get(SettingUtil.SERVER_PORT);
        try {
            var server = HttpServer.create(new InetSocketAddress(port), 0);
            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());

            // Server testing api
            var serveContext = server.createContext("/server");
            serveContext.setHandler(Server::serverExchange);

            server.start();
            System.out.println("Server running on port " + port);
        } catch (Exception ignore) {

        }
    }

    private static void serverExchange(HttpExchange exchange) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Served");
        try {

            Map<String, String> parameters = getQueryParameters(exchange);
            String delay = parameters.getOrDefault(DELAY_PROPERTY, "");

            if (!delay.isEmpty()) {
                TimeUnit.SECONDS.sleep(Integer.parseInt(delay));
            }

            String body = JsonUtil.MAPPER.writeValueAsString(message);
            sendResponse(exchange, body.getBytes(StandardCharsets.UTF_8), CODE_200, JSON_CONTENT_TYPE);
        } catch (Exception ignore) {
        }
    }

    private static void sendResponse(HttpExchange exchange, byte[] bytes, int code, String contentType) throws IOException {

        exchange.getResponseHeaders().add("Content-Type", contentType);
        exchange.sendResponseHeaders(code, bytes.length);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    private static Map<String, String> getQueryParameters(HttpExchange exchange) {
        Map<String, String> queryParams = new HashMap<>();
        String query = exchange.getRequestURI().getQuery();
        if (query != null) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=", 2);
                String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                String value = keyValue.length > 1 ? URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8) : "";
                queryParams.put(key, value);
            }
        }
        return queryParams;
    }

}
