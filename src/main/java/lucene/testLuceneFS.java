package lucene;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
 
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
public class testLuceneFS {
	
	/**
	 * 
	 * @author davenzhang
	 * @email zsnjuim@163.com
	 */

	    //�ú������ڴ������������Խ���д��һ���������ࡣ
	    public  static void createIndex() throws IOException{
	        //���ݴ���ļ���
	        File  f_doc=new File("Lucene/data");
	        //��������ļ���
	        File  f_idx=new File("Lucene/index");
	         
	        //directory���������ڴ���IndexWriter
	        Directory directory=FSDirectory.open(f_idx);
	        //conf���������ڴ���IndexWriter.
	        IndexWriterConfig conf=new IndexWriterConfig(Version.LUCENE_45,new StandardAnalyzer(Version.LUCENE_45));
	        IndexWriter writer=new IndexWriter(directory,conf);
	         
	        //�������ļ�������������ļ���������;
	        File[] textFiles =f_doc.listFiles();
	        for(int i=0;i<textFiles.length;i++)
	        {
	            if(textFiles[i].isFile())//&&textFiles[i].getName().endsWith( ".txt" )  
	            {
	                Document document=new Document();//ע��,���Document������ѭ���д���������֮ǰһֱ�����洴����
	                System.out.println( " File"+ textFiles[i].getCanonicalPath() +"���ڱ����� . " );   
	                FileInputStream temp=new FileInputStream (textFiles[i]);
	                 
	                //��ȡ�ļ�����
	                int len=temp.available();
	                byte[] buffer=new byte[len];
	                temp.read(buffer);
	                temp.close();
	                String content=new String(buffer);
	 
	                //System.out.println(content);//����
	                //����Field
	                document.add(new Field("path", textFiles[i].getPath(), TextField.TYPE_STORED));
	                document.add(new Field("body", content, TextField.TYPE_STORED));          
	                
	                
	                
	                writer.addDocument(document); 
	 
	                writer.commit();//�������ύ����Ȼ�Ļᱨ��
	                 
	            }
	        }
	        System.out.println("��������Ŀ��"+writer.numDocs());
	        writer.close();  
	    }
	     
	    //��ѯ����
	    public static void TestQuery(String querystring) throws IOException,ParseException
	    {
	        //����һ��IndexSearcher
	        File  f=new File("Lucene/index");
	        Directory directory =FSDirectory.open(f);   
	        IndexReader ireader=DirectoryReader.open(directory);
	        IndexSearcher searcher=new IndexSearcher(ireader);//�������ǰ�İ汾�����仯��
	         
	        //��ѯ�ʽ���
	        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_45);
	    
	        QueryParser parser=new QueryParser(Version.LUCENE_45,"body",analyzer);
	        Query query=parser.parse(querystring);
	         
	        //��ǰ��Hits�Ѿ�����ʹ�á�ֱ��ʹ��TopDocs���ɡ�
	        Filter filter = null;
	        TopDocs topDocs = searcher.search(query, filter, 10);//���һ�����������Ʋ�ѯ�������Ŀ��
	         
	        System.out.println("�ܹ��С�" + topDocs.totalHits + "����ƥ����");
	          
	        //��ʾ���
	        for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
	            //�ĵ��ڲ���
	            int index = scoreDoc.doc; 
	            //���ݱ��ȡ����Ӧ���ĵ�
	            Document doc =searcher.doc(index);
	            System.out.println("------------"+index+1+"----------------");
	            System.out.println("path = " + doc.get("path"));
	       //     System.out.println("content = " + doc.get("body"));
	            }
	        ireader.close();
	    }
	         
	 
	 
	  

	  //������
    public static void main(String[] args) throws IOException, ParseException{
         
    	//  createIndex(); //�þ����ڴ�����������һ������ʱ��"//"ȥ�����Ժ�ͼ���ע�ͣ���Ȼ�������ظ�������
        //������
        String querystring="mysql";
     
        System.out.println("���ļ�����Ϊ����"+querystring+"��");
       
        TestQuery(querystring);
    }
 
}
