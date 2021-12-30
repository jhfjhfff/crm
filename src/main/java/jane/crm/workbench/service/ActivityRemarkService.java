package jane.crm.workbench.service;

import jane.crm.vo.Message;
import jane.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
    Message saveRemark(String activityId, String noteContent, String createBy);

    List<ActivityRemark> getRemarkList(String activityId);

    Message updateRemark(ActivityRemark activityRemark);

    Message deleteRemark(String id);
}
