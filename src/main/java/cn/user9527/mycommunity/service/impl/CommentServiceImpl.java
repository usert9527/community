package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.dto.CommentTypeEnum;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.mapper.CommentMapper;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.model.Question;
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

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Integer insert(Comment comment) {
        if (comment.getParentId() == null) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {

            //回复评论
            Comment comment1 = commentMapper.selectByParentKey(comment.getParentId());
            if (comment1 == null) {
                throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
            }
            return commentMapper.insertSelective(comment);
        } else {
            Question byId = questionMapper.getById(comment.getParentId());
            if (byId == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_EXISTS);
            }

            questionMapper.update_Comment_Count(comment.getParentId());

            return commentMapper.insertSelective(comment);
        }
    }
}
