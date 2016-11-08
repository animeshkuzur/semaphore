import java.io.*;
import java.util.concurrent.Semaphore;

class main{
	public static void main(String args[]) {

		String filename = "file.txt";
		int n = 5;
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(0);

		Thread read = new Thread(new Runnable(){
			public void run(){
				try{
					int a = n;
					String line;
					
					while(a>=0){
						s2.acquire();
						FileReader fileReader = new FileReader(filename);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						System.out.println("read thread executing.");
						while((line = bufferedReader.readLine())!=null){
							System.out.println(line);
						}
						a--;
						bufferedReader.close();
						Thread.currentThread().sleep(1000);
						s1.release();
					}
					
					
				}
				catch(FileNotFoundException ex) {
            		System.out.println("Unable to open file '" + filename + "'");                
        		}
		   		catch(Exception ex){
		   			System.out.println("Something went terribly wrong. Stay Calm!");
				}
				
			}
		});

		Thread write = new Thread(new Runnable(){
			public void run(){
				try{
					int b = n;
					String line;
					
					while(b>=0){
						s1.acquire();
						FileWriter fileWriter = new FileWriter(filename,true);
						BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
						System.out.println("write thread executing.");
						bufferedWriter.write("#line "+b);
						bufferedWriter.newLine();
						bufferedWriter.close();
						Thread.currentThread().sleep(1000);
						s2.release();
						b--;
					}
					
				}
				catch(FileNotFoundException ex) {
        		    System.out.println("Unable to open file '" + filename + "'");                
        		}
		   		catch(Exception ex){
		   			System.out.println("Something went terribly wrong. Stay Calm!");
				}
				
			}
		});
		read.start();
		write.start();
	}
}