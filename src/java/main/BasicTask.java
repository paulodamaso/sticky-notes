package main;
import java.util.Date;

public final class BasicTask implements Task {
	
	private final int id;
    private final Date date;
    private final String description;
    
    BasicTask(int id, Date date, String description){
    	this.id = id;
    	this.date = date;
    	this.description = description;
    }
    
    public int id() {
    	return this.id;    	
    }
    
    public String description() {
      return this.description;
    }
    
    public Date date() {
      return this.date;
    }
}
