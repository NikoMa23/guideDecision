package com.zh.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtils {
    public static JsonNode parseRequest(HttpServletRequest request){
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                request.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonBody = stringBuilder.toString();
        System.out.println("Received JSON data: " + jsonBody);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(jsonBody);
            // 现在你可以使用 jsonNode 处理 JSON 数据
            // 例如：String value = jsonNode.get("key").asText();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
