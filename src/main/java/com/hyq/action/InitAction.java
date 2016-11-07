package com.hyq.action;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.hyq.entity.Type;
import com.hyq.entity.User;
import com.hyq.service.ArticleService;
import com.hyq.service.TypeService;
import com.hyq.service.UserService;
import com.hyq.service.WisdomService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * 初始化的Action类
 * @author Administrator
 */

@Component		//标注一个普通的Spring Bean类
@WebListener
public class InitAction implements ServletContextListener,ApplicationContextAware{

	private static ApplicationContext applicationContext;		//启动的时候会给该变量赋值,后面获取的都是同一个实例(static)

	public void contextDestroyed(ServletContextEvent application) {
		// 应用关闭时执行该方法
		System.out.println("关闭应用");
	}

	/**
	 * 初始化容器的时候，把页面一些基本不变的数据存在application缓存中
	 */

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		UserService userService = (UserService) applicationContext.getBean("userService");
		ServletContext application = servletContextEvent.getServletContext();
		User admin = userService.getUserById(1);
		application.setAttribute("admin",admin);
		TypeService typeService = (TypeService) applicationContext.getBean("typeService");
		List<Type> appli_typeList = typeService.findTypeList();
		application.setAttribute("appli_typeList",appli_typeList);
		ArticleService articleService = (ArticleService)applicationContext.getBean("articleService");

		List<Object> year_month_list = articleService.searchGroupByYM();
		Map<String,String> year_month = new LinkedHashMap<String, String>();		//有序map才能按照查询结果顺序插入
		for (Object ym:year_month_list) {
			year_month.put(((Object[])ym)[0].toString(),((Object[])ym)[1].toString());
		}
		application.setAttribute("year_month",year_month);

		WisdomService wisdomService = (WisdomService)applicationContext.getBean("wisdomService");
		application.setAttribute("wisdom",wisdomService.getTheShowOne());
	}
	/**
	 * 该方法在contextInitialized方法之前执行(注意，这里执行contextInitialized和setApplication方法不再同一个实例中)
	 */

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//System.out.println("setApplicationContext："+this);
		InitAction.applicationContext = applicationContext;
	}

}
