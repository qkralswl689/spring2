package bean;

import org.springframework.beans.factory.annotation.Autowired; // 생성자 주입(injection) Autowired annotation..
import org.springframework.beans.factory.annotation.Value; // 생성자 및 단순 값("Hi !") 주입에 따른 인자값(Value) annoatation.
import org.springframework.stereotype.Service; // (전체적인) Service annotation 지원을 위해 추가됨.

@Service("msgBean") // annotation 형태의 Bean 설정(wiring)
public class MsgBeanImpl implements MsgBean {
	
	private String name;
	
	// @Value("Hi !") 
	// 값 주입을 할 경우 멤버 필드 위에서 직접 주입할 수도 있고
	// 아래의 setMsg 메소드와 같이 setter에서 값을 주입(injection)할 수도 있다.
	// 또한 다른 방법은 이러한 값을 할당하는 별도의 클래스(MsgBeanValues) 
	// 를 두어서 SpEL(since Spring 3.0)
	// 방식으로 별도로 값을 호출하는 방법을 이용할 수도 있다. 
	// 그렇게 한다면 고정값을 두지 않고 값을 조정할 수 있는 장점이 있다.
	@Value("#{msgBeanValues.msg}")
	private String msg;
	
	// annotation 사용시 반드시 기본 생성자를 정의해주도록 한다 : 에러(error) 방지
	// 여러 곳에 생성자에 Autowired를 하게 되면 에러 발생 !
	// @Autowired
	public MsgBeanImpl() {
		
	}
	
	// (주의사항) 생성자에 Autowired는 단지 한 곳에서만 적용이 가능하다 !
	@Autowired
	//public MsgBeanImpl(@Value("Spring") String name) {
	 public MsgBeanImpl(@Value("#{msgBeanValues.name}") String name) {
		// super();
		this.name = name;
	}

	@Override
	public void setMsg() {
		System.out.println(msg + " " + name);
	}
	
	public String getMsg() {
		return msg;
	}
	
	// @Autowired
	//public void setMsg(@Value("Hi !") String msg) {
	public void setMsg(String msg) {			
		this.msg = msg;
	}

}