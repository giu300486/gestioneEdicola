package it.fornaro.gestione_edicola.controller;

import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers;

    public LoginController() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        String idToken = result.split("=")[1].split("&")[0];

        headers.add("Authentication","Bearer " + idToken);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://127.0.0.1:8090/loginUser",HttpMethod.POST,requestEntity,String.class);
        //TO CONTROL IF result IS OK CALLING PORTALE_THINKOPEN, IF OK THEN SEND REDIRECT TO EDICOLA ELSE SEND MESSAGE ERROR LOGIN
        response.sendRedirect("/edicola");
    }

}
