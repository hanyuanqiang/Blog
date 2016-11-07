package com.hyq.test;

import com.hyq.dao.BaseDAO;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Administrator on 2016/9/29.
 */
public class AnnotationTest {

    public static void main(String[] args) {
        /*BeanFactory ctx = new XmlBeanFactory(
                new ClassPathResource("applicationContext.xml"));*/
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseDAO baseDAO = ctx.getBean("baseDAO", BaseDAO.class);
        System.out.println(baseDAO);
    }

}
