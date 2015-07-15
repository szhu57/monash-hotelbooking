/*
 * the first step to create the remote interface
 * 
 * 
 */
package broker;

import java.rmi.*;
//import java.util.ArrayList;
public interface Client extends Remote {

	public String[] listCity() throws RemoteException;
	public String[] listHotel() throws RemoteException;
	public String[] listCityHotel(String city) throws RemoteException;
	public String[] listRoom(String city, String hotel) throws RemoteException;
	public double bookRoom(String city,String hotel,String type,String in,String out) throws RemoteException;
	public String insertInfo(String   id, String name, String cno, String email,String hotel)throws RemoteException;
}
