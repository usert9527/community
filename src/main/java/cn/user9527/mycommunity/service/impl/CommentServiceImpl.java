package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.mapper.CommentMapper;
import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @date 2019/10/6 - 20:54
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Integer insert(Comment comment) {
        if(comment.getParentId() == null ){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        return commentMapper.insertSelective(comment);
    }
}
