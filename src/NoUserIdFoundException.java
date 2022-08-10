



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */

public class NoUserIdFoundException extends Exception
{
   private String msg = null;
    NoUserIdFoundException()
    {
        super();
    }
    NoUserIdFoundException(String msg)
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
