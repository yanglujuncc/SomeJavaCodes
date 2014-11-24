package REDIS;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class testJedisPool {

	 /**
     * ����redis���ӳ�
     * 
     * @param ip
     * @param port
     * @return JedisPool
     */
	//static JedisPool pool;
	
    public static JedisPool getPool() {
    
            JedisPoolConfig config = new JedisPoolConfig();
            //����һ��pool�ɷ�����ٸ�jedisʵ����ͨ��pool.getResource()����ȡ��
            //�����ֵΪ-1�����ʾ�����ƣ����pool�Ѿ�������maxActive��jedisʵ�������ʱpool��״̬Ϊexhausted(�ľ�)��
            config.setMaxActive(500);
            //����һ��pool����ж��ٸ�״̬Ϊidle(���е�)��jedisʵ����
            config.setMaxIdle(5);
            //��ʾ��borrow(����)һ��jedisʵ��ʱ�����ĵȴ�ʱ�䣬��������ȴ�ʱ�䣬��ֱ���׳�JedisConnectionException��
            config.setMaxWait(1000 * 100);
            //��borrowһ��jedisʵ��ʱ���Ƿ���ǰ����validate���������Ϊtrue����õ���jedisʵ�����ǿ��õģ�
            config.setTestOnBorrow(true);
            JedisPool   pool = new JedisPool(config, "bje2b9.space.163.org", 6379);
       
            return pool;
    }
    
    /**
     * ���������ӳ�
     * 
     * @param pool 
     * @param redis
     */
    public static void returnResource(JedisPool pool, Jedis redis) {
        if (redis != null) {
            pool.returnResource(redis);
        }
    }
    
    /**
     * ��ȡ����
     * 
     * @param key
     * @return
     */
    public static String get(String key){
        String value = null;
        
        JedisPool pool = null;
        Jedis jedis = null;
        try {
            pool = getPool();
            jedis = pool.getResource();
            value = jedis.get(key);
        } catch (Exception e) {
            //�ͷ�redis����
            pool.returnBrokenResource(jedis);
            e.printStackTrace();
        } finally {
            //���������ӳ�
            returnResource(pool, jedis);
        }
        
        return value;
    }
    
    
    public static void main(String[] args){
    	JedisPool pool=getPool() ;
    	Jedis jedis=pool.getResource();
    	pool.returnResource(jedis);
    }
    
    
    
}
