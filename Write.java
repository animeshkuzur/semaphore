import java.io.*;
public class Write implements Runnable{
	String filename;
	int n;

	public Write(String args, int a){
		filename = args;
		n = a;
	}
	
	public void run(){
		try{
			String line;
			FileWriter fileWriter = new FileWriter(filename);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			while(n>=0){
				s1.acquire();
				System.out.println("write thread executing.");
				bufferedWriter.write("#line "+n);
				bufferedWriter.newLine();
				n--;
				
			}
			bufferedWriter.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
   		catch(Exception ex){
   			System.out.println("Something went terribly wrong. Stay Calm!");
		}
		s2.release();
	}
}