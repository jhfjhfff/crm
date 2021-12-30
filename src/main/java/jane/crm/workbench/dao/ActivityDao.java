package jane.crm.workbench.dao;

import jane.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int saveActivity(Activity activity);

    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getActivityByCondition(Map<String, Object> map);

    Activity getActivtyById(String id);

    int updateActivity(Activity activity);

    int deleteActivityByListId(String[] ids);

    List<Activity> searchActivities(String name);
}
