package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import cn.user9527.mycommunity.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @date 2019/10/1 - 7:19
 */
@Controller
public class ProfileController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    /**
     * 个人问题 和 最新回复
     * @param action
     * @param model
     * @param request
     * @param pageNum
     * @return
     */
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model,
                          HttpServletRequest request,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        // 我的发布
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的发布");
            if (user != null) {
                PageUtil pageUtil = new PageUtil(5, pageNum, questionService.this_Question(user.getId()));
                List<QuestionDTO> questionDTOList = questionService.select_Question(user.getId(), pageUtil);
                model.addAttribute("questionDTOList", questionDTOList);
                model.addAttribute("pageUtil", pageUtil);
            }
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        return "profile";
    }


}
