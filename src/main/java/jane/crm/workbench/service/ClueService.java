package jane.crm.workbench.service;

import jane.crm.setting.domain.User;
import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.Clue;

import java.util.List;

public interface ClueService {
    List<User> getuList();

    Message saveClue(Clue clue);

    PaginationVo<Clue> pageList(Integer pageNo, Integer pageSize);

    Clue detail(String id);

    Message unbund(String activityId,String clueId);

    List<Activity> searchActivities(String name);

    Message bund(String[] activityId,String clueId);
}
