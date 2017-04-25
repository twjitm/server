package com.test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.management.MXBean;

import redis.clients.jedis.Jedis;

public class RedisTest {
	public static void main(String[] args) {
		startup();
	}

	public static void startup(){
		Jedis jedis=new Jedis("192.168.0.50",6379);
		jedis.auth("123456");
		System.out.println("----------����ֵ����--------");
		jedis.set("twj", "tangwenjiang");
		System.out.println(jedis.get("twj"));
		System.out.println("----------ƴ���ַ���ֵ����--------");
		jedis.append("twj", "�ӱ���ѧ");//ƴ���ַ���
		System.out.println(jedis.get("twj"));
		System.out.println("----------���ֵ����--------");
		jedis.mset("name","tangwenjiang","age","23","school","�ӱ���ѧ");
		System.out.println("name="+jedis.get("name")+",age="+jedis.get("age")+",school="+jedis.get("school"));
		System.out.println("--------Map����----------------");
		Map<String, String>params=new HashMap<>();
		params.put("name", "tangwenjiang");
		params.put("age", "23");
		params.put("school", "�ӱ���ѧ");
		params.put("company", "�����������Ļ���չ���޹�˾");
		params.put("job", "����ʨ");
		jedis.hmset("user",params);
		System.out.println("------------ȡ����ֵ---------");
		System.out.println(jedis.hget("user", "job"));//ȡ��user�е�ĳһ��ֵ
		System.out.println("------------ȡ�����ֵ---------");
		List<String> datalist=jedis.hmget("user", "name","age","school","company");//ȡ��user�е�ĳЩֵ
		for(String value:datalist){
			System.out.println(value);
		}
		System.out.println("----------ɾ��ĳЩֵjob,��age---------");
		jedis.hdel("user","job","age");
		System.out.println(jedis.hget("user", "job")); 
		System.out.println(jedis.hget("user", "age"));
		System.out.println("------------���õ���������-------------");
		Iterator<String> iterator=jedis.hkeys("user").iterator();
		while (iterator.hasNext()) {
			String key =iterator.next();
			System.out.println(key+":"+jedis.hmget("user",key));	
		}
		System.out.println("-------------����List---------------------");
		jedis.del("list");
		jedis.lpush("list", "tangwenjiang");
		jedis.lpush("list", "lishuoning");
		jedis.lpush("list","�ӱ���ѧ");
		//��һ����key���ڶ�������ʼλ�ã��������ǽ���λ�ã�jedis.llen��ȡ���� -1��ʾȡ������  (��push���ȳ���)
		System.out.println(jedis.lrange("list", 1, -1));
		jedis.rpush("list", "tangwenjiang1");
		jedis.rpush("list", "lishuoning1");
		jedis.rpush("list","�ӱ���ѧ1");
		jedis.lpush("list", "tangwenjiang3");
		jedis.lpush("list", "lishuoning3");
		jedis.lpush("list","�ӱ���ѧ3");
		jedis.rpush("list", "tangwenjiang4");
		jedis.rpush("list", "lishuoning4");
		jedis.rpush("list","�ӱ���ѧ4");
		System.out.println(jedis.lrange("list", 0, -1));
		/**
		 * ����list�ó��Ľ��ۣ�
		 * 1����push��ȥ�ȳ���
		 * 2��lpush��push��Զ�ڶ���
		 * 3��rpush��push��Զ�ڶ�β
		 */
		System.out.println("-------------����Set--------");
		jedis.sadd("phone", "ƻ��");
		jedis.sadd("phone", "����");
		jedis.sadd("phone", "��Ϊ");
		jedis.sadd("phone", "vivo");
		jedis.sadd("phone", "oppo");
		//�Ƴ�  ����
		jedis.srem("phone","����");  
		System.out.println(jedis.smembers("phone"));//��ȡ���м����value  
		System.out.println(jedis.sismember("phone", "����"));//�ж� ���� �Ƿ���phone���ϵ�Ԫ��  
		System.out.println(jedis.srandmember("phone"));  
		System.out.println(jedis.scard("phone"));//���ؼ��ϵ�Ԫ�ظ���  
		System.out.println("--------------�������--------");
		jedis.del("sort");
		jedis.lpush("sort", "2");
		jedis.lpush("sort", "4");
		jedis.lpush("sort", "1");
		jedis.lpush("sort", "3");
		jedis.lpush("sort", "5");
		System.out.println(jedis.lrange("sort",0,-1));//[5, 3, 1, 4, 2]
		System.out.println(jedis.sort("sort")); //[1, 2, 3, 4, 5]  //�����������  
		System.out.println(jedis.lrange("sort",0,-1));// [5, 3, 1, 4, 2]��û�иı�洢�ṹ
        System.out.println("--------------����ʵ�����--------------");
        Girls girls=new Girls();
        girls.setId(1);
        girls.setName("С����");
        girls.setAge(22);
		jedis.set("girls".getBytes(), SerializeUtil.serialize(girls));
		System.out.println(SerializeUtil.unserialize(jedis.get("girls".getBytes())));
		jedis.close();
	}
}
