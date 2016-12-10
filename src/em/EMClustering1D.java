package em;

import java.io.*;
import java.util.*;

public class EMClustering1D {
	private static double[] getData(String fName) {
		HashMap<Integer,Double> temp = new HashMap<Integer,Double>();
		int count = 0;
		
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader(fName));
			while((line = reader.readLine()) != null) {
				if(line.length() > 2) {
					int key = count;
					double value = Double.parseDouble(line.split("\t")[0]);
					temp.put(key, value);
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		double[] data = new double[count];
		for(int i = 0; i < count; i++) {
			data[i] = temp.get(i);
		}
		
		return data;
		
	}
	
	private static void print(double[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	
	private static void print(double[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	private static double[] initialPi (int K) {
		double[] pi = new double[K];
		
		for(int i = 0; i < K; i++) {
			pi[i] = 1.0/(double)K;
		}
		
		return pi;
	}
	
	private static double getPhi(double x, double mu, double sig) {
		double phi1 = 1.0 / (Math.sqrt(2.0 * Math.PI)*sig);
		double phi2 = -1.0 * Math.pow(x-mu, 2) / (2.0 * Math.pow(sig, 2));
		phi2 = Math.exp(phi2);
		
		double phi = phi1 * phi2;		
		return phi;
	}
	
	private static double[] initialMu(int K, double min, double max) {
		double[] mu = new double[K];
		
		for(int i = 0; i < K; i++) {
			mu[i] = Math.random()*(max-min);
		}
		return mu;
	}
	
	private static double getMin(double[] data) {
		double min = Double.MAX_VALUE;
		for(int i = 0; i < data.length; i++) {
			if(min > data[i]) {
				min = data[i];
			}
		}
		return min;
	}
	
	private static double getMax(double[] data) {
		double max = Double.MIN_VALUE;
		for(int i = 0; i < data.length; i++) {
			if( max < data[i]) {
				max = data[i];
			}
		}
		return max;
	}
	
	private static double[] initialSigma(int K, double min, double max) {
		double[] sig = new double[K];
		
		for(int i = 0; i < K; i++) {
			sig[i] = Math.random()*(max-min) / ((Math.random()+1.0)*2.0);
		}
		return sig;
	}
	
	final private static int K = 2;
	
	public static void main(String[] args) {
		String dataFile = "./gData/data";
		
		// about 2800 tuples
		double[] X = getData(dataFile);
//		print(data);
		
		// two PI
		double[] pi = initialPi(K);
//		print(pi);
		
		// two Mu
		double[] mu = initialMu(K, getMin(X), getMax(X));
//		print(mu);
		
		// two Sigma
		double[] sig = initialMu(K, getMin(X), getMax(X));
//		print(sig);
		
		// two Probability
		double[][] prob = new double[X.length][K];
		
		
		
		// E-Step
		// compute probability C_j given X_i for 1 to k and all X
		
		// sum of probability
		double[] sumProb = new double[K];
		for(int i = 0; i < K; i++) {
			for(int j = 0; j < X.length; j++) {
				sumProb[i] += pi[i] * getPhi(X[j], mu[i], sig[i]);
			}
		}
//		print(sumProb);
		
		// to compute probability
		for(int i = 0; i < prob.length; i++) {
			for(int j = 0; j < K; j++) {
				prob[i][j] = pi[j] * getPhi(X[i], mu[j], sig[j]) / sumProb[j];
			}
		}
//		print(prob);
		
		// to check prob matrix
//		double prob1 = 0d;
//		double prob2 = 0d;
//		for(int i = 0; i < prob.length; i++) {
//			prob1 += prob[i][0];
//			prob2 += prob[i][1];
//		}
//		System.out.println(prob1+"\t"+prob2);
		
		
		
		// M-Step
		// compute pi_j, mu_j, sig_j for 1 to K
		
	}
}
