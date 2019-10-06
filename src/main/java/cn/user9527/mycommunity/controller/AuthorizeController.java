package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.AccessTokenDTO;
import cn.user9527.mycommunity.dto.GithubUser;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.provider.GithubProvider;
import cn.user9527.mycommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @date 2019/9/29 - 7:56
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserService userService;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String cedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(cedirectUri);
        accessTokenDTO.setState(state);

        String accessToken = githubProvider.getAccessToken(accessTokenDTO);

        GithubUser user = githubProvider.getUser(accessToken);

        if(user !=null && user.getId() != null){
            User user1 = new User();
            String token = UUID.randomUUID().toString();

            user1.setToken(token);
            user1.setName(user.getName());
            user1.setAccountId(String.valueOf(user.getId()));
            user1.setAvatarUrl(user.getAvatarUrl());

            userService.createOrUpdate(user1);

            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
