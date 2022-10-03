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
public class Api {

    // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/api")
    public String api(Model model) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://free-nba.p.rapidapi.com/players?page=0&per_page=25"))
		.header("X-RapidAPI-Key", "9f4648d091mshd482eecf7cc5dcfp1c3796jsn99cec5dc67e0")
		.header("X-RapidAPI-Host", "free-nba.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        // alternative #2: convert response.body() to JSON object
        Object obj = new JSONParser().parse(response.body());
        JSONObject jobject = (JSONObject) obj;
        System.out.println(jobject);

        // pass stats to view
        model.addAttribute("jobject", jobject);

        return "api";

    }
}
