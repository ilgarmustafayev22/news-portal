package az.newsportal.msuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/news")
public class ReadController {

    @GetMapping
    public String getAllNews() {
        return "here are the news";
    }
}
