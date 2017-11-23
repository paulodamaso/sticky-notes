public final BasicTask implements Task {
    private final Date date;
    private final String description;
    
    BasicTask(Date date, String description){
      this.date = date;
      this.description = description;
    }
    
    public String description() {
      return this.description;
    }
    
    public Date date() {
      return date();
    }
}
