package cn.user9527.mycommunity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @date 2019/9/29 - 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccessTokenDTO  {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

    private String two;
    private String two1;
    private String two3;


}
