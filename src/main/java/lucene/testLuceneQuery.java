package lucene;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;

public class testLuceneQuery {


/**
	 * ��ѯ����
	 * @throws IOException 
	 * @throws CorruptIndexException 
	 * @throws ParseException 
	 * @throws InvalidTokenOffsetsException 
	 */
	public  ScoreDoc[] Search(String searchString,  IndexSearcher isearcher) throws Exception{
		
		//����һ:
	    //String[] fields = {"fileName","fieldid","date"};
        //MultiFieldQueryParser  ������ѯ����ֶ� queryparser 
        //QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40 ,fields, this.indexSettings.getAnalyzer());
		//Query query = parser.parse(searchString);
	    
		//������: ������ѯ 
        //Term t = new Term("fileName", searchString);
        //Query query = new TermQuery(t);
	    //String[] searchFields = {"Infotitle","Linkaddr","Skuname","Skudetaile"};
        //�������Ͳ�ѯ��BooleanQuery��
	    //BooleanQuery query = new BooleanQuery();
	    BooleanQuery query = new BooleanQuery();
	    //�����Ĺؼ���
	    /*QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_40 ,searchFields,this.indexSettings.getAnalyzer());
        Query q1 = parser.parse("ƻ��");
        query.add(q1,BooleanClause.Occur.MUST);*/
       //�ĸ�ģ�����µ�
        Term t2 = new Term("fileName","java����");
        Query q2 = new TermQuery(t2);
       // query.add(q2,BooleanClause.Occur.SHOULD);
        Term t2s = new Term("fileName","java����");
        Query q2s = new TermQuery(t2s);
       // query.add(q2s,BooleanClause.Occur.SHOULD);
        
        
        
        
       //��������
        /*Term t3 = new Term("Infotype","2");
        Query q3 = new TermQuery(t3);
        query.add(q3,BooleanClause.Occur.MUST);*/
       /* 
      //�������
        Term t4 = new Term("Areacode","");
        PrefixQuery q4 = new PrefixQuery(t4);
       query.add(q4,BooleanClause.Occur.MUST);
        
      //��Ʒ���� 
        Term t5 = new Term("Infocateg","");
        PrefixQuery q5 = new PrefixQuery(t5);
        query.add(q5,BooleanClause.Occur.MUST);*/
	   /* Term t1 = new Term("fileName", "*");
        TermQuery q1 = new TermQuery(t1);
	    
	    Term t2 = new Term("date", "");
	    PrefixQuery q2 = new PrefixQuery(t2);*/
	    
	   // Term t3 = new Term("fileName", "java");
        //PrefixQuery q3 = new PrefixQuery(t3);
        
		
		//query.add(q1,BooleanClause.Occur.MUST);
        //query.add(q3,BooleanClause.Occur.MUST);
        //query.add(q2,BooleanClause.Occur.MUST);
		
        //��Χ������TermRangeQuery�� ------------------------
        //����ѯ��Χ�ϴ��������������ĵ�����Ҳ�ܴ�ʱЧ���Ǻܵ͵�
	    //Date date = new Date();
        //SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
        //String times = time.format(date);
        //��Χ������TermRangeQuery��
        //����ѯ��Χ�ϴ��������������ĵ�����Ҳ�ܴ�ʱЧ���Ǻܵ͵�
        //BytesRef lowerTerm = new BytesRef("19000101010100");
        //BytesRef upperTerm = new BytesRef(times);
        //System.out.println("19000101010100");
        //System.out.println(times);
        //TermRangeQuery query = new  TermRangeQuery("Pubtime", lowerTerm, upperTerm, true, true);
        //ģ����ѯ
        //�����������캯�� 
        //FuzzyQuery fd  = new FuzzyQuery(term, maxEdits)
        //maxEdits ������ƶȣ�Ĭ��Ϊ0.5������ 3.0��ģ�
        //prefixLength ��ʾ����ģ��ƥ���ʱ��Ҫ�ж��ٸ�ǰ׺��ĸ������ȫƥ��
        //�����1����ʾ���д���ֻ�е�һ����ĸ������ؼ������ʱ���Żᱻ���뱸ѡ������
        //FuzzyQuery fd  = new FuzzyQuery(t,int 1,int prefixLength)
        //FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("fileName", "java"), 0); // ����ԽСԽ��ȷ0-2
		
        //ǰ׺��ѯ  ��ǰ��ļ�����
        //PrefixQuery query2 = new PrefixQuery(term);
        
        //�����ѯ �Ȳ�ֳɵ��� ���ķ��� ʹ�� �������Ͳ�ѯ��BooleanQuery����װ���Ӿ�
        //�Ӿ���ϵ��Ϊ MUSTȡ�佻��
        //setSlop��int�� �����¶ȣ�Ĭ��ֵΪ0�������ϸ�ƥ�������ĵ���Ϊ�����
        //����ΪsetSlop��1��,��ʾ�м���Բ����޹������ĸ��� 1�����м���Բ����޹��ָ���Ϊ1��
        //PhraseQuery query2 = new PhraseQuery();
        //query2.add(term);
        //query2.setSlop(1);
        
        //������ѯ�����Ҹо�����,Ҳ����һ���һ��õ����ģ�
        //MultiPhraseQuery query2 = new MultiPhraseQuery();
        
        //ͨ�������
        //Term term = new Term("filed","?o*");
        //WildcardQuery query2 = new WildcardQuery(term); 
        
        //�������
        //SpanTermQuery query2 = new SpanTermQuery(term);
        
        //Sort sort = new Sort();
        //SortField sortField = new SortField("fileid",FieldCache.DEFAULT_INT_PARSER, false); 
        //sort.setSort(sortField);
        //ScoreDoc[] docs = this.indexSearcher.search(query,100,sort).scoreDocs;
        
        ScoreDoc[] docs = isearcher.search(query,100).scoreDocs;
		System.out.println("һ����:"+docs.length+"����¼");
		//List result = luceneResultCollector.collect(docs, this.indexSearcher, query ,this.indexSearcher,this.indexSettings.getAnalyzer());
		return docs;
	}
}
