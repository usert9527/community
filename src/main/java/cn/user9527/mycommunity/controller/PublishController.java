package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @date 2019/9/30 - 8:54
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        User user = null;

        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length != 0){
            for (int i = 0; i < cookies.length; i++) {
                if(cookies[i].getName().equals("token")){
                    String token = cookies[i].getValue();

                    user = userMapper.findByToken(token);

                    if(user != null){
                        System.out.println(user+"-----");
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());

        questionMapper.create(question);

        return "redirect:/";

    }
}
