package net.bplaced.clayn.cfs.err;

/**
 *
 * @author Clayn <clayn_osmato@gmx.de>
 * @since 0.3.0
 */
public class CFSException extends RuntimeException
{

    public CFSException()
    {
    }

    public CFSException(String message)
    {
        super(message);
    }

    public CFSException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CFSException(Throwable cause)
    {
        super(cause);
    }

    public CFSException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
