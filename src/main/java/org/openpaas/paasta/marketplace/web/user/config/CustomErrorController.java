package org.openpaas.paasta.marketplace.web.user.config;

import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@ControllerAdvice
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";
    
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
    
    @RequestMapping("${error.path:/error}")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));
        
        log.info(">>>>>>> httpStatus : {}", httpStatus.toString());
        log.info(">>>>>>> error page url : error/error");
        
        model.addAttribute("code", status.toString());
        model.addAttribute("msg", httpStatus.getReasonPhrase());
        model.addAttribute("timestamp", new Date());
        
        return "error/500";
    }
}
