import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


public class ReadFile {
	public  static void main(String[] argvs)
	{
		File f=new File("fileName");
		
		
		
		//FileInputStream ��д��λ  =�� �ֽ�
		FileInputStream fis=null;
		
		
		try {
			fis=new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//InputStreamReader ��д��λ  =��java char �ַ�
		InputStreamReader isr=null;
		
		
		try {
			isr = new InputStreamReader(fis,"gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//BufferedReader  ��д��λ �ַ�&��
		BufferedReader br= new BufferedReader(isr);
		
		try {
			br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
