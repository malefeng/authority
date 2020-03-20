package com.innove.authority.config.shiro;

import com.innove.authority.util.StringUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class WechatAuthorizationFilter extends AuthorizationFilter {

    public static final String SINGNATURE_TOKEN = "加密token";

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //这里只有返回false才会执行onAccessDenied方法,因为
        // return super.isAccessAllowed(request, response, mappedValue);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        Subject subject = getSubject(servletRequest, servletResponse);
        String servletPath = ((HttpServletRequest) servletRequest).getServletPath();
        String queryString = ((HttpServletRequest) servletRequest).getQueryString();
        if(StringUtil.isNotBlank(queryString)){
            servletPath = servletPath.concat("?").concat(queryString);
        }
        boolean permitted = subject.isPermitted(servletPath);
        if(!permitted){
            String unauthorizedUrl = this.getUnauthorizedUrl();
            if (StringUtils.hasText(unauthorizedUrl)) {
                WebUtils.issueRedirect(servletRequest, servletResponse, unauthorizedUrl);
            } else {
                WebUtils.toHttp(servletResponse).sendError(401);
            }
        }
        return true;
    }

}
