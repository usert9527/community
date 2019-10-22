package cn.user9527.mycommunity.enums;

/**
 * @date 2019/10/19 - 9:30
 */
public enum NotificationStatusEnum {

    UNREAD(0),READ(1);

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    NotificationStatusEnum(Integer status) {
        this.status = status;
    }
}
