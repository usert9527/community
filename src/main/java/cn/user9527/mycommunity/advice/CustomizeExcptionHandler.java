package cn.user9527.mycommunity.advice;

import cn.user9527.mycommunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2019/10/4 - 15:31
 */
@ControllerAdvice
public class CustomizeExcptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView hand(HttpServletRequest request, Throwable ex, Model model) {
        if(ex instanceof CustomizeException){
            model.addAttribute("message",((CustomizeException) ex).getErrMsg());
        }else {
            model.addAttribute("message","服务冒烟了，要不然你稍后再试");
        }
        return new ModelAndView("error");
    }

}
