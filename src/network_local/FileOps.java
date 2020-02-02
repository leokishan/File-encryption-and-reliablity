package network_local;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileOps {

	public static byte[] readFileData(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
		File f=new File(fileName);
		byte[] content=Files.readAllBytes(f.toPath());
		return content;
	}

	public static Double[][] formatData(byte[] fileContent) {
		// TODO Auto-generated method stub

		List<Byte> l=new ArrayList<>();
		for(byte x:fileContent)
		{l.add(x);}
		
		int len=fileContent.length;
		int offset=len%3;
		
		if(offset!=0)
		{
			for(int i=0;i<(3-offset);i++)
			{l.add(new Byte((byte) 0));}
		}
		
		Double[][] formattedData=new Double[3][(l.size())/3];
		int k=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<( (l.size())/3 );j++)
			{
				formattedData[i][j]=new Double(l.get(k));
				k++;
			}
		}
		
		return formattedData;
	}

	public static void writeFile(Double[][] encoded1, String string) throws Exception{
		// TODO Auto-generated method stub
		int in=0;
		if(string.equals("By1"))
		{
			for(Double[] i:encoded1)
			{
				in++;
				String fname="data/P1_"+in+".txt";
				String x="";
				for(Double j:i)
				{x=x+j+"\t";}
				File fw=new File(fname);
				fw.createNewFile();
				Files.write(fw.toPath(), x.getBytes());
				
			}
		}
		
		if(string.equals("By2"))
		{
			for(Double[] i:encoded1)
			{
				in++;
				String fname="data/P2_"+in+".txt";
				String x="";
				for(Double j:i)
				{x=x+j+"\t";}
				File fw=new File(fname);
				fw.createNewFile();
				Files.write(fw.toPath(), x.getBytes());
				
			}
		}
	}

	public static Double[] readEncodedFileData(String decodeFile3) throws Exception {
		// TODO Auto-generated method stub
		File f=new File(decodeFile3);
		String stringFile=new String(Files.readAllBytes(f.toPath()));
		String[] stringData=stringFile.split("\t");
		Double[] doubleData=new Double[stringData.length];
		
		for(int i=0;i<stringData.length;i++)
		{
			doubleData[i]=new Double(stringData[i]);
		}
		
		
		return doubleData;
	}

}
