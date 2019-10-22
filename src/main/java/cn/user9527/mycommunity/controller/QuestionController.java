package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.enums.CommentTypeEnum;
import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.CommentService;
import cn.user9527.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @date 2019/10/2 - 10:28
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;


    /**
     * 问题详情
     * @param id 问题id
     * @param model
     * @param request
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model,
                           HttpServletRequest request){

        User user = (User)request.getSession().getAttribute("user");

        Integer userId = null;
        if(user != null){
            userId = user.getId();
        }

        //问题详情
        QuestionDTO questionDTO = questionService.getById(id,userId);

        //相关问题
        List<QuestionDTO> questionCorrelation = questionService.selectRelated(questionDTO);

        //累加阅读数
        //questionService.addView(id);

        //当前问题的评论
        List<CommentDTO> commentDTOList = commentService.listByTargeId(id, CommentTypeEnum.QUESTION);


        model.addAttribute("questionDTOList", questionDTO);
        model.addAttribute("commentDTOList", commentDTOList);
        model.addAttribute("questionCorrelation", questionCorrelation);
        return "question";
    }

}
