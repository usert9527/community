package cn.user9527.mycommunity;

import cn.user9527.mycommunity.model.Tar;
import cn.user9527.mycommunity.service.CommentService;
import cn.user9527.mycommunity.service.TarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MycommunityApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //都是字符串

    @Autowired
    RedisTemplate redisTemplate; //k-v都是对象

    @Autowired
    private TarService tarService;

    @Test
    public void contextLoads() {

        List<Tar> tars = tarService.selectTarType(1);

        redisTemplate.opsForValue().set("tar",tars);
//        stringRedisTemplate.opsForValue().append("msd", "123");
    }

}
