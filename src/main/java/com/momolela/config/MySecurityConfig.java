package com.momolela.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("zhangsan").password("123").authorities("VIP1", "VIP2").build());
//        manager.createUser(User.withUsername("lisi").password("456").authorities("VIP2", "VIP3").build());
//        return manager;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        // 会话控制, 默认IF_REQUIRED，如果需要就创建一个Session（默认）登录时
        // always 如果没有session存在就创建一个
        // never SpringSecurity 将不会创建Session，但是如果应用中其他地方创建了Session，那么Spring Security将会使用它。
        // stateless SpringSecurity将绝对不会创建Session，也不使用Session，就可以以使用token了
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

        // 会话超时，跳转页面控制
        http.sessionManagement().maximumSessions(1).expiredUrl("/expire?error=EXPIRED_SESSION");

        // sessionid无效，跳转页面控制
        http.sessionManagement().invalidSessionUrl("/invalid?error=INVALID_SESSION");

        // 定制请求的授权规则，web授权
        // 授权类型1：hasRole()，基于角色的访问控制
        // 授权类型2：hasAuthority()，基于资源的访问控制
        http.csrf().disable() // 关闭csrf
                .authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        // 开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/userlogin");
        // 1、/login来到登陆页
        // 2、重定向到/login?error表示登陆失败
        // 3、更多详细规定
        // 4、默认post形式的 /login代表处理登陆
        // 5、一但定制loginPage；那么 loginPage的post请求就是登陆


        // 开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        // 1、访问 /logout 表示用户注销，清空session
        // 2、注销成功会返回 /login?logout 页面；

        // 开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        // 登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        // 点击注销会删除cookie

    }

    // 定义认证规则，拦截规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder()) // 密码需要加密。默认BCrypt加密格式
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP3");

    }
}
