package cn.user9527.mycommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @date 2019/9/29 - 9:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
