



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */

public class NoFAQFoundException extends Exception
{
   private String msg = null;
    NoFAQFoundException()
    {
        super();
    }
    NoFAQFoundException(String msg)
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
