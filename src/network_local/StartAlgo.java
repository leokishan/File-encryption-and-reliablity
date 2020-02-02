package network_local;

public class StartAlgo {

	public static void main(String[] args) throws Exception {
		
		String fileName="data/data.txt";
		MatOps mo=new MatOps();
		
		long start=System.currentTimeMillis();
		
		Double[][] key1=mo.getKey();
		Double[][] key2=mo.getKey();
		
		byte[] fileContent=FileOps.readFileData(fileName);
		
		Double[][] formattedData = FileOps.formatData(fileContent);
		
		System.out.println("before encoding");
		
		Double[][] encoded1=mo.matrixMul(key1, formattedData);
		Double[][] encoded2=mo.matrixMul(key2, formattedData);
		
		FileOps.writeFile(encoded1,"By1");
		FileOps.writeFile(encoded2,"By2");
		
		
		
		String decodeFile1="data/P2_1.txt";
		String decodeFile2="data/P2_2.txt";
		String decodeFile3="data/P1_3.txt";
		
		Double[] df1=FileOps.readEncodedFileData(decodeFile1);
		Double[] df2=FileOps.readEncodedFileData(decodeFile2);
		Double[] df3=FileOps.readEncodedFileData(decodeFile3);
		
		Double[][] doubleData=new Double[3][df1.length];
		doubleData[0]=df1;
		doubleData[1]=df2;
		doubleData[2]=df3;
		
		System.out.println(df1.length);
		System.out.println(df2.length);
		System.out.println(df3.length);
		
		Double[] ecv1=StartAlgo.getECV(decodeFile1,key1,key2);
		Double[] ecv2=StartAlgo.getECV(decodeFile2,key1,key2);
		Double[] ecv3=StartAlgo.getECV(decodeFile3,key1,key2);
		
		Double[][] em=new Double[3][3];
		em[0]=ecv1;
		em[1]=ecv2;
		em[2]=ecv3;
		
		Double[][] emInverse=mo.inverseMatrix3x3(em);
		Double[][] originalData=mo.matrixMul(emInverse, doubleData);
		
		System.out.println("after decoding");
		System.out.println("after rounding");
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<df1.length;j++)
			{
				originalData[i][j]=new Double((Math.round(originalData[i][j])));
				byte b=originalData[i][j].byteValue();
				System.out.print((char)(b));
				
			}
		}
		
		long end=System.currentTimeMillis();
		System.out.println("time is "+(end-start));
		
	}

	private static Double[] getECV(String decodeFile, Double[][] key1, Double[][] key2) {
		// TODO Auto-generated method stub
		int x=Integer.parseInt(String.valueOf(decodeFile.charAt(6)));
		int y=Integer.parseInt(String.valueOf(decodeFile.charAt(8)));
		System.out.println("get ecv "+x);
		System.out.println("get ecv "+y);
		
		if(x==1)
		{return key1[y-1];}
		
		else
		{return key2[y-1];}
	}

}
