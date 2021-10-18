package exp.b8082.app8082.intercepter;

import exp.b8082.app8082.Utils.HttpUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class Sessionintercepter extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在user.com中是否有局部session对象
        HttpSession session=request.getSession(false);//没有不创建
        if(session!=null&&session.getAttribute("login").equals("login")){
            return true;
        }

        //没有session认证信息，也没有其他系统已经登录过的token信息
        String token=request.getParameter("token");
        System.out.println(token);
        //System.out.println(token);
        if(token!=null){
            String reqUrl="localhost:8084/checkToken";
            //String content="token="+token;
            Map<String,String> params=new HashMap<>();
            params.put("token",token);
            reqUrl="http://localhost:8084/checkToken?"+"token="+token;
            String result=HttpUtil.get(reqUrl);
            if(result.length()>=7){
                result=result.substring(0,7);
            }
            if("correct".equals(result)){
                request.getSession().setAttribute("login","login");
                return true;
            }
        }
        response.sendRedirect("http://localhost:8084/preLogin?url=localhost:8082/sysB/wel");
        return false;
    }
}
