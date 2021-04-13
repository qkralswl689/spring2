package bean;

public class MsgBeanImpl2 implements MsgBean {
	
	private String name;
	private String msg;
	
	public MsgBeanImpl2(String name) {
		super();
		this.name = name;
	}

	@Override
	public void setMsg() {
		System.out.println(msg + " " + name);
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
