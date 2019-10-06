package cn.user9527.mycommunity.advice;

import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.response.ResultComment;
import com.alibaba.fastjson.JSON;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @date 2019/10/4 - 15:31
 */
@ControllerAdvice
public class CustomizeExcptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView hand(HttpServletRequest request, Throwable ex, Model model, HttpServletResponse response) {

        String contentType = request.getContentType();

        if("application/json".equals(contentType)){
            ResultComment resultComment;
            if(ex instanceof CustomizeException){
                resultComment = ResultComment.errorOf((CustomizeException)ex);
            }else {
                resultComment = ResultComment.errorOf(CustomizeErrorCode.SYS_ERROR);
            }

            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultComment));
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }else {
            if(ex instanceof CustomizeException){
                model.addAttribute("message",((CustomizeException) ex).getErrMsg());
            }else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getErrMsg());
            }
            return new ModelAndView("error");
        }

    }

}
