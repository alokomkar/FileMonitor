package in.beanlifecycle;



import org.apache.log4j.Logger;

public class FileAccessEvent {

  private String FilePath;
	
	Logger log = Logger.getLogger(FileAccessEvent.class.getName());
	
	public FileAccessEvent(String filepath){
		this.FilePath = filepath;
	}
	
	public String getFilePath(){
		return this.FilePath;
	}
	
	
	public void init(){
		log.info("Initializing File Access Event...");
		System.out.println("Initializing File Access Event...");
	}
	
	public void destroy(){
		log.info("Destroying File Access Event...");
		System.out.println("Destroyinging File Access Event Event...");
	}
}
