package jane.crm.setting.service.Impl;

import jane.crm.exception.LoginException;
import jane.crm.setting.dao.UserDao;
import jane.crm.setting.domain.User;
import jane.crm.setting.service.UserService;
import jane.crm.util.DateTimeUtil;
import jane.crm.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User queryUserLogin(String loginAct, String loginPwd, String ip) throws LoginException {

        User user = new User();
        user.setLoginAct(loginAct);
        user.setLoginPwd(MD5Util.getMD5(loginPwd));
        System.out.println(user.getLoginAct());
        System.out.println(user.getLoginPwd());

        user = userDao.queryUserLogin(user);

        if (user == null){
            throw new LoginException("账号或密码错误！");
        }else if (!user.getAllowIps().contains(ip)){
            throw new LoginException("ip受限！");
        }else if (DateTimeUtil.getSysTime().compareTo(user.getExpireTime())>0){
            throw new LoginException("账号已过期！");
        }else if ("0".equals(user.getLockState())){
            throw new LoginException("账号已被锁定！");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {

        List<User> users = userDao.getUserList();


        return users;
    }
}
