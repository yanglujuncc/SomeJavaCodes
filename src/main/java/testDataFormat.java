import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testDataFormat {
	
	/*
	 * //
	 * 
	 * ע��HH��hh���ϲ�ͬ��HH��ʾ��24Сʱ��ȡ��hh��ʾ��12Сʱ��ȡ
	     ���õĸ�ʽ�ĺ��壬ժ��Jdk��ע���Сд�ĺ���ͨ���ǲ�ͬ�ģ�
		��ĸ    ����                            ʾ��
		y    Year                         1996;96             ��һ��
		M    Month in year  J             uly;Jul;07           һ���е���һ��
		m    Minute in hour               30                    һ��Сʱ�еĵڼ�����
		w    Week in year                 27                   һ���еĵڼ�������
		W    Week in month                2                    һ�����еĵڼ�������
		D    Day in year                  189                 һ���еĵڼ���
		d    Day in month                 10                  һ�����еĵڼ���
		H    Hour in day (0-23)           0                   һ���еĵڼ���Сʱ��24Сʱ�ƣ�
		h    Hour in am/pm (1-12)         12                  һ�������硢����ĵڼ���Сʱ��12Сʱ�ƣ�
		S    Millisecond                  978                 ������
		s    Second in minute             55                  һ���ӵĵڼ���
	 */
	public static void main(String[] args) {
		
		
		long longTime = 1329148803000L;
		String aStringTime = "2012-02-14T00:00:03";
		SimpleDateFormat a_format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		
		
		SimpleDateFormat a_format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date aDate = new Date(longTime);
		System.out.println(aDate);
		System.out.println(a_format2.format(aDate));

		

		try {
			Date aDate2 = a_format1.parse(aStringTime);
			System.out.println(aDate2);
			System.out.println(a_format2.format(aDate2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
