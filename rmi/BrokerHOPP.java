package rmi;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import common.BrokerClient;


public class BrokerHOPP implements BrokerClient {
	


		private BrokerClient stub;
		
		public BrokerHOPP(){
			
			if (System.getSecurityManager() == null)
			{
			   System.setSecurityManager(new SecurityManager());
			}
			 
			try{

			 stub = (BrokerClient)Naming.lookup("rmi://localhost:6700/BrokerClient");
			
			}catch(ConnectException conEx){
			
			System.out.println(
			"Unable to connect to server!");
			System.exit(1);
			}
			catch(Exception ex){
				ex.printStackTrace();
				System.exit(1);
			}
		}
	
		public String[] listRoom(String city, String hotel) throws RemoteException{
			return stub.listRoom(city, hotel);
		}
		public double bookRoom (String city, String hotel, String type, String in,String out) throws RemoteException{
			return stub.bookRoom(city, hotel, type, in, out);
		}
		public String insertInfo(String   id, String name, String cno, String email,String hotel) throws RemoteException{
			return stub.insertInfo(id, name, cno, email, hotel);
		}
}
