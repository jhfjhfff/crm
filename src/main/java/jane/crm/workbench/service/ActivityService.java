package jane.crm.workbench.service;

import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.ActivityRemark;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    Message saveActivity(Activity activity);


    PaginationVo<Activity> pageList(Map<String, Object> map);


    Activity getActivtyById(String id);

    Message updateActivity(Activity activity);

    Message deleteActivity(String[] ids);


    List<Activity> showActivityByClueId(String clueId);
}
