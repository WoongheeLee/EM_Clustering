package em;

import java.io.*;
import java.util.*;

public class Preprocessing {
	public static void getGdata() {
		HashMap<Integer,Double> hash = new HashMap<Integer,Double>();
		int count = 0;
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader("./gData/g2-1-10.txt"));
			while((line = reader.readLine()) != null) {
				if(line.length() > 2) {
					String str = "";
					for(int i = 5; i <8; i++ ) {
//						System.out.print(line.charAt(i));
						str += line.charAt(i);
					}
//					System.out.println();
					hash.put(count, Double.parseDouble(str));
					count++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("./gData/data"));
			for(int i = 0; i < count; i++) {
				bw.write(hash.get(i)+"\n");
				bw.flush();
			}
			bw.close();
		} catch (IOException e) { 
			e.printStackTrace(); 
			System.exit(1);
		}
	}
	
	public static void getGroundTruth() {
		double[][] groundTruth = new double[15][2];
		
		int count = 0;
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader("s1-cb.txt"));
			while((line = reader.readLine()) != null) {

				String X = "";
				String Y = "";
				
				for(int i = 0; i < 6; i++) {
					X += line.charAt(i);
				}
				
				for(int i = 7; i < 13; i++) {
					Y += line.charAt(i);
				}
				groundTruth[count][0] = Double.parseDouble(X);
				groundTruth[count][1] = Double.parseDouble(Y);
				count++;
			}

		} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("groundTruth.txt"));
			for(int i = 0; i < groundTruth.length; i++) {
				bw.write(groundTruth[i][0]+"\t"+groundTruth[i][1]+"\n");
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
	public static void getData1() {
		double[][] points = new double[5000][2];
		
		int count = 0;
		try {
			String line;
			BufferedReader reader = new BufferedReader(new FileReader("s1.txt"));
			while((line = reader.readLine()) != null) {
				String X = "";
				String Y = "";
				
				for(int i = 4; i < 10; i++) {
//					System.out.print(line.charAt(i));
					X += line.charAt(i);
				}
				for(int i = 14; i < 20; i++) {
//					System.out.print(line.charAt(i));
					Y += line.charAt(i);
				}
//				System.out.println();
				points[count][0] = Double.parseDouble(X);
				points[count][1] = Double.parseDouble(Y);
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
//		for(int i = 0; i < points.length; i++) {
//			for(int j = 0; j < 2; j++) {
//				System.out.print(points[i][j]+"\t");
//			}
//			System.out.println();
//		}
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));
			for(int i  = 0; i < points.length; i++) {
				bw.write(points[i][0]+"\t"+points[i][1]+"\n");
				bw.flush();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		getGdata();
	}
}
