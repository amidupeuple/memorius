package com.memorius.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dpivovar on 18.05.2016.
 */
@Controller
@RequestMapping("/")
public class MemoriusMainController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String showHome() {
        return "home";
    }
}
