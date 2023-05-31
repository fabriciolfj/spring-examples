package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.entity.Member;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class RestTemplateSimulacao {

    public static void main(String[] args) {
        var uri = "http://localhost:8080/members/{memberId}";
        var params = Map.of("memberId", 1001);

        var restTemplate = new RestTemplate();
        var result = restTemplate.getForObject(uri, Member.class, params);

        System.out.println(result);
    }
}
