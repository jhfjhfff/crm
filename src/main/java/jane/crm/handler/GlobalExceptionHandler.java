package jane.crm.handler;

import jane.crm.exception.LoginException;
import jane.crm.vo.Message;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Object doLoginFailException(Exception exception){

        // 拿到错误消息
        String msg = exception.getMessage();
        // 设置success为false
        boolean success = false;

        // 创建map 返回json数据格式
        Message message = new Message();
        message.setSuccess(false);
        message.setMsg(msg);

        return message;
    }
}
