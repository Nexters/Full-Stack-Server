package fullstack.labelary.controller;

import fullstack.labelary.service.dto.HelloResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/hello")
    public String home() {
        log.info("home controller");
        return "home";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                           @RequestParam("amount") int amount) {
        log.info("API RequestParam name : {}, amount : {}", name, amount);

        return new HelloResponseDto(name, amount);
    }

}
