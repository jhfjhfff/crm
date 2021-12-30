package jane.crm.listener;

import jane.crm.setting.domain.DicValue;
import jane.crm.setting.service.DicService;
import jane.crm.setting.service.Impl.DicServiceImpl;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SysInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        //获取全局作用域对象
        ServletContext application = event.getServletContext();

        //通过util获取spring容器
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(application);

        //通过spring容器获取DicServiceImpl业务类
        DicService bean = (DicService) applicationContext.getBean("dicServiceImpl");

        //获取map中的数据
        Map<String, List<DicValue>> dicInfos = bean.getDicValues();
        Set<String> keys = dicInfos.keySet();

        for (String type:keys){

            application.setAttribute(type,dicInfos.get(type));
        }

        for (String type:keys){

            System.out.println(type);
            System.out.println(application.getAttribute(type));
            System.out.println("--------------------------------------------------");
        }


        //把map中的数据写入到全局作用域对象中
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
