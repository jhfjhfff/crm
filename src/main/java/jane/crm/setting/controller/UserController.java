package jane.crm.setting.controller;

import jane.crm.exception.LoginException;
import jane.crm.setting.domain.User;
import jane.crm.setting.service.UserService;
import jane.crm.vo.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/setting/user/login.do")
    @ResponseBody
    public Object userLogin(String loginAct, String loginPwd, HttpServletRequest request) throws LoginException {

        Message message = new Message();

        String ip = request.getLocalAddr();

        User user = userService.queryUserLogin(loginAct,loginPwd,ip);

        request.getSession().setAttribute("user",user);

        //代码执行到这里代表登录成功
        message.setSuccess(true);
        return message;

    }




}
