package cn.user9527.mycommunity.controller;

import cn.user9527.mycommunity.dto.CommentCreateDTO;
import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.dto.CommentTypeEnum;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.response.ResultComment;
import cn.user9527.mycommunity.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @date 2019/10/6 - 19:26
 */
@Controller
public class CommentController {

    @Autowired
   private CommentService commentService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentCreateDTO commentDTO, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultComment.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        if(commentDTO == null || StringUtils.isBlank(commentDTO.getComment())){
            return  ResultComment.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setComment(commentDTO.getComment());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());

        BeanUtils.copyProperties(commentDTO,comment);
        commentService.insert(comment);

        return ResultComment.okOf();
    }

    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultComment comments(@PathVariable(name="id") Integer id){


        List<CommentDTO> commentDTOS = commentService.listByTargeId(id, CommentTypeEnum.COMMENT);

        return ResultComment.okOf(commentDTOS);
    }
}


