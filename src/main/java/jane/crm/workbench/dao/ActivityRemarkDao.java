package jane.crm.workbench.dao;

import jane.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkDao {
    int selectByActivtyIds(String[] ids);

    int deleteByActivtyIds(String[] ids);

    int saveRemark(ActivityRemark activityRemark);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    int updateRemark(ActivityRemark activityRemark);

    int deleteRemark(String id);
}
