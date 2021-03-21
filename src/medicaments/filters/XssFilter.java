package medicaments.filters;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class XssFilter
 * 
 * Filtre non utilise, permettant de filtrer les parametres envoyes au serveur pour supprimer des potentielles attaques XSS
 * 
 *@author Raphael VIGNON 
 * 
 */
//@WebFilter(filterName="XssFilter", urlPatterns= {"/*"})
public class XssFilter implements Filter {


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new XssRequestWrapper((HttpServletRequest)request), response);
	}

	/**
	 * Classe permettant d'effectuer la suppression des balises 
	 * 
	 * @author Raphael VIGNON
	 *
	 */
	private static class XssRequestWrapper extends HttpServletRequestWrapper{

		public XssRequestWrapper(HttpServletRequest request) {
			super(request);

		}

		/**
		 * Liste des expression regulieres correspondant a des balises, ou a de potentiels parametres non desires
		 */
		private static final Pattern[] XSS_PATTERNS= {
				Pattern.compile("<.*>"),
				Pattern.compile("&.*;"),
				Pattern.compile("%[0-9a-fA-F]*")
		};

		/**
		 * Methode permettant d'effectuer la suppression des expression regulieres sur les parametres
		 */
		@Override
		public String[] getParameterValues(String parameterName) {
			String[] values = super.getParameterValues(parameterName);
			if(values==null)
				return null;
			for(String s : values) {
				s=removeTags(s);
			}
			return values;
		}

		/**
		 * Methode permettant d'effectuer la suppression des expression regulieres sur un parametre
		 */
		@Override
		public String getParameter(String parameter) {
			return removeTags(super.getParameter(parameter));
		}

		/**
		 * Methode permettant d'effectuer la suppression des expression regulieres sur le Header
		 * 
		 */
		@Override
		public String getHeader(String name) {
			return removeTags(super.getHeader(name));
		}

		/**
		 * Methode permettant de supprimer les occurences des expression regulieres dans une chaine de charactere
		 * @param value La chaine de charactere dont on veut enlever les expression regulieres
		 * @return La chaine de charactere sans les expressions regulieres
		 */
		private String removeTags(String value) {
			if(value!=null) {
				value=value.replaceAll("\0", "");

				for(Pattern pattern : XSS_PATTERNS) {
					value=pattern.matcher(value).replaceAll("");
				}


				//Si < et > sont impaires
				value = value.replaceAll("<","");
				value = value.replaceAll(">","");
			}
			return value;
		}
	}
}
