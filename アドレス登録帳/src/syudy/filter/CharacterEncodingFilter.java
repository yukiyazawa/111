package syudy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    public CharacterEncodingFilter() {
           }

	public void destroy() {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
request.setCharacterEncoding("utf-8");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	
	public void  init (FilterConfig fcoConfig)throws ServletException{

	}
}
