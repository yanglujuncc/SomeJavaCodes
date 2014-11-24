import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testPattern {
	public static void main(String[] argvs) throws ParseException
	{
		System.out.println("************case  *************");
		
		
		  //������Java��ͷ,�����β���ַ���
		  Pattern pattern = Pattern.compile("^Java.*");
		  Matcher matcher = pattern.matcher("Java������");
		  
	
		  boolean b= matcher.matches();
		  //����������ʱ��������true�����򷵻�false
		 
		  System.out.println(b);
		  String str="2012-02-26T02:38:01+08:00 qn162-157 master_27[28264]: [86588]INFO|CHAT|����(127115) 1819960027 ̧ͷ΢Ц0476 IP:123.82.13.224 ��ô�˰�����Ȼ������ס��";
		 
		 // ��Java�����У�\\��ʾҪ����������ʽ�ķ�б�ߣ����Һ�����ַ����������塣
		  boolean a= Pattern.matches(".*INFO\\|CHAT.*", str);
	
		  System.out.println("a="+a);
		 
		  //�Զ������ָ��ַ���ʱ
		  System.out.println("************case 0 *************");
		
		  Pattern pattern0 = Pattern.compile("[, |]+");
		  String[] strs = pattern0.split("Java Hello World  Java,Hello,,World|Sun");
		  for (int i=0;i<strs.length;i++) {
		      System.out.println(strs[i]);
		  } 
		  
		  
		  
		  //�����滻���״γ����ַ���
		  System.out.println("************case 1 *************");
		 
		  Pattern pattern1 = Pattern.compile("������ʽ");
		  Matcher matcher1 = pattern1.matcher("������ʽ Hello World,������ʽ Hello World");
		  //�滻��һ���������������
		
		  System.out.println(matcher1.replaceFirst("Java"));
		  System.out.println(matcher1.replaceAll("c++"));
		  
		  
		  //�ҳ�ƥ�����
		  System.out.println("************case 2 *************");
		  Pattern pattern2 = Pattern.compile("Java.");
		  
		  
		 // String message_reg1="[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2} .+ .+ \\[[0-9]+\\]INFO|CHAT|IMMESSAGE .+";
		  String message_reg="[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}\\+\\d{2}:\\d{2}\\s.+\\s.+\\s\\[\\d+\\]INFO\\|CHAT\\|IMMESSAGE\\s.+";
		  String line="2012-02-26T21:49:13+08:00 hz174-20 gas_141[15771]: [86588]INFO|CHAT|IMMESSAGE SenderId:1724310141 RecverId:1721550141 IP:61.130.215.128 Message:���ܿ���������";

		  Pattern pattern_message = Pattern.compile(message_reg);
		  Matcher matcher_message=pattern_message.matcher(line);
		  if(matcher_message.matches())
		  {
			  System.out.println(line);
		  }
		  else
		  {
			  System.out.println("not match");
		  }
		  String[] fileds=line.split("\\s");
		  System.out.println(Arrays.toString(fileds));
		  String time=fileds[0].split("\\+")[0].replace("T", " ");
		  String senderID=fileds[4].split(":")[1];
		  String recverID=fileds[5].split(":")[1];
		  String messageContent=fileds[7].split(":")[1];
		  
		  System.out.println("time="+time);
		  System.out.println("senderID="+senderID);
		  System.out.println("recverID="+recverID);
		  System.out.println("messageContent="+messageContent);
		  
		 
		  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		  Date date = sdf.parse(time);
		  long timeUnix= date.getTime();
		  
		
		  System.out.println("timeUnix="+timeUnix);
		  System.out.println("senderID="+senderID);
		  System.out.println("recverID="+recverID);
		  System.out.println("messageContent="+messageContent);
		  
		  
		   String reg_adj="����.*((sb)|TM|�в�|�Բ�|��Ǯ|ǿ|��|��ƽ��|������|����ƽ|����|������|û��˼|������|����|IMBA|BUG|bug|Bug)";
		   
		   String yiren="(((^|��|��|��|��|��|��|��|��|��|��|��ͬС)��(��|��|��|���쿪|��|��|��|��|$))|([������������ڱ����������������������������������������߮�������������������߽��������٫�����й������������������ʳ��������������������������������������������޲���������ܲ��������β������������������ζ��һ������ҽ�����ҿҼҾ��������][���������������������������������������ܵ��])|yiren|yren|yi)";
		    	
		   String meizhe="((��([^������]|$))|([ûöõüݮ÷ý������úø����ù����������������ÿ���þ][��ر������������ߡ�������������������������������])|meizhe|mei|m|mz|gm)";
			

		   Pattern pattern_reg_adj = Pattern.compile(yiren,Pattern.CASE_INSENSITIVE);
		   Matcher matcher_reg_adj=pattern_reg_adj.matcher(line);
		   if(matcher_reg_adj.matches())
		   {
			   System.out.println("match:"+line);
		   }
		   else
			   System.out.println("not match:"+line);
		   
		   
		   String no_adj=".*(?<!��)����(?!ô).*";
		   
		   line="�в��а�";
	

		   Pattern pattern_no_adj = Pattern.compile(no_adj,Pattern.CASE_INSENSITIVE);
		   matcher_reg_adj=pattern_no_adj.matcher(line);
		   if(matcher_reg_adj.matches())
		   {
			   System.out.println("match:"+line);
		   }
		   else
			   System.out.println("not match:"+line);
		
		 //  line="���ͬ";
		//   String no_ciyu= ".*��(?!��ͬ��).*";
		   
		   //line="w����ʹ�";
		   //line="MZ��";
		   //line="MZ ҽҩ��";
		   // line="MZnan����";
		   //line="MZnan ����ι�";
		   //line="MZnan ������";
		  // line="MZnan ����JS��";
		   line="MZnan ����JS��";
		   
		   String yiren_reg= ".*(((?<!(��|��|��|��|��|��|��|��|��|��|��ͬС))��(?!(��|��|��|���쿪|��|��|��|��)))|((?<![a-z])yiren(?![a-z]))|((?<![a-z])yr(?![a-z]))|([������������ڱ����������������������������������������߮�������������������߽��������٫�����й������������������ʳ��������������������������������������������޲���������ܲ��������β������������������ζ��һ������ҽ�����ҿҼҾ��������][���������������������������������������ܵ��])).*";
		   String mei_reg=".*((��(?!(��|��|��)))|((?<![a-z])meizhe(?![a-z]))|((?<![a-z])mz(?![a-z]))|((?<![a-z])mei(?![a-z]))|((?<![a-z])m(?![a-z]))|([ûöõüݮ÷ý������úø����ù����������������ÿ���þ][��ر������������ߡ�������������������������������])).*";
		   String yishi_reg=".*(((?<!(��|��|��|��|��|��|��|У|��|ӹ|��|��|����|����))ҽ(?!(ҩ|Ժ|��|��|��|��|��|��|��|��)))|((?<![a-z])yishi(?![a-z]))|((?<![a-z])ys(?![a-z]))|((?<![a-z])yi(?![a-z]))|((?<![0-9])1(?![0-9]))|([������������ڱ����������������������������������������߮�������������������߽��������٫�����й������������������ʳ��������������������������������������������޲���������ܲ��������β������������������ζ��һ����ҽ�����ҿҼҾ��������][ʮʲʯʱʶʵʰ��ʴʳ��ݪ˶��ʿ��������ʾʽ��������������������������������ݪ����߱������������ʷʸ��ʹʼʻʺʬʧʦʭʫʩʨʪ])).*";
		   String fangshi_reg=".*(((?<!(��|��|��|��|��|��|��|��|��|��|��|��|��|��|��|��|��|��|ħ|��|��|ƫ|ƽ|ǰ|��|��|ҩ|��|��|Զ|��))��(?!(��|��|��|��|��|��|��|��|��|��|��|λ|Բ|��|��|��|��|��)))|((?<![a-z])fangshi(?![a-z]))|((?<![a-z])fs(?![a-z]))|((?<![a-z])fang(?![a-z]))|((?<![a-z])f(?![a-z]))|([�����������зŷ·��ݷ���������������][ʲʯʱʶʵʰ��ʴʳ��ݪ˶��ʿ����������ʾʽ������������������������������������ݪ������߱�������������ʷʸ��ʹʼʻʺʬʧʦʭʫʩʨʪ��������])).*";
		   String xiake_reg=".*(((?<!(��|��|��|��|��))��(?!(��)))|((?<!(��|��|��))Ͽ(?!(��)))|(��(?!(խ|��|·���|С|��)))|((?<!(��|��|��|��|��|��))ϼ(?!(��|��)))|((?<!(��|��Χ))Ϻ)|((?<![a-z])xiake(?![a-z]))|((?<![a-z])xk(?![a-z]))|((?<![a-z])xia(?![a-z]))|((?<![a-z])x(?![a-z]))|([��ϻ����Ͽ������������Ͼ���Ͻϼ�����Ļ�������Ϻ��Ϲ][�οǿ��ɿ˿̿���������﾿ɿ�᳿ʺǿ���������������������������������])).*";
		   String daoke_reg=".*(((?<!(��|��|��|��|��|��|��|�|��|����|��|��|�κ�|��|��|��|��|��|��|��|��|��|��˿|��|ĥ|ţ|��|ǧ|����|ˮ��|��|��ͷ|����|��|Ц���|����|��|��|բ|ա|ս|ָ��))��(?!(��|����|����|��|Ƭ|��|��|��|����|������|�⽣Ӱ|��|��|��|ǹ|��|ɽ|��|����|��)))|((?<![a-z])daoke(?![a-z]))|((?<![a-z])dk(?![a-z]))|((?<![a-z])dao(?![a-z]))|((?<![a-z])d(?![a-z]))|([����������������������������߶���][�ǿ��ɿ˿̿���������﾿ɿ�᳿οʺǿ���������������������������������])).*";
		   String jiashi_reg=".*(((?<!(��|��|��ɽ|������|��|ָ|��|��|��|��|��|����|��|ֺ|װ))��(?!(״��|��|��|��|��|����|��|��|����|����)))|((?<![a-z])jiashi(?![a-z]))|((?<![a-z])js(?![a-z]))|((?<![a-z])jia(?![a-z]))|((?<![a-z])j(?![a-z]))|([�Ҽ׼�ۣ�׼����������ռۼݼּܼټ޼ڼ���μּؼ������Ӽ�٤������Ю������������������ʼ���][ʲʯʱʶʵʰ��ʴʳ��ݪ˶��ʿ����������ʾʽ������������������������������������ݪ������߱�������������ʷʸ��ʹʼʻʺʬʧʦʭʫʩʨʪ��������])).*";
		   String sheshou_reg=".*(((?<!(��|��|��|��|��|��ɳ|��|ɢ|ɨ|��|��|��|Ͷ|Ӱ|ӳ|��|��|ע))��(?!(��|��|Ƶ|��|ɱ|��|��|��|��|��|��)))|((?<!(��|��|��|��|��))��(?!(��|��|����|��|��)))|((?<![a-z])sheshou(?![a-z]))|((?<![a-z])gongjian(?![a-z]))|((?<![a-z])ss(?![a-z]))|((?<![a-z])gj(?![a-z]))|((?<![a-z])she(?![a-z]))|((?<![a-z])gong(?![a-z]))|((?<![a-z])s(?![a-z]))|([����������Ҷ��������ʰ�����������������������][�������������������������])).*";
		 
		   //String no_d="\\d{3}(?!abc)\\w";
		  //  line= "123a";
		   
		   Pattern pattern_no_ciyu = Pattern.compile(sheshou_reg,Pattern.CASE_INSENSITIVE);
		   matcher_reg_adj=pattern_no_ciyu.matcher(line);
		   
		   if(matcher_reg_adj.matches())
		   {
			   System.out.println("match:"+line);
		   }
		   else
			   System.out.println("not match:"+line);
	
	}
}
