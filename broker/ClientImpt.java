/*
 * Second step ,to create clientimpt to implements the interface
 * 
 */

package broker;
import java.rmi.*;
import java.rmi.server.*;
//import java.util.ArrayList;
import java.util.Scanner;

import common.BrokerClient;
import common.BrokerFactory;

//import rmi.BrokerHOPP;

import database.DBUtility;

public class ClientImpt extends UnicastRemoteObject implements Client {
	private BrokerClient brokerHOPP;
	private DBUtility db;
	private String arg;
	//private BrokerHOPP brokerHOPP;
	public ClientImpt(String [] args)throws RemoteException{
		super(); 
		 arg =args[0];
	}

	@Override
	public String[] listCity() throws RemoteException {
		// TODO Auto-generated method stub
		db = new DBUtility();
		String[] cities = db.selectCityFromDatabase();
		return cities;
	}
	public String[] listHotel() throws RemoteException{
		db= new DBUtility();
		String [] hotel = db.selectHotelFromDatabase();
		return hotel;
	}
	public String[] listCityHotel(String city) throws RemoteException{
		db = new DBUtility();
		String[] hotel = db.selectCityHotelFromDatabase(city);
		return hotel;
		
	}
	//@SuppressWarnings("resource")
	public String[] listRoom(String city,String hotel) throws RemoteException{
		
		
		
		//boolean flag = false;// it can create a factory to implement the corba and rmi 
		//while(!flag){
			//Scanner s = new Scanner(System.in);
			///System.out.println("please input the technology you want to use corba or rmi");
			//String para = s.next();
			//if(arg.equals("corba")||arg.equals("rmi")){
		
			brokerHOPP = BrokerFactory.creatHOPP(arg);
			String[] tempList=brokerHOPP.listRoom(city, hotel);
			//flag = true;
	     	return tempList;
			
			//}
//			else{
//				System.out.println("wrong, please input again!!!");
//			}
			
		//}
//		if(para.contains("rmi")){
//			flag = true;
//			rmi.BrokerHOPP brokerHOPP = new rmi.BrokerHOPP();
//			// ArrayList<String> tempList = new ArrayList<String>();
//			 
		
//		}
//		else if(para.contains("corba")){
//			flag = true;
//			corba.BrokerHOPP brokerHOPP;
//			brokerHOPP = new corba.BrokerHOPP();
//			// ArrayList<String> tempList = new ArrayList<String>();
//			 
//				String[] tempList=brokerHOPP.listRoom(city, hotel);
//				return tempList;
//		}
//		else {
//			System.out.println("wrong message!!!"); 
//		}
//		
//	}
		//return null;
		
	}
	//@SuppressWarnings("resource")
	public double bookRoom (String city, String hotel, String type, String in,String out) throws RemoteException{
//		
//		boolean flag = false;// it can create a factory to implement the corba and rmi 
//		while(!flag){
//			Scanner s = new Scanner(System.in);
//			System.out.println("please input the technology you want to use corba or rmi");
//			String para = s.next();
//			if(para.equals("corba")||para.equals("rmi")){
		
			brokerHOPP = BrokerFactory.creatHOPP(arg);
			double tempList=brokerHOPP.bookRoom(city, hotel, type, in, out);
			//flag = true;
	     	return tempList;
			
//			}
//			else{
//				System.out.println("wrong, please input again!!!");
//			}
//			
//		}
		//return 0;
	}
	//@SuppressWarnings("resource")
	public String insertInfo(String   id, String name, String cno, String email,String hotel)throws RemoteException{
//
//		boolean flag = false;// it can create a factory to implement the corba and rmi 
//		while(!flag){
//			Scanner s = new Scanner(System.in);
//			System.out.println("please input the technology you want to use corba or rmi");
//			String para = s.next();
//			if(para.equals("corba")||para.equals("rmi")){
//		
			brokerHOPP = BrokerFactory.creatHOPP(arg);
			String res=brokerHOPP.insertInfo(id, name, cno, email, hotel);
			//flag = true;
	     	return res;
			
//			}
//			else{
//				System.out.println("wrong, please input again!!!");
//			}
//			
//		}
//		return null;
	}
	
}
