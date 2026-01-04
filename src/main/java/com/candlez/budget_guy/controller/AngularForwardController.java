package com.candlez.budget_guy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * the purpose of this controller is to direct all traffic that does not start with "/api" and is not a
 * static file to the angular application. angular will then try to find a resource with its router. if
 * one is not found, it will fall back to the wildcard route that has been configured
 */
@Controller
public class AngularForwardController {

    @GetMapping("/{path:^(?!api)(?!.*\\.).*$}")
    public String forward() {
        return "forward:/index.html";
    }
}
