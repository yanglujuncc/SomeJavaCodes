package ylj.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.Logger;





public class FilesInput {
	
	private static Logger logger = Logger
	.getLogger(FilesInput.class.getName());
	
	String characterSet;
	File dataDir ;
	File[] dataFiles;
	
	
	// ��log��־�ļ��� ������
	//��ǰ��ȡ���ļ�
	int currentFileIndex=-1;
	BufferedReader currentReader = null;
	
	
	
	long readline=0;
	long totalLine=0;
	
	public FilesInput(String CharacterSet){
		characterSet=CharacterSet;
	}
	
	
	public void setCharacterSet(String newCharacterSet ){
		characterSet=newCharacterSet;
	}
	
	public long remains(){
		return totalLine-readline;
	}
	public long total(){
		return totalLine;
	}
	
	//��ȡ�������ļ�
	private static File[] getAllSubFiles(File dir) {
		if (dir == null || !dir.isDirectory())
			return null;

		List<File> allSubFiles = new LinkedList<File>();

		Queue<File> unProcessFiles = new LinkedList<File>();

		unProcessFiles.add(dir);

		while (true) {
			File curentFile = unProcessFiles.poll();
			if (curentFile == null)
				break;
			if (curentFile.isDirectory()) {
				for (File subFile : curentFile.listFiles()) {
					unProcessFiles.add(subFile);
				}
			} else {
				allSubFiles.add(curentFile);
			}
		}

		return (File[]) allSubFiles.toArray(new File[allSubFiles.size()]);

	}
	
	
	public long loadFromDirName(String dirName) throws IOException {

		 dataDir = new File(dirName);
		 dataFiles = getAllSubFiles(dataDir);
		 totalLine=0;
		 
		 
		 //���ļ�������linux ϵͳ��Сд���У�windows ������
		 Arrays.sort(dataFiles);
		 
		 
		 //���������ļ��������ܹ��ɶ�ȡ������
		for (int i = 0; i < dataFiles.length; i++) {
			File file = dataFiles[i];
			
			logger.info("Reading File(" + i + "/" + dataFiles.length + ")  :"
					+ file.getName());
			
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, characterSet);
			BufferedReader br = new BufferedReader(isr);

			while (br.readLine() != null)
				totalLine++;
			br.close();

		}
		
		if (dataFiles.length > 0) {

			FileInputStream fis = new FileInputStream(dataFiles[0]);
			InputStreamReader isr = new InputStreamReader(fis, "gbk");
			currentReader = new BufferedReader(isr);
			currentFileIndex = 0;
			

		}else
		{
			currentReader =null;
			currentFileIndex = -1;
	
		}
		return totalLine;

	}
	
	public String getLine() throws IOException {

		while (currentReader != null) {

			String aLine = null;

			aLine = currentReader.readLine();

			if (aLine == null) {

				currentReader.close();
				
				/*
				 * if (fileReaderIterator.hasNext()) currentReader =
				 * fileReaderIterator.next(); else currentReader = null;
				 */
				currentFileIndex++;

				if (currentFileIndex < dataFiles.length) {
					FileInputStream fis = new FileInputStream(
							dataFiles[currentFileIndex]);
					InputStreamReader isr = new InputStreamReader(fis, "gbk");
					currentReader = new BufferedReader(isr);

				} else {
					currentReader = null;
				}

			} else {
				readline++;
				return aLine;
			}

		}

		return null;

	}
}
