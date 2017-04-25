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
		System.out.println("----------设置值操作--------");
		jedis.set("twj", "tangwenjiang");
		System.out.println(jedis.get("twj"));
		System.out.println("----------拼接字符串值操作--------");
		jedis.append("twj", "河北大学");//拼接字符串
		System.out.println(jedis.get("twj"));
		System.out.println("----------多键值操作--------");
		jedis.mset("name","tangwenjiang","age","23","school","河北大学");
		System.out.println("name="+jedis.get("name")+",age="+jedis.get("age")+",school="+jedis.get("school"));
		System.out.println("--------Map操作----------------");
		Map<String, String>params=new HashMap<>();
		params.put("name", "tangwenjiang");
		params.put("age", "23");
		params.put("school", "河北大学");
		params.put("company", "北京爱乐游文化发展有限公司");
		params.put("job", "攻城狮");
		jedis.hmset("user",params);
		System.out.println("------------取单个值---------");
		System.out.println(jedis.hget("user", "job"));//取出user中的某一个值
		System.out.println("------------取单多个值---------");
		List<String> datalist=jedis.hmget("user", "name","age","school","company");//取出user中的某些值
		for(String value:datalist){
			System.out.println(value);
		}
		System.out.println("----------删除某些值job,和age---------");
		jedis.hdel("user","job","age");
		System.out.println(jedis.hget("user", "job")); 
		System.out.println(jedis.hget("user", "age"));
		System.out.println("------------利用迭代器操作-------------");
		Iterator<String> iterator=jedis.hkeys("user").iterator();
		while (iterator.hasNext()) {
			String key =iterator.next();
			System.out.println(key+":"+jedis.hmget("user",key));	
		}
		System.out.println("-------------操作List---------------------");
		jedis.del("list");
		jedis.lpush("list", "tangwenjiang");
		jedis.lpush("list", "lishuoning");
		jedis.lpush("list","河北大学");
		//第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  (后push，先出来)
		System.out.println(jedis.lrange("list", 1, -1));
		jedis.rpush("list", "tangwenjiang1");
		jedis.rpush("list", "lishuoning1");
		jedis.rpush("list","河北大学1");
		jedis.lpush("list", "tangwenjiang3");
		jedis.lpush("list", "lishuoning3");
		jedis.lpush("list","河北大学3");
		jedis.rpush("list", "tangwenjiang4");
		jedis.rpush("list", "lishuoning4");
		jedis.rpush("list","河北大学4");
		System.out.println(jedis.lrange("list", 0, -1));
		/**
		 * 操作list得出的结论：
		 * 1，后push进去先出来
		 * 2，lpush后push永远在队首
		 * 3，rpush后push永远在队尾
		 */
		System.out.println("-------------操作Set--------");
		jedis.sadd("phone", "苹果");
		jedis.sadd("phone", "三星");
		jedis.sadd("phone", "华为");
		jedis.sadd("phone", "vivo");
		jedis.sadd("phone", "oppo");
		//移除  三星
		jedis.srem("phone","三星");  
		System.out.println(jedis.smembers("phone"));//获取所有加入的value  
		System.out.println(jedis.sismember("phone", "三星"));//判断 三星 是否是phone集合的元素  
		System.out.println(jedis.srandmember("phone"));  
		System.out.println(jedis.scard("phone"));//返回集合的元素个数  
		System.out.println("--------------排序操作--------");
		jedis.del("sort");
		jedis.lpush("sort", "2");
		jedis.lpush("sort", "4");
		jedis.lpush("sort", "1");
		jedis.lpush("sort", "3");
		jedis.lpush("sort", "5");
		System.out.println(jedis.lrange("sort",0,-1));//[5, 3, 1, 4, 2]
		System.out.println(jedis.sort("sort")); //[1, 2, 3, 4, 5]  //输入排序后结果  
		System.out.println(jedis.lrange("sort",0,-1));// [5, 3, 1, 4, 2]并没有改变存储结构
        System.out.println("--------------保存实体对象--------------");
        Girls girls=new Girls();
        girls.setId(1);
        girls.setName("小宁宁");
        girls.setAge(22);
		jedis.set("girls".getBytes(), SerializeUtil.serialize(girls));
		System.out.println(SerializeUtil.unserialize(jedis.get("girls".getBytes())));
		jedis.close();
	}
}
