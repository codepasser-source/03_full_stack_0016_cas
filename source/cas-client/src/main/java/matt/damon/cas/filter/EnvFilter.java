package matt.damon.cas.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import matt.damon.cas.env.RequireEnvironment;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;

public class EnvFilter implements Filter {

	// private static final Log logger = LogFactory.getLog(EnvFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		RequireEnvironment environment = (RequireEnvironment) session
				.getAttribute("environment");

		if (environment == null) {
			environment = new RequireEnvironment();
			Assertion assertion = AssertionHolder.getAssertion();
			AttributePrincipal principal = assertion.getPrincipal();
			environment.setUserID(principal.getName());
			environment.setUserName(principal.getName());

			Map<String, Object> attributes = principal.getAttributes();
			if (attributes != null) {
				System.out.println(attributes.get("userId"));
				System.out.println(attributes.get("userName"));
			}

			session.setAttribute("environment", environment);
		} else {
			// 更新
			environment = new RequireEnvironment(environment);
		}

		RequireEnvironment.setEnv(environment);
		// pass the request along the filter chain
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
