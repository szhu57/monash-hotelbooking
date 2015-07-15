package client;

import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import broker.Client;

public class ClientHOOP {
	private Client stub;
	
	public ClientHOOP(){
		
		if (System.getSecurityManager() == null)
		{
		   System.setSecurityManager(new SecurityManager());
		}
		 
		try{

		 stub = (Client)Naming.lookup("rmi://localhost:6600/Client");
		
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
	 public String[] cities() {
		    
		    try {
		    
		    	return stub.listCity();	
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
			return null;
		    
		  }
	 public String [] hotels(){
		 try{
			 return stub.listHotel();
		 }catch (Exception e) {
		      e.printStackTrace(); return null;
		    }
		
	 }
	 public String [] selectCityHotel(String city){
		 try{
			 return stub.listCityHotel(city);
		 }catch (Exception e) {
		      e.printStackTrace();
		      return null;
		    }
		
	 }
	 public String[] listRoom(String city,String hotel){
		 try{
			 return stub.listRoom(city,hotel);
		 }catch (Exception e) {
		      e.printStackTrace();
		      return null;
		    }
		 
	 }
	 public double bookRoom(String city,String hotel, String type, String in,String out) {
		 try{
		 return stub.bookRoom(city, hotel, type, in, out);
	 }catch (Exception e) {
	      e.printStackTrace(); return 0;
	    }
	
	 }
	public String insertInfo(String id, String name, String cno, String email,
			String hotel) {
		// TODO Auto-generated method stub

			try{
				return stub.insertInfo(id, name, cno, email, hotel);
			}catch (Exception e) {
			      e.printStackTrace();return null;
		    }
		 
		 
			
	}

}