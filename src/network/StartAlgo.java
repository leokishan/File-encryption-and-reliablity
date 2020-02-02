package network;

import java.util.HashMap;

public class StartAlgo {

	public static void main(String[] args) throws Exception {
		String fileName="data1.txt";
		MatOps mo=new MatOps();
		
		Double[][] key1=mo.getKey();
		Double[][] key2=mo.getKey();
		
		byte[] fileContent=FileOps.readFileData(fileName);
		
		Double[][] formattedData = FileOps.formatData(fileContent);
		
		System.out.println("before encoding");
		for(Double[] i:formattedData)
		{
			for(Double j:i)
			{System.out.print(j+" ");}
			System.out.println(i.length);
		}
		System.out.println();
		Double[][] encoded1=mo.matrixMul(key1, formattedData);
		Double[][] encoded2=mo.matrixMul(key2, formattedData);
		
		FileOps.writeFile(encoded1,"By1",key1);
		FileOps.writeFile(encoded2,"By2",key2);
		
		System.out.println("done");
		
		String decodeFile1="P2_1.txt";
		String decodeFile2="P2_2.txt";
		String decodeFile3="P1_3.txt";
		
		HashMap<String, Double[]> df1=FileOps.readEncodedFileData(decodeFile1);
		HashMap<String, Double[]> df2=FileOps.readEncodedFileData(decodeFile2);
		HashMap<String, Double[]> df3=FileOps.readEncodedFileData(decodeFile3);
		
		
		
		Double[][] doubleData=new Double[3][df1.get("data").length];
		doubleData[0]=df1.get("data");
		doubleData[1]=df2.get("data");
		doubleData[2]=df3.get("data");
		
		Double[][] em=new Double[3][3];
		em[0]=df1.get("key");
		em[1]=df2.get("key");
		em[2]=df3.get("key");
		
		System.out.println("data");
		for(Double[] i:doubleData)
		{
			for(Double j:i)
			{System.out.print(j+" ");}
			System.out.println();
		}
		
		Double[][] emInverse=mo.inverseMatrix3x3(em);
		Double[][] originalData=mo.matrixMul(emInverse, doubleData);
		
		System.out.println("after decoding");
		for(Double[] i:originalData)
		{
			for(Double j:i)
			{System.out.print(j+" ");}
			System.out.println();
		}
		System.out.println();
		System.out.println("after rounding");
		
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<df1.get("data").length;j++)
			{
				originalData[i][j]=new Double((Math.round(originalData[i][j])));
				byte b=originalData[i][j].byteValue();
				System.out.print((char)(b));
			}
		}
		
	}
}
