package cn.user9527.mycommunity.exception;

/**
 * @date 2019/10/4 - 15:59
 * 公共异常处理的接口
 */
public interface ICustomizeErrorCode {

    //获取当前报错内容的错误码
    int getErrCode();

    //获取错误信息
    String getErrMsg();

    //自定义异常的设置
    void setErrMsg(String errMsg);
}
