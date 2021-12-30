package jane.crm.workbench.service.Impl;

import jane.crm.util.DateTimeUtil;
import jane.crm.util.UUDIUtil;
import jane.crm.vo.Message;
import jane.crm.workbench.dao.ActivityRemarkDao;
import jane.crm.workbench.domain.ActivityRemark;
import jane.crm.workbench.service.ActivityRemarkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Resource
    private ActivityRemarkDao activityRemarkDao;

    @Override
    public Message saveRemark(String activityId,String noteContent,String createBy) {

        Message message = new Message();

        ActivityRemark activityRemark = new ActivityRemark();

        activityRemark.setId(UUDIUtil.getUUID());
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setNoteContent(noteContent);
        activityRemark.setCreateBy(createBy);
        activityRemark.setEditFlag("0");
        activityRemark.setActivityId(activityId);

        int count = activityRemarkDao.saveRemark(activityRemark);

        if (count >0 ){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }
        return message;
    }

    @Override
    public List<ActivityRemark> getRemarkList(String activityId) {

        List<ActivityRemark> remarkList = activityRemarkDao.getRemarkListByAid(activityId);

        return remarkList;
    }

    @Transactional
    @Override
    public Message updateRemark(ActivityRemark activityRemark) {

        Message message = new Message();

        activityRemark.setEditTime(DateTimeUtil.getSysTime());
        activityRemark.setEditFlag("1");

        int count = activityRemarkDao.updateRemark(activityRemark);
        if (count > 0){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }
        return message;
    }

    @Transactional
    @Override
    public Message deleteRemark(String id) {

        Message message = new Message();

        int count =  activityRemarkDao.deleteRemark(id);

        if (count > 0){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }
        return message;

    }
}
