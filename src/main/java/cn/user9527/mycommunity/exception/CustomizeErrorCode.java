package cn.user9527.mycommunity.exception;

/**
 * @date 2019/10/4 - 16:03
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{

    QUESTION_NOT_EXISTS(1001,"该问题不存在，请尝试换一个"),
    QUESTION_UPDATE_EXISTS(1002,"更新失败");
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
