package network;

import java.util.Random;

public class MatOps {

		//matrix multiplication D=D*D
		Double[][] matrixMul(Double[][] keyMat, Double[][] dataMat) throws Exception {

			try {
				int mA = keyMat.length;
				int nA = keyMat[0].length;
				int mB = dataMat.length;
				int nB = dataMat[0].length;

				if (nA != mB)
					throw new RuntimeException("Illegal matrix dimensions.");

				Double[][] ansMat = new Double[mA][nB];

				for (int i = 0; i < mA; i++)
					for (int j = 0; j < nB; j++)
						ansMat[i][j] = new Double(0);

				for (int i = 0; i < mA; i++)
					for (int j = 0; j < nB; j++)
						for (int k = 0; k < nA; k++)
							ansMat[i][j] += (keyMat[i][k] * dataMat[k][j]);

				return ansMat;
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}
		}


		//inverse matrix calls determinant(returns det) and cofactor(returns matrix of cofactor)
		public Double[][] inverseMatrix3x3(Double[][] matrix) throws Exception {

			try {

				if (matrix == null)
					throw new NullPointerException("Error: Matrix found NULL.");
				else {

					Double determinate = determinateMatrix3x3(matrix);
					Double[][] cofector = cofectorMatrix3x3(matrix);
					
					/*
					 * Re-arranging cofactor
					 */
					
					double temp = cofector[0][1];
					cofector[0][1] = cofector[1][0];
					cofector[1][0] = temp;
					
					temp = cofector[0][2];
					cofector[0][2] = cofector[2][0];
					cofector[2][0] = temp;
					
					temp = cofector[1][2];
					cofector[1][2] = cofector[2][1];
					cofector[2][1] = temp;
					
					/*
					 * dividing using determinant
					 */
					
					for (int i = 0; i < cofector.length; i++) {
						
						for (int j = 0; j < cofector.length; j++) {
							
							cofector[i][j] = (1/determinate) * cofector[i][j];
							
						}
					}
					
					return cofector;
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}

		}

		//Determinant D
		public Double determinateMatrix3x3(Double[][] matrix) throws Exception {

			try {

				if (matrix == null)
					throw new NullPointerException("Error: Matrix found NULL.");
				else {
					
					Double determinate = 0D;
					
					for (int i = 0; i < 1; i++) {
						
						for (int j = 0; j < matrix.length; j++) {
							Double tearm = matrix[i][j];
							
							Double x = 0D, y = 0D;
							
							if(j==0){
								x = matrix[1][1] * matrix[2][2];
								y = matrix[2][1] * matrix[1][2];
							}
							else if(j==1){
								x = matrix[1][0] * matrix[2][2];
								y = matrix[2][0] * matrix[1][2];
							}
							else if(j==2){
								x = matrix[1][0] * matrix[2][1];
								y = matrix[2][0] * matrix[1][1];
							}
							
							if(j==1)
								determinate = determinate + (tearm * (x - y)*-1);
							else
								determinate = determinate + tearm * (x - y);
						}
					}
					
					return determinate;
					
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}

		}

		//Matrix of cofactor
		public Double[][] cofectorMatrix3x3(Double[][] matrix) throws Exception {

			try {

				if (matrix == null)
					throw new NullPointerException("Error: Matrix found NULL.");
				else {
					
					Double[][] cofector = new Double[3][3];
					
					for (int i = 0; i < matrix.length; i++) {
						
						for (int j = 0; j < matrix.length; j++) {
							
							
							if(i==0 && j==0){
								Double x = matrix[1][1] * matrix[2][2];
								Double y = matrix[2][1] * matrix[1][2];
								
								cofector[i][j] = x-y;
							}
							else if(i==0 && j==1){
								Double x = matrix[1][0] * matrix[2][2];
								Double y = matrix[2][0] * matrix[1][2];
								
								cofector[i][j] = (x-y)*-1;
							}
							else if(i==0 && j==2){
								Double x = matrix[1][0] * matrix[2][1];
								Double y = matrix[2][0] * matrix[1][1];
								
								cofector[i][j] = (x-y);
							}
							else if(i==1 && j==0){
								Double x = matrix[0][1] * matrix[2][2];
								Double y = matrix[2][1] * matrix[0][2];
								
								cofector[i][j] = -1*(x-y);
							}
							else if(i==1 && j==1){
								Double x = matrix[0][0] * matrix[2][2];
								Double y = matrix[2][0] * matrix[0][2];
								
								cofector[i][j] = x-y;
							}
							else if(i==1 && j==2){
								Double x = matrix[0][0] * matrix[2][1];
								Double y = matrix[2][0] * matrix[0][1];
								
								cofector[i][j] = -1*(x-y);
							}
							else if(i==2 && j==0){
								Double x = matrix[0][1] * matrix[1][2];
								Double y = matrix[1][1] * matrix[0][2];
								
								cofector[i][j] = (x-y);
							}
							else if(i==2 && j==1){
								Double x = matrix[0][0] * matrix[1][2];
								Double y = matrix[1][0] * matrix[0][2];
								
								cofector[i][j] = -1*(x-y);
							}
							else if(i==2 && j==2){
								Double x = matrix[0][0] * matrix[1][1];
								Double y = matrix[1][0] * matrix[0][1];
								
								cofector[i][j] = (x-y);
							}
							
						}
					}
					
					return cofector;
					
				}

			} catch (Exception exception) {
				exception.printStackTrace();
				throw exception;
			}

		}
	
		
		public static void main(String[] args) throws Exception {
			Double[][] x=new Double[1][3];
			Double[][] y=new Double[3][3];
			Double[][] ans=new Double[1][3];
			MatOps m1=new MatOps();
			
			x[0][0]=19D;
			x[0][1]=14D;
			x[0][2]=36D;

//			x[1][0]=91D;
//			x[1][1]=28D;
//			x[1][2]=36D;
//			
//			x[2][0]=23D;
//			x[2][1]=45D;
//			x[2][2]=57D;
			
			y[0][0]=-0.5D;
			y[0][1]=3D;
			y[0][2]=-0.5D;

			y[1][0]=0.75D;
			y[1][1]=-4D;
			y[1][2]=1.25D;
			
			y[2][0]=23D;
			y[2][1]=45D;
			y[2][2]=57D;
			
			for(int i=0;i<3;i++)
					{System.out.print(x[0][i]+" ");}
				System.out.println();
			
			System.out.println("---------------------");
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++)
					{System.out.print(y[i][j]+" ");}
				System.out.println();
			}
			System.out.println("---------------------");
			ans=m1.matrixMul(x,y);
			//ans=m1.inverseMatrix3x3(y);
			for(int i=0;i<3;i++)
					{System.out.print(ans[0][i]+" ");}
				System.out.println();
			
			

			
		}


		public Double[][] getKey() {
			// TODO Auto-generated method stub
			Random r=new Random();
			Double[][] key=new Double[3][3];
			
			
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					key[i][j]=(double) r.nextInt(9);
				}
			}
			return key;
		}
}
