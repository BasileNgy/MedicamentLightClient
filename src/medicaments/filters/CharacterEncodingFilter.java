package medicaments.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 * Filtre permettant de forcer l'encodage des pages en UTF-8 sur tout le site
 * 
 * @author Raphael VIGNON
 */
@WebFilter(filterName="/CharacterEncodingFilter", urlPatterns= {"/*"})
public class CharacterEncodingFilter implements Filter {


	/**
	 * Methode permettant de forcer l'encodage en UTF-8
	 */
	 @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	        request.setCharacterEncoding("UTF-8");
	        chain.doFilter(request, response);
	    }


}
