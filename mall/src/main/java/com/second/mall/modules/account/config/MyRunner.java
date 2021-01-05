package com.second.mall.modules.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author yqs
 * @Comments   * 配置类：配置默认打开自定义的浏览器
 *             * 启动服务打开浏览器
 * @Date 2020/12/30 19:06
 **/
@Configuration
public class MyRunner implements CommandLineRunner {
    @Value("${myurl}")
    private String myurl;

    @Value("${googleexcute}")
    private String googleExcutePath;

    @Value("${openurl}")
    private boolean isOpen;

    @Value("${server.port}")
    private String port;

    @Override
    public void run(String... args) throws Exception {
        if(isOpen){
            //跳转到控制层的方法中
            String cmd = googleExcutePath +" http://"+ myurl+":"+port+"/account/userLogin";
            Runtime run = Runtime.getRuntime();
            try{
                run.exec(cmd);
                System.err.println("启动浏览器程序成功");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
