package bean;

import org.springframework.stereotype.Component; // Component annotation
// import org.springframework.stereotype.Service; // Service annotation

// Service annotation은 Component 에서 특화된 클래스이기 때문에
// 동일한 표현으로 사용할 수 있다.
// 그러나, 비즈니스 로직 서비스(Business Logic Service)를 제공하는 것이 아닌, 
// application에 대한 설정 정보(context, metadata)를 저장하는 클래스인 경우는
// 논리적으로는 Component를 사용하는 것이 합당하다.
@Component("msgBeanValues") 
// @Service("msgBeanValues")
public class MsgBeanValues {
	
  // 아래의 필드에서 private을 하지 않도록 유의하자! (참조 불가 Error 유발!)
  // 호출하여 사용하는 값이기 때문에 public으로 공유해주는 것이 좋겠다.

  public String name = "Spring ~~~ !"; 
  
  public String msg = "Hi ~~~~ !"; 

}