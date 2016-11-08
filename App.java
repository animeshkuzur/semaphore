import java.io.*;
import java.util.concurrent.Semaphore;

class App{
	public static void main(String args[]) throws IOException {

		String filename = "file.txt";
		int n = 5;
		Semaphore s1 = new Semaphore(1);
		Semaphore s2 = new Semaphore(0);

		Thread read = new Thread(new Read(filename,n));
		Thread write = new Thread(new Write(filename,n));

		read.start();
		write.start();
	}
}