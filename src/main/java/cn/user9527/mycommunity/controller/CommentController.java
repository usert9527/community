package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.mapper.CommentMapper;
import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.response.ResultComment;
import cn.user9527.mycommunity.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2019/10/6 - 19:26
 */
@Controller
public class CommentController {

    @Autowired
   private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request){

//        User user = (User) request.getSession().getAttribute("user");
//
//        System.out.println("否"+user);
//
//        if(user == null){
//            return ResultComment.errorOf(CustomizeErrorCode.NO_LOGIN);
//        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setComment(commentDTO.getComment());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
//        comment.setCommentator(user.getId());

        BeanUtils.copyProperties(commentDTO,comment);
        commentService.insert(comment);

        return ResultComment.okOf();
    }
}


