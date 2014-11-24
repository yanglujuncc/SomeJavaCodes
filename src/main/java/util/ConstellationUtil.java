package util;

import java.util.Calendar;

public class ConstellationUtil {

	
	public static final String[] zodiacArr = { "��", "��", "��", "��", "��", "ţ", "��", "��", "��", "��", "��", "��" };   
	  
	public static final String[] constellationArr = { "ˮƿ��", "˫����", "ĵ����", "��ţ��", "˫����", "��з��", "ʨ����", "��Ů��", "�����",   
	        "��Ы��", "������", "ħ����" };   
	  
	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };   
	  
	/**  
	 * �������ڻ�ȡ��Ф  
	 * @return  
	 */  
	public static String date2Zodica(Calendar time) {   
	    return zodiacArr[time.get(Calendar.YEAR) % 12];   
	}   
	  
	/**  
	 * �������ڻ�ȡ����  
	 * @param time  
	 * @return  
	 */  
	public static String date2Constellation(Calendar time) {   
	    int month = time.get(Calendar.MONTH);   
	    int day = time.get(Calendar.DAY_OF_MONTH);   
	    if (day < constellationEdgeDay[month]) {   
	        month = month - 1;   
	    }   
	    if (month >= 0) {   
	        return constellationArr[month];   
	    }   
	    //default to return ħ��   
	    return constellationArr[11];   
	}  
	
	public static void main(String[] args){
		
		
		Calendar time=Calendar.getInstance();
		time.setTimeInMillis(System.currentTimeMillis());
		time.set(1987,12-1,25);
		System.out.println(date2Zodica(time));
		System.out.println(date2Constellation(time));
	}
}
