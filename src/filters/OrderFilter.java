package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.commands.CommandConstants;
import model.entities.Order;
import model.entities.Tour;
import model.entities.User;
import model.services.OrderService;

/**
 * Servlet Filter implementation class OrderFilter. This filter checks travel's
 * status in order list for current user and sets appropriate data about this
 * one.
 * 
 * @author Yevhen Hryshchenko
 * @version 31 Jule 2016
 */
@WebFilter("/OrderFilter")
public class OrderFilter implements Filter {

    /**
     * Default constructor. 
     */
    public OrderFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Order order;
		HttpSession session = ((HttpServletRequest) request).getSession(true);
		OrderService orderService = OrderService.getInstance();
		Tour travel = (Tour)session.getAttribute(CommandConstants.TRAVEL);
		User user = (User)session.getAttribute(CommandConstants.USER);
		
		if ((order = orderService.getOrder(user.getId(), travel.getId())) != null) {
			session.setAttribute(CommandConstants.ORDER, order);
		}
			
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
