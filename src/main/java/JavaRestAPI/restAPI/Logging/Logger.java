package JavaRestAPI.restAPI.Logging;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logger implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getHeader("X-Forwarded-For");
        ip = ip!=null && ip.contains(",")
                ? ip.split(",")[0]
                : ip;
        MemoryLogUtil.Log(ip);
        if (MemoryLogUtil.IsOverused(ip)){
            response.getWriter().write("Daily request limit met. Please contact the maintainer or lower request volume.");
            response.setStatus(429);
            return false;
        }
        return true;
    }
}
