package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import common.BrokerClient;

import database.DBUtility;

public class ServerHOPP extends UnicastRemoteObject implements BrokerClient{

	public ServerHOPP() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	private DBUtility db;
//	public ServerHOPP() throws RemoteException {
//		super();
//		// TODO Auto-generated constructor stub
//	}

	@Override
	public String[] listRoom(String city,String hotel) throws RemoteException {
		// TODO Auto-generated method stub
		db = new DBUtility();
		String [] list = db.selectRoomDatabase(city, hotel);
		 return list;
	}

	@Override
	public double bookRoom(String city, String hotel, String type, String in,
			String out) throws RemoteException {
		// TODO Auto-generated method stub
		db = new DBUtility();
		return db.selectStateFromDatabase(city, hotel, type, in, out);
	}
	public String insertInfo(String id, String name, String cno, String email,
			String hotel)throws RemoteException{
		db = new DBUtility();
		return db.insertUserInfo(id, name, cno, email);
	}
}
