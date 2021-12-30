package jane.crm.setting.service;

import jane.crm.exception.LoginException;
import jane.crm.setting.domain.User;

import java.util.List;

public interface UserService {
    User queryUserLogin(String loginAct, String loginPwd, String ip) throws LoginException;

    List<User> getUserList();
}
