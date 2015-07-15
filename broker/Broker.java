/*
 * step 3 create the server and in the server-side to registry the rmi port and communicat
 * 
 * 
 */

package broker;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Broker
{
//private static final String HOST = "localhost";
public static void main(String[] args)
throws Exception
{

	if (System.getSecurityManager() == null)
	{
		System.setSecurityManager(new SecurityManager());
	}


   ClientImpt client = new ClientImpt(args);

   LocateRegistry.createRegistry(6600);
   
   String rmiObjectName = "rmi://localhost:6600/Client";


   Naming.rebind(rmiObjectName,client);

System.out.println(args[0]+"   Broker server run...\n");
}
}