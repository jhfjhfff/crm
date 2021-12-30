package jane.crm.workbench.service.Impl;

import jane.crm.setting.domain.User;
import jane.crm.util.DateTimeUtil;
import jane.crm.util.UUDIUtil;
import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.dao.ActivityDao;
import jane.crm.workbench.dao.ClueActivityRelationDao;
import jane.crm.workbench.dao.ClueDao;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.Clue;
import jane.crm.workbench.domain.ClueActivityRelation;
import jane.crm.workbench.service.ClueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueDao clueDao;

    @Resource
    private ClueActivityRelationDao clueActivityRelationDao;

    @Resource
    private ActivityDao activityDao;

    @Override
    public List<User> getuList() {

        List<User> uList = clueDao.getuList();

        return uList;
    }

    @Transactional
    @Override
    public Message saveClue(Clue clue) {

        Message message = new Message();

        clue.setId(UUDIUtil.getUUID());
        clue.setCreateTime(DateTimeUtil.getSysTime());

        int count = clueDao.saveClue(clue);

        if (count>0){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }

        return message;
    }

    @Override
    public PaginationVo<Clue> pageList(Integer pageNo, Integer pageSize) {

        PaginationVo<Clue> paginationVo = new PaginationVo<>();

        //获取clue总条数
        int total = clueDao.getClueCount();

        //计算略过的条数
        int skipCount = (pageNo-1) * pageSize;
        System.out.println("skipCount====="+skipCount);

        //获取clue信息列表
        Map<String,Integer> map = new HashMap<>();
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);

        List<Clue> dataList = clueDao.getClueList(map);
        for (Clue clue : dataList){
            System.out.println("clue====="+clue);
        }

        paginationVo.setTotal(total);
        paginationVo.setDataList(dataList);

        return paginationVo;
    }

    @Override
    public Clue detail(String id) {

        Clue clue = clueDao.queryClueById(id);

        return clue;
    }

    @Transactional
    @Override
    public Message unbund(String activityId,String clueId) {

        Message message = new Message();

        Map<String,String> map = new HashMap<>();
        map.put("activityId",activityId);
        map.put("clueId",clueId);

        int count = clueActivityRelationDao.unbund(map);

        if (count >0 ){
            message.setSuccess(true);
        }else {
            message.setSuccess(false);
        }

        return message;
    }

    @Override
    public List<Activity> searchActivities(String name) {

        List<Activity> acList = activityDao.searchActivities(name);

        return acList;
    }

    @Transactional
    @Override
    public Message bund(String[] activityId,String clueId) {

        Message message = new Message();

        //向tbl_clue_activity_relation中添加关联关系
        ClueActivityRelation car = new ClueActivityRelation();
        for (String acId :
                activityId) {
            car.setActivityId(acId);
            car.setId(UUDIUtil.getUUID());
            car.setClueId(clueId);
            int count = clueActivityRelationDao.bund(car);

            if (count ==0 ){
                message.setSuccess(false);
            }else {
                message.setSuccess(true);
            }
        }


        return message;
    }
}
