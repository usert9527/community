package cn.user9527.mycommunity.exception;

/**
 * @date 2019/10/5 - 17:26
 */
public class CustomizeException extends RuntimeException implements ICustomizeErrorCode {

    private ICustomizeErrorCode iCustomizeErrorCode;


    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode) {
        super();
        this.iCustomizeErrorCode = iCustomizeErrorCode;
    }

    public CustomizeException(ICustomizeErrorCode iCustomizeErrorCode,String errMsg) {
        super();
        this.iCustomizeErrorCode = iCustomizeErrorCode;
        this.iCustomizeErrorCode.setErrMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return iCustomizeErrorCode.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return iCustomizeErrorCode.getErrMsg();
    }

    @Override
    public void setErrMsg(String errMsg) {
        iCustomizeErrorCode.setErrMsg(errMsg);
    }
}
