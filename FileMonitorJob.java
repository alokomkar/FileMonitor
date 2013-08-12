package in.beanlifecycle;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FileMonitorJob extends QuartzJobBean{
  private JDBCTemplateFileAccessed JTFAObject;
	
	public void setJTFAObject(JDBCTemplateFileAccessed jTFAObject) {
		JTFAObject = jTFAObject;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		JTFAObject.isFileAccessed();
		
	}

}
