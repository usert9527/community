package cn.user9527.mycommunity.exception;

/**
 * @date 2019/10/4 - 16:03
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_EXISTS(1001,"该问题不存在，请尝试换一个"),
    QUESTION_UPDATE_EXISTS(1002,"更新失败"),
    TARGET_PARAM_NOT_FOUND(1003,"未选中任何问题或评论进行回复"),
    NO_LOGIN(1004,"未登入,请登入后再试"),
    SYS_ERROR(1005,"服务冒烟了，要不然你稍后再试"),
    TYPE_PARAM_WRONG(1006,"该问题已经删除或不存在"),
    COMMENT_NOT_FOUND(1007,"回复的评论不存在了"),
    CONTENT_IS_EMPTY(1008,"回复不能为空··");

    private int errCode;
    private String errMsg;

    private CustomizeErrorCode(int errCode,String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}
