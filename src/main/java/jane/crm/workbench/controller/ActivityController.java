package jane.crm.workbench.controller;

import jane.crm.setting.domain.User;
import jane.crm.setting.service.UserService;
import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.ActivityRemark;
import jane.crm.workbench.service.ActivityRemarkService;
import jane.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @Resource
    private UserService userService;

    @Resource
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("workbench/activity/getUserList.do")
    @ResponseBody
    public List<User> getUserList(){
        List<User> uList = userService.getUserList();

        return uList;
    }

    @RequestMapping("workbench/activity/saveActivity.do")
    @ResponseBody
    public Object saveActivity(Activity activity){

        Message message = activityService.saveActivity(activity);

        return message;
    }

    @RequestMapping("workbench/activity/pageList.do")
    @ResponseBody
    public Object pageList(Integer pageNo, Integer pageSize, Activity activity){

        int skipCount = (pageNo-1)*pageSize;

        Map<String,Object> map = new HashMap<>();
        map.put("skipCount",skipCount);
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("name",activity.getName());
        map.put("owner",activity.getOwner());
        map.put("startDate",activity.getStartDate());
        map.put("endDate",activity.getEndDate());



        PaginationVo<Activity> paginationVo = activityService.pageList(map);

        return paginationVo;
    }


    @RequestMapping("workbench/activity/getActivtyById.do")
    @ResponseBody
    public Map<Object,Object> getActivtyById(String id){
        Map<Object,Object> map = new HashMap<>();

        List<User> uList = userService.getUserList();
        Activity activity = activityService.getActivtyById(id);

        map.put("uList",uList);
        map.put("activity",activity);

        return map;

    }


    @RequestMapping("workbench/activity/updateActivity.do")
    @ResponseBody
    public Object updateActivity(Activity activity){

        Message message = activityService.updateActivity(activity);

        return message;
    }


    @RequestMapping("workbench/activity/deleteActivity.do")
    @ResponseBody
    public Object deleteActivity(HttpServletRequest request){

        String[] ids = request.getParameterValues("id");

        Message message = activityService.deleteActivity(ids);

        return message;
    }

    @RequestMapping("workbench/activity/detail.do")
    @ResponseBody
    public ModelAndView detail(String id){

        ModelAndView mv = new ModelAndView();
        Activity activity = activityService.getActivtyById(id);


        mv.addObject("activity",activity);
        mv.setViewName("/workbench/activity/detail.jsp");

        return mv;
    }

    @RequestMapping("workbench/activity/saveRemark.do")
    @ResponseBody
    public Object saveRemark(String activityId,String noteContent,String createBy){

        Message message = activityRemarkService.saveRemark(activityId,noteContent,createBy);

        return message;
    }

    @RequestMapping("workbench/activity/showRemark.do")
    @ResponseBody
    public List<ActivityRemark> showRemark(String activityId){

        List<ActivityRemark> remarkList = activityRemarkService.getRemarkList(activityId);

        return remarkList;
    }

    @RequestMapping("workbench/activity/updateRemark.do")
    @ResponseBody
    public Message updateRemark(ActivityRemark activityRemark){

        Message message = activityRemarkService.updateRemark(activityRemark);

        return message;
    }


    @RequestMapping("workbench/activity/deleteRemark.do")
    @ResponseBody
    public Message updateRemark(String id){

        Message message = activityRemarkService.deleteRemark(id);

        return message;
    }
}
