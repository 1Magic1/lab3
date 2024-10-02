import java.util.*;
import org.apache.xmlrpc.*;

public class JavaClient {
	public static void main (String [] args)
	{
	try {
	XmlRpcClient server = new
	XmlRpcClient("http://localhost:8080");
	Vector params = new Vector();
	int a=3; int b=3;
	try{
	a = Integer.parseInt(args[0]);
	b = Integer.parseInt(args[1]);
	}
	catch(Exception e){System.out.println("Error: "+e);}
	params.addElement(a);
	params.addElement(b);
	Object result = server.execute("sample.sum", params);
	int sum = ((Integer) result).intValue();
	System.out.println("The sum is: "+ sum);
	} catch (Exception exception) { System.err.println("JavaClient: " + exception); }
	}
}
