package com.sango.warehouse.controller;

import com.google.code.kaptcha.Producer;
import com.sango.warehouse.entity.LoginUser;
import com.sango.warehouse.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


@RestController
public class LoginController {

    //注入DefaultKaptcha的bean对象--生成验证码图片
    @Resource(name = "captchaProducer")
    private Producer producer;

    //注入redis模板
    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response) {
        ServletOutputStream out = null;
        try {
            //生成验证码图片文件
            String text = producer.createText();
            //使用验证码文本生成验证码文图片 -- BufferedImage对象就代表生成的验证码图片，在内存中
            BufferedImage image = producer.createImage(text);
            //将验证码文本保存在redis -- 设置键的过期时间30分钟
            redisTemplate.opsForValue().set(text, "", 60 * 30, TimeUnit.SECONDS);
             /*
             将验证码图片响应给前端：
             */
            //设置响应正文Imge/jpeg
            response.setContentType("image/jpeg");
            //将验证码图片给前端
            out = response.getOutputStream();
            ImageIO.write(image, "jpg", out);//使用响应对象的字节输出流写入验证码图片，自然是给前端写入
            //刷新
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭字节流
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    登入的URL接口/login

    参数@RequestBody LoginUser loginUser --表示接受并封装前端传递的登录的用户信息的Json数据；
    返回值Result对象 -- 表示向前端响应结果Result对象转的json串，包含响应状态码 成功失败响应 响应信息 响应数据
     */
    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser) {


    }
}


