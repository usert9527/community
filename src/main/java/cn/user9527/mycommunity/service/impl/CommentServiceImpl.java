package cn.user9527.mycommunity.service.impl;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.dto.CommentTypeEnum;
import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import cn.user9527.mycommunity.mapper.CommentMapper;
import cn.user9527.mycommunity.mapper.QuestionMapper;
import cn.user9527.mycommunity.mapper.UserMapper;
import cn.user9527.mycommunity.model.Comment;
import cn.user9527.mycommunity.model.CommentExample;
import cn.user9527.mycommunity.model.Question;
import cn.user9527.mycommunity.model.User;
import cn.user9527.mycommunity.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @date 2019/10/6 - 20:54
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
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
            commentMapper.insert(comment);
//            return commentMapper.insertSelective(comment);
        } else {

            //回复问题
            Question byId = questionMapper.getById(comment.getParentId());
            if (byId == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_EXISTS);
            }

            questionMapper.update_Comment_Count(comment.getParentId());

           commentMapper.insert(comment);
        }
    }

    @Override
    public List<CommentDTO> listByTargeId(Integer id, CommentTypeEnum type) {

        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());  // 1 问题评论
        commentExample.setOrderByClause("gmt_create DESC");

        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //得到所有的评论的人的id 去除重复的id
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        System.out.println(userIds+"---");
        //获取评论人并转换为 Map
        List<User> users = userMapper.selectListByUserId(userIds);

        System.out.println(users+"------------");
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
