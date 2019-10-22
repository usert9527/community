package cn.user9527.mycommunity.dto;

import lombok.Data;

/**
 * @date 2019/10/19 - 20:33
 */
@Data
public class NotificationDTO {

    private Integer id;
    private Long gmtCreate;
    private Integer status;
    private Integer notifier;
    private String notifierName;
    private String outerTitle;
    private String type;
}
