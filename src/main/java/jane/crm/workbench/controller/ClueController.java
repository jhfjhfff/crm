package jane.crm.workbench.controller;

import jane.crm.setting.domain.User;
import jane.crm.vo.Message;
import jane.crm.vo.PaginationVo;
import jane.crm.workbench.domain.Activity;
import jane.crm.workbench.domain.Clue;
import jane.crm.workbench.service.ActivityService;
import jane.crm.workbench.service.ClueService;
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
public class ClueController {

    @Resource
    private ClueService clueService;

    @Resource
    private ActivityService activityService;

    @RequestMapping("workbench/clue/getuList.do")
    @ResponseBody
    public List<User> getuList(){

        List<User> uList =  clueService.getuList();

        return uList;
    }

    @RequestMapping("workbench/clue/saveClue.do")
    @ResponseBody
    public Message saveClue(Clue clue){

        Message message =  clueService.saveClue(clue);

        return message;
    }


    @RequestMapping("workbench/clue/pageList.do")
    @ResponseBody
    public PaginationVo<Clue> pageList(Integer pageNo,Integer pageSize){

        PaginationVo<Clue> paginationVo =  clueService.pageList(pageNo,pageSize);

        return paginationVo;
    }


    @RequestMapping("workbench/clue/detail.do")
    @ResponseBody
    public ModelAndView detail(String id,HttpServletRequest request){

        ModelAndView mv = new ModelAndView();

        Clue clue = clueService.detail(id);

        request.getSession().setAttribute("clue",clue);

        mv.addObject("clue",clue);
        mv.setViewName("/workbench/clue/detail.jsp");

        return mv;
    }

    @RequestMapping("workbench/clue/showActivityByClueId.do")
    @ResponseBody
    public List<Activity> showActivityByClueId(String clueId){

        List<Activity> aList = activityService.showActivityByClueId(clueId);

        return aList;

    }

    @RequestMapping("workbench/clue/unbund.do")
    @ResponseBody
    public Message unbund(String activityId,String clueId){

        Message  message = clueService.unbund(activityId,clueId);

        return message;
    }


    @RequestMapping("workbench/clue/getActivities.do")
    @ResponseBody
    public List<Activity> searchActivities(String name){

        List<Activity> acList = clueService.searchActivities(name);

        return acList;
    }

    @RequestMapping("workbench/clue/bund.do")
    @ResponseBody
    public Object bund(HttpServletRequest request){

        String[] activityId = request.getParameterValues("id");

        Clue clue = (Clue) request.getSession().getAttribute("clue");
        String clueId = clue.getId();

        Message message = clueService.bund(activityId,clueId);

        return message;
    }


}
