package network;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;

public class FileOps {

	public static byte[] readFileData(String fileName) throws Exception {
		// TODO Auto-generated method stub
		
		String accessKey = "AKIAJEI3TX7JF3RVKXUA";
		String secretKey = "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";

		Regions regions = Regions.US_EAST_2;

		String bucket = "edupurpose";
		
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(regions).build();

		S3Object s3Object = amazonS3Client.getObject(bucket, fileName);
		String fileData = IOUtils.toString(s3Object.getObjectContent());
		System.out.println(fileData);
		
		byte[] content=fileData.getBytes();
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

	public static void writeFile(Double[][] encoded1, String string, Double[][] key) throws Exception{
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
				uploadFile(fw,key);
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
				uploadFile(fw,key);
			}
		}
	}

	private static void uploadFile(File fw, Double[][] key) {
		// TODO Auto-generated method stub
		String accessKey = "AKIAJEI3TX7JF3RVKXUA";
		String secretKey = "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";

		Regions regions = Regions.US_EAST_2;

		String bucket = "edupurpose";
		
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(regions).build();
		
		Long lkey=(long) 0;
		for(Double[] x:key)
		{
			for(Double y:x)
			{
				lkey=(long) (lkey*10 + y);
			}
		}
		String skey=new String(lkey.toString());
		PutObjectRequest req=new PutObjectRequest(bucket,fw.getName(), fw);
		ObjectMetadata om=new ObjectMetadata();
		om.addUserMetadata("key", skey);
		req.setMetadata(om);

		amazonS3Client.putObject(req);
		
	}

	public static HashMap<String, Double[]> readEncodedFileData(String fileName) throws Exception {
		// TODO Auto-generated method stub
		String accessKey = "AKIAJEI3TX7JF3RVKXUA";
		String secretKey = "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";

		Regions regions = Regions.US_EAST_2;

		String bucket = "edupurpose";
		
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).withRegion(regions).build();

		S3Object s3Object = amazonS3Client.getObject(bucket, fileName);
		String fileData = IOUtils.toString(s3Object.getObjectContent());
		
		String[] stringData=fileData.split("\t");
		Double[] doubleData=new Double[stringData.length];
		
		for(int i=0;i<stringData.length;i++)
		{
			doubleData[i]=new Double(stringData[i]);
		}
		HashMap<String, Double[]> hp=new HashMap<>();
		hp.put("data", doubleData);
		
		ObjectMetadata om=s3Object.getObjectMetadata();
		Long lkey=Long.parseLong(om.getUserMetaDataOf("key"));
		
		System.out.println("long key" + lkey);
		
		Double[][] dkey=new Double[3][3];
		for(int i=2;i>=0;i--)
		{
			for(int j=2;j>=0;j--)
			{
				System.out.println("in for");
				Long x1=new Long(lkey%10);
				dkey[i][j]=x1.doubleValue();
				lkey=lkey/10;
				System.out.println(dkey[i][j]);
			}
		}
		
		int y=Integer.parseInt(String.valueOf(fileName.charAt(3)));
		
		hp.put("key", dkey[y-1]);
		
		return hp;
	}

}
