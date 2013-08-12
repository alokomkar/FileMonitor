package in.beanlifecycle;

import javax.sql.DataSource;

public interface DAO_FileChangeLog {

  /** 
	 * This is the method to be used to initialize
	 * database resources ie. connection.
	 */
	public void setDataSource(DataSource ds);
	/** 
	 * This is the method to be used to check if 
	 * a file has been accessed using the FileLog table log.
	 */
	public void isFileAccessed();
}
