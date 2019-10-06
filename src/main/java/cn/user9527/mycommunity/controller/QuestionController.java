package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2019/10/2 - 10:28
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model,
                           HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");

        QuestionDTO questionDTO = questionService.getById(id,user.getId());
        //累加阅读数
//        questionService.addView(id);

        model.addAttribute("questionDTOList", questionDTO);

        return "question";
    }

}
