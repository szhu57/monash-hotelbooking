package corba;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;


public class Server {
	public static void main(String[] args){
		
		try {
//			Runtime runtime = Runtime.getRuntime();
//			runtime.exec("tnameserv -ORBInitialPort 1235");
//
//			String[] arg = new String[1];
//			arg[0] = "localhost";
		      // create and initialize the ORBdd
			String [] args1={"-ORBInitialPort","900"};
		      ORB orb = ORB.init(args1,null);

		      // get reference to rootpoa & activate the POAManager
		      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
		      rootpoa.the_POAManager().activate();

		      // create servant and register it with the ORB
		      BrokerImpt impl = new BrokerImpt();
		      // impl.setORB(orb);

		      // get object reference from the servant
		      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(impl);
		      BrokerClient href = BrokerClientHelper.narrow(ref);

		      // get the root naming context
		      // NameService invokes the name service

		      org.omg.CORBA.Object objRef = orb
		          .resolve_initial_references("NameService");

		      // Use NamingContextExt which is part of the Interoperable
		      // Naming Service (INS) specification.
		      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

		      // bind the Object Reference in Naming
		      String name = "BrokerClient";
		      NameComponent path[] = ncRef.to_name(name);
		      ncRef.rebind(path, href);

		      System.out.println("corba server ready and waiting ...");

		      // wait for invocations from clients
		      orb.run();
		    } catch (org.omg.CORBA.SystemException e) {
		      System.err.println("CORBA ERROR: " + e);
		      e.printStackTrace(System.out);
		    }

		    catch (org.omg.CORBA.UserException e) {
		      System.err.println("CORBA User ERROR: " + e);
		      e.printStackTrace(System.out);
		    }

		    catch (Exception e) {
		      System.err.println("ERROR: " + e);
		      e.printStackTrace(System.out);
		    }

		    //System.out.println("FileTransferServer Exiting ...");
		 }
		 // FileTransferServer
		
		
	}


