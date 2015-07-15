package corba;
import java.rmi.RemoteException;
import java.util.ArrayList;

import server.ServerHOPP;

public class BrokerImpt extends BrokerClientPOA {

	private ServerHOPP serverHOPP ;
	
	BrokerImpt()throws RemoteException{
		
	}
	@Override
	public String[] listRoom(String city, String hotel) {
		// TODO Auto-generated method stub
		try
		{
			serverHOPP = new ServerHOPP();
			String[] list = serverHOPP.listRoom(city, hotel);
			return list;
			
		}  catch(RemoteException e){
			System.out.println("Yichang");
		     return null;
		}
		
	}

	@Override
	public double bookRoom(String city, String hotel, String type,	String in, String out) {
		// TODO Auto-generated method stub
		try{
			serverHOPP = new ServerHOPP();
			double id =serverHOPP.bookRoom(city, hotel, type, in, out);
			return id;
			
		} catch(RemoteException e){ 
		System.out.println("Yichang");
	     
		}
		return 0;
	}

	@Override
	public String insertInfo(String id, String name, String cno, String email,
			String hotel) {
		// TODO Auto-generated method stub
		try{
			serverHOPP = new ServerHOPP();
			String res =serverHOPP.insertInfo(id, name, cno, email,hotel);
			return res;
			
		} catch(RemoteException e){ 
		System.out.println("Yichang");
	     
		}
		return null;
	}

}
