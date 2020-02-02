package network_local;

import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD_HASH {
	
	public static void main(String[] args) throws Exception {
		long start=System.currentTimeMillis();
		MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream("data/data.txt");
        
        byte[] dataBytes = new byte[1024];
     
        int nread = 0; 
        while ((nread = fis.read(dataBytes)) != -1) {
          md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();
     
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        long end=System.currentTimeMillis();
        System.out.println("Digest(in hex format):: " + sb.toString());
        System.out.println("Time is "+(end-start));
	}

}
