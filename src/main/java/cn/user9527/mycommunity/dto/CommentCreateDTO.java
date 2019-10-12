package cn.user9527.mycommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @date 2019/10/6 - 19:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentCreateDTO implements Serializable {

    private Integer parentId;
    private String comment;
    private Integer type;


}
