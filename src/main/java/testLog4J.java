import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class testLog4J {
	private static Logger logger  = Logger.getLogger(testLog4J.class.getName());
	
	public static void  main(String[] args)
	{
		//BasicConfigurator.configure ();  �Զ����ٵ�ʹ��ȱʡLog4j������ 
		//DOMConfigurator.configure ( String filename ) ����ȡXML��ʽ�������ļ���
		PropertyConfigurator.configure("conf/log4j.properties");
		logger.info("logger.info");
		logger.error("logger.error");
	}
	
}
