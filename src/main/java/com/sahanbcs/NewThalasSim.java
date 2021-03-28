package com.sahanbcs;
import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
 
import java.net.Socket;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOUtil;

public class NewThalasSim {

	public static void main(String[] args) throws IOException, ISOException {
		
		Socket socket =null;
		DataInputStream din = null;
		DataOutputStream dout = null;
		 
		
		try {
			socket = new Socket( "localhost" , 9998);
			din = new DataInputStream(socket.getInputStream());
			dout = new DataOutputStream(socket.getOutputStream());

//			byte[] request  = ISOUtil.hex2byte("0008303030304E4F3030");  
//			byte[] response = new byte[64];
			
			

			//status Check
//			byte[] request = ("0000"+"NO00").getBytes();
			
			byte[] request = ("0000"+"B2" +"0010" + "7A30FD618D7C2361" ).getBytes();
			
			String hlen = Integer.toHexString(request.length);
			String hd =  ISOUtil.zeropad(hlen, 4);
			 request  =ISOUtil.concat(ISOUtil.hex2byte(hd), request)   ;  
			byte[] response = new byte[64];
			
			
//			String aaa =  ISOUtil.byte2hex( "a".getBytes())  ;
//			System.out.println("THa Ascii Value  " +   aaa );
			System.out.println("\nREQUEST :\n" + ISOUtil.hexdump(request));
			
			dout.write(request);
			dout.flush();
			
			final int len = din.read(response,0,64);
			
			
			
			System.out.println("\nRESPONSE :\n" + ISOUtil.hexdump(response));
			
			
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}finally {
			if (din != null) {
				din.close();
			}
			if(dout != null){
				dout.close();
			}
			if(socket != null){
				socket.close();
			}
			
		}
		
	}
	
}

 


 