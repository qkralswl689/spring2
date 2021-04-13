package bean.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import bean.MsgBean;

public class MsgApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Resource resource 
			= new ClassPathResource("bean/applicationContext.xml");
		
		@SuppressWarnings("deprecation")
		BeanFactory factory = new XmlBeanFactory(resource);
		MsgBean bean = (MsgBean)factory.getBean("msgBean");
		bean.setMsg();
	}

}