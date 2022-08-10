
public class NoQIdFoundException extends Exception {

	  private String msg = null;
	    NoQIdFoundException()
	    {
	        super();
	    }
	    NoQIdFoundException(String msg)
	    {
	        super(msg);
	        this.msg = msg;
	    }
	    @Override
	    public String toString() {
	        return msg;
	    }

	    @Override
	    public String getMessage() {
	        return msg;
	    }
}
