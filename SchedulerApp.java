package in.beanlifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulerApp {

  public static void main(String args[]){
		AbstractApplicationContext context = 
				new ClassPathXmlApplicationContext("BeansFAEAllModule.xml");
		
	}
}
