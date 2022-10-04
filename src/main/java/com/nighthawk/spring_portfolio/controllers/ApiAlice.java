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
public class Apialice {

    // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/apialice")
    public String apialice(Model model) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://wft-geo-db.p.rapidapi.com/v1/geo/adminDivisions"))
		.header("X-RapidAPI-Key", "f877084053msh82cfa972b631ab7p1c4893jsn442e2f514bb0")
		.header("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        // alternative #2: convert response.body() to JSON object
        Object obj = new JSONParser().parse(response.body());
        JSONObject jobject = (JSONObject) obj;
        System.out.println(jobject);

        // pass stats to view
        model.addAttribute("jobject", jobject);

        return "apialice";

    }
}
