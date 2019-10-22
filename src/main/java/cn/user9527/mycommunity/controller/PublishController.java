package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.QuestionDTO;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2019/9/30 - 8:54
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    /**
     * 修改问题
     * @param id 问题 id
     * @param mode
     * @return
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model mode) {

        QuestionDTO question = questionService.getById(id, null);
        mode.addAttribute("title", question.getTitle());
        mode.addAttribute("description", question.getDescription());
//        mode.addAttribute("tag", question.getTag());
        mode.addAttribute("id", question.getId());
        mode.addAttribute("tarr", "1");
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    /**
     * 发布问题
     * @param title 标题
     * @param description 描述
     * @param tag 标签
     * @param request
     * @param model
     * @param id
     * @return
     */
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam(name = "tag",required = false) String tag,
            HttpServletRequest request,
            Model model,
            @RequestParam("id") Integer id) {

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setId(id);

        questionService.createOrUpdate(question);

        return "redirect:/";

    }
}
