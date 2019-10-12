package cn.user9527.mycommunity.dto;

import cn.user9527.mycommunity.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date 2019/10/9 - 22:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String comment;
    private User user;
}
