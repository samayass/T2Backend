package com.nighthawk.spring_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.json.simple.parser.ParseException;

import org.springframework.ui.Model;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller // HTTP requests are handled as a controller, using the @Controller annotation
public class ApiSarayu {

    // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/apisarayu")
    public String apisarayu(Model model) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://earthquake-monitor.p.rapidapi.com/recent"))
		.header("X-RapidAPI-Key", "cb6c4ae0c0mshf7c680cd7f9687bp1c11edjsnd948864f5be3")
		.header("X-RapidAPI-Host", "earthquake-monitor.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());



        // alternative #2: convert response.body() to JSON object
        Object obj = new JSONParser().parse(response.body());
        JSONObject jobject = (JSONObject) obj;
        System.out.println(jobject);

        // pass stats to view
        model.addAttribute("jobject", jobject);

        return "apisarayu";

    }
}
