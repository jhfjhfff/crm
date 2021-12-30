package jane.crm.setting.dao;

import jane.crm.setting.domain.User;

import java.util.List;

public interface UserDao {
    User queryUserLogin(User user);

    List<User> getUserList();
}
