package jane.crm.workbench.service.Impl;

import jane.crm.util.DateTimeUtil;
import jane.crm.util.UUDIUtil;
import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.dao.ActivityDao;
import jane.crm.workbench.dao.ActivityRemarkDao;
import jane.crm.workbench.dao.ClueActivityRelationDao;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.ActivityRemark;
import jane.crm.workbench.service.ActivityService;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;

    @Resource
    private ActivityRemarkDao activityRemarkDao;

    @Resource
    private ClueActivityRelationDao clueActivityRelationDao;

    @Transactional
    @Override
    public Message saveActivity(Activity activity) {

        Message message = new Message();

        String uuid = UUDIUtil.getUUID();
        activity.setId(uuid);

        String createTime = DateTimeUtil.getSysTime();
        activity.setCreateTime(createTime);


        int result = activityDao.saveActivity(activity);

        if (result>0){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }

        return message;
    }

    @Override
    public PaginationVo<Activity> pageList(Map<String, Object> map) {

        PaginationVo<Activity> vo = new PaginationVo<>();

        //取得total
        int total = activityDao.getTotalByCondition(map);

        //取得dataList
        List<Activity> dataList = activityDao.getActivityByCondition(map);
        for (Activity activity:dataList){
            System.out.println(activity);
        }

        vo.setDataList(dataList);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public Activity getActivtyById(String id) {

        Activity activity = activityDao.getActivtyById(id);

        return activity;
    }

    @Transactional
    @Override
    public Message updateActivity(Activity activity) {

        Message message = new Message();

        String editTime = DateTimeUtil.getSysTime();
        activity.setEditTime(editTime);

        int result = activityDao.updateActivity(activity);
        if (result > 0){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }

        return message;
    }

    @Transactional
    @Override
    public Message deleteActivity(String[] ids) {

        Message message = new Message();

        //级联删除
        //查询出需要删除的备注的数量
        int remarkCount = activityRemarkDao.selectByActivtyIds(ids);
        System.out.println("remarkCount===="+remarkCount);

        //删除影响到的备注的数量
        int delremarkCount = activityRemarkDao.deleteByActivtyIds(ids);
        System.out.println("delremarkCount===="+delremarkCount);

        if (remarkCount != delremarkCount){
            message.setSuccess(false);
        }

        //删除Activity
        int delActivtyCount = activityDao.deleteActivityByListId(ids);

        System.out.println("idCount===="+delActivtyCount);
        if (delActivtyCount != ids.length){
            message.setSuccess(false);
        }else {
            message.setSuccess(true);
        }

        return message;
    }

    @Override
    public List<Activity> showActivityByClueId(String clueId) {

        List<Activity> aList = clueActivityRelationDao.showActivityByClueId(clueId);

        return aList;
    }


}
