package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
//import java.util.ArrayList;

public interface BrokerClient extends Remote{
	
	public String[] listRoom(String city,String hotel) throws RemoteException; 
	public double bookRoom(String city,String hotel,String type,String in,String out) throws RemoteException;
	public String insertInfo(String   id, String name, String cno, String email,
			String hotel)throws RemoteException;
}
