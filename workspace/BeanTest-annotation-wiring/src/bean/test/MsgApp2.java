package bean.test;

import org.springframework.context.support.GenericXmlApplicationContext;

import bean.MsgBean;

public class MsgApp2 {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx 
			= new GenericXmlApplicationContext();
		ctx.load("classpath:bean/applicationContext.xml");
		ctx.refresh(); 
		// Context 정보 갱신 메소드 추가 :  
		// 이 정보가 없으면 위의 xml 정보 변경후 제대로 반영 안될 수 있습니다.
		
		MsgBean bean = (MsgBean)ctx.getBean("msgBean");
		bean.setMsg();
	}

}