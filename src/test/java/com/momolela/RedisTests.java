package com.momolela;

import com.momolela.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    @Autowired
    RedisTemplate redisTemplate; // 操作k-v都是对象的

    @Autowired
    RedisTemplate<Object, Object> customRedisTemplate; // 操作k-v都是对象的

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 操作k-v都是字符串的

    @Test
    public void testRedisForString() {
//        stringRedisTemplate.opsForValue();
//        stringRedisTemplate.opsForList();
//        stringRedisTemplate.opsForHash();
//        stringRedisTemplate.opsForSet();
//        stringRedisTemplate.opsForZSet();
        stringRedisTemplate.opsForValue().append("sunzjStr", "haha");
    }

    @Test
    public void testRedisForObj() {
        // 这里如果Person没有实现Serializable接口，会报错；
        // Person实现了Serializable接口后，会使用jdk序列化机制，将序列化后的数据存储到redis中
        redisTemplate.opsForValue().set("sunzjObj", new Person());
        // 但是我们一般情况下，还是希望存储json的对象数据到redis中；
        // 1、要么自己进行json转换然后存入redis；
        // 2、要么使用RedisTemplate自己的序列化机制；
        customRedisTemplate.opsForValue().set("sunzjNewObj", new Person());
    }
}
