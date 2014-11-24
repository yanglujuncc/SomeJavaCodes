package REDIS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtil {

	static String defaultHost = "localhost";
	static int defaultPort = 6379;
	static int defaultTimeout = 20000;

	static JedisPool commonPool;

	public static void init(String host, int port, int timeout) {

		defaultHost = host;
		defaultPort = port;
		defaultTimeout = timeout;

		commonPool = getPool(defaultHost, defaultPort, defaultTimeout);
	}

	public static void close() {
		if (commonPool != null)
			commonPool.destroy();
	}

	public static JedisPool getPool() {
		return getPool(defaultHost, defaultPort, defaultTimeout);
	}

	public static Jedis getJedis() {

		if (commonPool == null) {
			commonPool = getPool(defaultHost, defaultPort, defaultTimeout);
		}
		return commonPool.getResource();
	}

	public static void returnJedis(Jedis jedis) {

		commonPool.returnResource(jedis);

	}

	public static JedisPool getPool(String host, int port, int timeout) {

		JedisPoolConfig config = new JedisPoolConfig();
		// ����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
		// �����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
		config.setMaxActive(500);
		// ����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
		config.setMaxIdle(5);
		// ��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
		config.setMaxWait(1000 * 100);
		// ��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
		config.setTestOnBorrow(true);
		// JedisPool pool = new JedisPool(config, "bje2b9.space.163.org", 6379);
		JedisPool pool = new JedisPool(config, host, port, timeout);

		return pool;
	}

	public static ShardedJedisPool getShardedPool(List<JedisShardInfo> shards) {

		JedisPoolConfig config = new JedisPoolConfig();
		// ����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
		// �����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
		config.setMaxActive(500);
		// ����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
		config.setMaxIdle(5);
		// ��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
		config.setMaxWait(1000 * 100);
		// ��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
		config.setTestOnBorrow(true);
		// JedisPool pool = new JedisPool(config, "bje2b9.space.163.org", 6379);

		ShardedJedisPool shardedJedisPool = new ShardedJedisPool(config, shards);

		return shardedJedisPool;
	}

	private static String readFileText(String redisInstanceFilePath, String encoding) throws Exception {

		FileInputStream fileInputStream = new FileInputStream(redisInstanceFilePath);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
		BufferedReader bReader = new BufferedReader(inputStreamReader);

		String line = null;
		StringBuilder aStringBuilder = new StringBuilder();
		while ((line = bReader.readLine()) != null) {
			aStringBuilder.append(line + "\n");
		}
		bReader.close();
		
		
		return aStringBuilder.toString();
	}

	public static ShardedJedisPool getShardedPool(String redisInstanceFilePath) throws Exception {

		String xmlText=readFileText( redisInstanceFilePath,  "utf-8");		
	
		Document doc = Jsoup.parse(xmlText, "", Parser.xmlParser());	

		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
	
		
		
		for (Element e : doc.select("instance")) {
			
			//System.out.println(e);
			
			String host=e.attr("host");
			String port=e.attr("port");
			String name=e.attr("name");
			String timeout=e.attr("timeout");
			
			System.out.println(" name:"+name+" host:"+host+" port:"+port+" name:"+name+" timeout:"+timeout);
		
			JedisShardInfo jedisShardInfo=new JedisShardInfo(host, Integer.parseInt(port),name);
			jedisShardInfo.setTimeout(Integer.parseInt(timeout));
			
		
			shards.add(jedisShardInfo);

			//jedisShardInfo.
		}
		
		return  getShardedPool(shards);
	
	}

	public static void main(String[] args) throws Exception {

		String host = "bje2b9.space.163.org";
		int port = 7001;
		int timeout = 20000;
		JedisPool jedisPool = getPool(host, port, timeout);

	////	List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
	//	shards.add(new JedisShardInfo(host, port, "share_1"));

	//	ShardedJedisPool shardedJedisPool = getShardedPool(shards);

		String redisInstanceFilePath="conf/redis-instance.xml";
		ShardedJedisPool shardedJedisPool=getShardedPool( redisInstanceFilePath);
		
		
	}

}
