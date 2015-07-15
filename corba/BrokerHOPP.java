package corba;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.io.IOException;
import org.omg.CORBA.SystemException;

public class BrokerHOPP implements common.BrokerClient {
 private BrokerClient impl;
	public BrokerHOPP(){
	 try{
		 String [] args1={"-ORBInitialPort","900"};
	      // create and initialize the ORB
	      ORB orb = ORB.init(args1,null);

	      // get the root naming context
	      org.omg.CORBA.Object objRef = orb
	          .resolve_initial_references("NameService");

	      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

	      // resolve the Object Reference in Naming
	      String name = "BrokerClient";
	       impl = BrokerClientHelper.narrow(ncRef.resolve_str(name));

	     // System.out.println("Obtained a handle on server object: " + impl);

	 } catch (Exception e) {
	      System.out.println("ERROR : " + e);
      e.printStackTrace(System.out);
	 }
	 
		 
}
	public String[] listRoom(String city,String hotel){
		String [] list = impl.listRoom(city, hotel);
		 return list;
	}
	@Override
	public double bookRoom(String city, String hotel, String type, String in,String out) throws RemoteException {
		// TODO Auto-generated method stub
		double id = impl.bookRoom(city, hotel, type, in, out);
		return id;
	}
	@Override
	public String insertInfo(String id, String name, String cno, String email,
			String hotel) throws RemoteException {
		// TODO Auto-generated method stub
		String res = impl.insertInfo(id, name, cno, email, hotel);
		return res;
	}
}
	  


	

