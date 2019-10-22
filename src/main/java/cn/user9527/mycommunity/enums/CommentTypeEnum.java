package cn.user9527.mycommunity.enums;

/**
 * @date 2019/10/6 - 22:43
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType(){return type;}

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type){
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
