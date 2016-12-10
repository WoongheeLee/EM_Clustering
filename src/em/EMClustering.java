package em;

import java.io.*;
import java.util.*;

public class EMClustering {
	
	private static double[][] getData(String fName) {
		HashMap<Integer,ArrayList<Double>> temp = new HashMap<Integer,ArrayList<Double>>();
		int count = 0;
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(fName));
			while((line = reader.readLine()) != null) {
				if(line.length() > 2) {
					String[] arr = line.split("\t");
					ArrayList<Double> list = new ArrayList<Double>();
					list.add(Double.parseDouble(arr[0]));
					list.add(Double.parseDouble(arr[1]));
					temp.put(count, list);
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		double[][] data = new double[count][2];
		for(int i = 0; i < count; i++) {
			ArrayList<Double> list = temp.get(i);
			data[i][0] = list.get(0);
			data[i][1] = list.get(1);
		}
		
		return data;
	}
	
	private static void print(double[][] A) {
		for(int i = 0; i < A.length; i++) {
			System.out.print(i+":\t");
			for(int j = 0; j < A[0].length; j++) {
				System.out.print(A[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	private static void print(double[] A) {
		for(int i = 0; i < A.length; i++) {
			System.out.println(i+":\t"+A[i]);
		}
	}
	
	private static double initialMu(double min, double max) {		
		return Math.random()*(max-min);
	}
	
	private static double initialSigma(double min, double max) {
		return Math.random()*(max-min) / 2.0;
	}
	
	
	private static double getPhi(double x, double mu, double sig) {
		double phi1 = 1.0 / (Math.sqrt(2.0 * Math.PI)*sig);
		double phi2 = -1.0 * Math.pow(x-mu, 2) / (2.0 * Math.pow(sig, 2));
		phi2 = Math.exp(phi2);
		
		double phi = phi1 * phi2;		
		return phi;
	}
	
	private static double getMin(double[][] A, int col) {
		double min = Double.MAX_VALUE;
		for(int i = 0; i < A.length; i++) {
			if (A[i][col] < min) {
				min = A[i][col];
			}
		}
		return min;
	}
	
	private static double getMax(double[][] A, int col) {
		double max =Double.MIN_VALUE;
		for(int i = 0; i < A.length; i++) {
			if(max < A[i][col]) {
				max = A[i][col];
			}
		}
		return max;
	}
	
	
	public static void main (String[] args) {
		String fName = "data.txt";
		double[][] X = getData(fName);
		fName = "groundTruth.txt";
		double[][] GT = getData(fName);
//		print(X);
//		print(GT);
		
		int K = 15;
		double[][] pi = new double[K][2];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < 2; j++) {
				pi[i][j] = 1.0/15.0;
			}
		}
//		print(pi);

		double[][] mu = new double[K][2];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < 2; j++) {
				mu[i][j] = initialMu(getMin(X, j), getMax(X, j));
			}
		}		
//		print(mu);
		
		double[][] sig = new double[K][2];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < 2; j++) {
				sig[i][j] = initialSigma(getMin(X, j), getMax(X, j));
			}
		}
//		print(sig);
		
		
		
		//E-Step
		double[] sumPhi = new double[2];
		for(int k = 0; k < K; k++) {
			for(int i = 0; i < X.length; i++) {
				for(int j = 0; j < 2; j++) {
					sumPhi[j] += pi[k][j] * getPhi(X[i][j], mu[k][j], sig[k][j]);
				}
			}
		}
//		print(sumPhi);
		
		for(int k = 0; k < K; k++) {
			for(int i = 0; i < X.length; i++) {
				for(int j = 0; j < 2; j++) {
					
				}
			}
		}
	}
}
