package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import cn.user9527.mycommunity.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @date 2019/9/28 - 22:16
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {

        PageUtil pageUtil = new PageUtil(5,pageNum,questionService.num_Question());
        List<QuestionDTO> questionDTOList = questionService.list_Question(pageUtil);

        model.addAttribute("questionDTOList", questionDTOList);
        model.addAttribute("pageUtil", pageUtil);

        return "index";
    }


}
