package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import server.ServerHOPP;

import broker.ClientImpt;

public class Server {

	private static final String HOST = "localhost";
	public static void main(String[] args)
	throws Exception
	{
	//
		if (System.getSecurityManager() == null)
		{
			System.setSecurityManager(new SecurityManager());
		}


	   ServerHOPP broker = new ServerHOPP();

	   LocateRegistry.createRegistry(6700);
	   
	   String rmiObjectName = "rmi://localhost:6700/BrokerClient";


	   Naming.rebind(rmiObjectName,broker);

	System.out.println(" rmi server run...\n");
}

}