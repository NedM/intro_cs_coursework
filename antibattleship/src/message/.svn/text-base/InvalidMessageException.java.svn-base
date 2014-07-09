package message;

/**
 * 
 * Exception thrown when we attempt to create an ABSMessage from a malformed raw message string
 * TODO incorporate the creation of a syntax error ABSMessage with this exception
 * 
 * @author JVen, nedmurp
 *
 */

public class InvalidMessageException extends Exception
{
	/**
	 * Basic constructor for InvalidMessageException
	 * Calls the empty super()
	 * @author nedmurp
	 */
	public InvalidMessageException()
	{
		super();
	}
	
	/**
	 * Constructor for InvalidMessageException taking a message string
	 * @author nedmurp
	 * @param message String : the message to display with the exception
	 */
	public InvalidMessageException(String message)
	{
		super(message);
	}
	
	/**
	 * Constructor for Invalid message exception that takes message and inner exception
	 * @author nedmurp
	 * @param message String to display with exception
	 * @param inner Throwable : inner exception message that is wrapped in InvalidMessageException
	 */
	public InvalidMessageException(String message, Throwable inner)
	{
		super(message, inner);
	}
	
	/*
	 * Java yells at me if I don't put this here so I do.
	 * 
	 * -JVen
	 */
	
	private static final long serialVersionUID = 1L;

}
