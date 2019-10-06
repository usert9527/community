package cn.user9527.mycommunity.response;

import cn.user9527.mycommunity.exception.CustomizeErrorCode;
import cn.user9527.mycommunity.exception.CustomizeException;
import lombok.Data;
import org.springframework.web.servlet.ModelAndView;

/**
 * @date 2019/10/6 - 20:21
 */
@Data
public class ResultComment {

    private Integer code;
    private String message;

    public static ResultComment errorOf(Integer code, String message) {
        ResultComment resultComment = new ResultComment();
        resultComment.setCode(code);
        resultComment.setMessage(message);
        return resultComment;
    }

    public static ResultComment errorOf(CustomizeErrorCode errorCode){
        return errorOf(errorCode.getErrCode(),errorCode.getErrMsg());
    }

    public static ResultComment errorOf(CustomizeException ex) {
        return errorOf(ex.getErrCode(),ex.getErrMsg());
    }

    public static ResultComment okOf(){
        ResultComment resultComment = new ResultComment();
        resultComment.setCode(200);
        resultComment.setMessage("请求成功");
        return resultComment;
    }


}
