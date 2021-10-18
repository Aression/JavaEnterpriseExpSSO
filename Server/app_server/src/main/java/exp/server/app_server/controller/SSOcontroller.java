package exp.server.app_server.controller;

import exp.server.app_server.domain.User;
import exp.server.app_server.repository.UserRepository;
import exp.server.app_server.utils.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin(origins = "*")
public class SSOcontroller {
    List<String> list=new ArrayList<>();
    Map<String,String> map=new HashMap<>();
    static HttpServletRequest request2=null;

    @RequestMapping(value = "/preLogin",method = RequestMethod.GET)
    public String PreLogin(String url, HttpServletRequest request, Model model){
        System.out.println(url);
        HttpSession session =(HttpSession)request.getSession(false);

        //发现认证中心没有全局的session对话
        if(session==null){
            model.addAttribute("url",url);
            System.out.println("没有session");
            return "login";
        }
        else{
            String token=(String) session.getAttribute("token");
            System.out.println("redirect:http://"+url+"?token="+token);
            System.out.println("有session");
            return "redirect:http://"+url+"?token="+token;
        }

    }

    @Autowired
    private UserRepository userRepository;
    static  HttpServletRequest request1=null;
    @RequestMapping(value = "/login")
    public String login(String url,String username,String password,HttpServletRequest request){
        User user = new User();
        user.setName(username);
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnorePaths("age","createTime");
        Example<User> example = Example.of(user, matcher);
        User findUser = userRepository.findOne(example).get();
        System.out.println("userName="+findUser.getName()+", userPassword="+findUser.getPassword());
        // 如果用户存在且密码正确,则允许登陆

        System.out.println(url);

        System.out.println(findUser.getName());
        System.out.println(username);
        
        System.out.println(findUser.getPassword());
        System.out.println(password);

        boolean judge = findUser.getName().equals(username)&&findUser.getPassword().equals(password);
        
        if(findUser.getName().equals(username)&&findUser.getPassword().equals(password)){
            System.out.println("登陆成功!");
            String token=null;
            Map<String,String> msg=new HashMap<>();
            msg.put("name",username);
            token= JWTUtils.getToken(msg);
            //HttpSession session=request.getSession();
            System.out.println("请求1"+request);
            request.getSession().setAttribute("token",token);
            request.getSession().setAttribute("test","hello world");
            list.add(token);
            map.put(request.toString(),token);
            return "redirect:http://"+url+"?token="+token;
        }
        else return "login";
    }

    @RequestMapping(value = "/checkToken")
    @ResponseBody
    public String checkToken(String token){
        System.out.println("-------------------");
        System.out.println(JWTUtils.verify(token));
        if(JWTUtils.verify(token)){
            return "correct";
        }
        return "incorrect";
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test(String url, HttpServletRequest request, Model model){
        System.out.println(url);
        HttpSession session=request.getSession(false);

        //发现认证中心没有全局的session对话
        if(session==null){
            return "没有session";
        }
        else{
            String token=(String) session.getAttribute("token");
            System.out.println("当前token:"+token);
            return "有session";
        }
    }

    @RequestMapping(value = "/b")
    @ResponseBody
    public String b(HttpServletRequest request){
        request1=request;
        return "test";
    }
    
    public static String bb(HttpServletRequest request){
        HttpSession session =(HttpSession)request.getSession(false);
        System.out.println("请求2"+request);

        System.out.println("存储1"+request.getSession().getAttribute("token"));
        System.out.println("存储2"+request.getSession().getAttribute("test"));
        return "test";
    }
}