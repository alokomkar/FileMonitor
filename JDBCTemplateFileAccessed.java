package in.beanlifecycle;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplateFileAccessed implements DAO_FileChangeLog {

  private DataSource ds;
	private FileAccessEvent FAEvent;
	
	public void setFAEvent(FileAccessEvent fAEvent) {
		FAEvent = fAEvent;
	}

	private JdbcTemplate jdbcTemplateObject;
	
	
	@Override
	public void setDataSource(DataSource ds) {
		this.ds = ds;
		this.jdbcTemplateObject = new JdbcTemplate(this.ds);
	}
	
	
	@Override
	public void isFileAccessed() {
		
		String FilePath = this.FAEvent.getFilePath();
		String SQL = "select AccessTime from FileLog where AbsoluteFilePath = ?";
		String AccessTime = jdbcTemplateObject.queryForObject(SQL, new Object[]{FilePath},String.class);
		
		BasicFileAttributes Attrs = FileAttrs(FilePath);
		
		String NewAccessTime = Attrs.lastAccessTime().toString();
		if(NewAccessTime.equalsIgnoreCase(AccessTime)){
			System.out.println("File "+FilePath+" has not been Accesed.\n"+
					"Last Access Time is :" + AccessTime);
		}
		else{
			System.out.println("File "+FilePath+" has been Accesed.\n"+
					"Last Access Time is :" + Attrs.lastAccessTime());
			updateLog(FilePath,NewAccessTime);
		}
		
		return;
		
	}
	
	public void updateLog(String filepath, String AccessTime){
		System.out.println("Updating Access Time for next time detection.");
		String SQL = "update FileLog set AccessTime = ? where AbsoluteFilePath = ? ";
		jdbcTemplateObject.update(SQL, new Object[]{AccessTime,filepath});
	}
	
	/* Function to retrieve Basic File Attributes of given file */
	public BasicFileAttributes FileAttrs(String filepath){
		File chosenfile = new File(filepath);
		Path file_dir = Paths.get(chosenfile.getParent());
		Path file = file_dir.resolve(chosenfile.getName());
		BasicFileAttributes attrs = null;
		try {
			attrs = Files.readAttributes(file, BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return attrs;

	}


}
