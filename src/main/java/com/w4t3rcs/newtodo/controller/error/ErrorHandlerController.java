package com.w4t3rcs.newtodo.controller.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
@Controller
public class ErrorHandlerController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    @Autowired
    public ErrorHandlerController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public String handleError(WebRequest webRequest, Model model) {
        Map<String, Object> attributes = errorAttributes.getErrorAttributes(webRequest, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));
        int status = (int) attributes.get("status");
        model.addAttribute("status", status);
        return "error/error";
    }
}
