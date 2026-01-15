package com.candlez.budget_guy.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * the purpose of this controller is to direct all traffic that does not start with "/api" and is not a
 * static file to the angular application. angular will then try to find a resource with its router. if
 * one is not found, it will fall back to the wildcard route that has been configured
 */
@Controller
public class AngularForwardController implements ErrorController {

    @GetMapping("/{path:^(?!api)(?!.*\\.).*$}")
    public String forward() {
        return "forward:/index.html";
    }

    @RequestMapping(
            value = "${server.error.path:/error}",
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String forwardError(HttpServletRequest request, HttpServletResponse response) {
        if (request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE) instanceof Integer status) {
            response.setStatus(status);
        }

        return "forward:/index.html";
    }
}
