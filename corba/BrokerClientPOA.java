package corba;


/**
* corba/BrokerClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from BrokerClient.idl
* Thursday, May 14, 2015 9:01:16 PM CST
*/

public abstract class BrokerClientPOA extends org.omg.PortableServer.Servant
 implements corba.BrokerClientOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("listRoom", new java.lang.Integer (0));
    _methods.put ("bookRoom", new java.lang.Integer (1));
    _methods.put ("insertInfo", new java.lang.Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // corba/BrokerClient/listRoom
       {
         String city = in.read_string ();
         String hotel = in.read_string ();
         String $result[] = null;
         $result = this.listRoom (city, hotel);
         out = $rh.createReply();
         corba.roomrateHelper.write (out, $result);
         break;
       }

       case 1:  // corba/BrokerClient/bookRoom
       {
         String city = in.read_string ();
         String hotel = in.read_string ();
         String type = in.read_string ();
         String checkin = in.read_string ();
         String checkout = in.read_string ();
         double $result = (double)0;
         $result = this.bookRoom (city, hotel, type, checkin, checkout);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 2:  // corba/BrokerClient/insertInfo
       {
         String id = in.read_string ();
         String name = in.read_string ();
         String cno = in.read_string ();
         String email = in.read_string ();
         String hotel = in.read_string ();
         String $result = null;
         $result = this.insertInfo (id, name, cno, email, hotel);
         out = $rh.createReply();
         out.write_string ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:corba/BrokerClient:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public BrokerClient _this() 
  {
    return BrokerClientHelper.narrow(
    super._this_object());
  }

  public BrokerClient _this(org.omg.CORBA.ORB orb) 
  {
    return BrokerClientHelper.narrow(
    super._this_object(orb));
  }


} // class BrokerClientPOA