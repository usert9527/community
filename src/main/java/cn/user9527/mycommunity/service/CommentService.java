package cn.user9527.mycommunity.service;

import cn.user9527.mycommunity.dto.CommentDTO;
import cn.user9527.mycommunity.dto.CommentTypeEnum;
import cn.user9527.mycommunity.model.Comment;

import java.util.List;

/**
 * @date 2019/10/6 - 20:54
 */
public interface CommentService {

     void insert(Comment comment);

    List<CommentDTO> listByTargeId(Integer id, CommentTypeEnum type);
}
