package com.example.WebApplication;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
public class LoginController {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${auth.service.base-url}")
    private String authServiceBaseUrl;
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Credentials> request = new HttpEntity<>(new Credentials(email, password), headers);
        try {
            ResponseEntity<User> response = restTemplate.exchange(authServiceBaseUrl + "/api/authenticate", HttpMethod.POST, request, User.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                User user = response.getBody();
                return "redirect:/enterData/" + user.getId();
            }
        } catch (HttpClientErrorException e) {
            model.addAttribute("invalidCredentials", "Invalid credentials. Please check your email or password.");
        }
        return "login";
    }
}
