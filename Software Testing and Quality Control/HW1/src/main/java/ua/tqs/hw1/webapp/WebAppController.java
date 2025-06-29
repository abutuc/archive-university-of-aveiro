package ua.tqs.hw1.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAppController {


    @GetMapping("/")
    public String index(){
        return "now";
    }

    @GetMapping("/now")
    public String now(){
        return "now";
    }

    @GetMapping("/forecast")
    public String forecast(){
        return "forecast";
    }

}
