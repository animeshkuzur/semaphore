import java.io.*;
public class Read implements Runnable{
	String filename;
	int n;

	public Read(String args,int a){
		filename = args;
		n = a;
	}

	public void run(){
		try{
			String line;
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while(n>=0){
				s2.acquire();
				System.out.println("read thread executing.");
				while((line = bufferedReader.readline())!=null){
					System.out.println(line);
				}
				n--;
				
			}
			bufferedReader.close();
		}
		catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");                
        }
   		catch(Exception ex){
   			System.out.println("Something went terribly wrong. Stay Calm!");
		}
		s1.release();
	}
} 