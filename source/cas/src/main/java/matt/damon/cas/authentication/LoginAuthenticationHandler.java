package matt.damon.cas.authentication;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.constraints.NotNull;

public class LoginAuthenticationHandler extends
		AbstractJdbcUsernamePasswordAuthenticationHandler implements
		InitializingBean {

	private static final String SQL_PREFIX = "Select count('x') from ";

	@NotNull
	private String fieldUser;

	@NotNull
	private String fieldPassword;

	@NotNull
	private String tableUsers;

	private String sql;

	protected final boolean authenticateUsernamePasswordInternal(
			final UsernamePasswordCredentials credentials)
			throws AuthenticationException {
		final String transformedUsername = getPrincipalNameTransformer()
				.transform(credentials.getUsername());
		final String encyptedPassword = getPasswordEncoder().encode(
				credentials.getPassword());

		System.out.println(encyptedPassword);

		@SuppressWarnings("deprecation")
		final int count = getJdbcTemplate().queryForInt(this.sql,
				transformedUsername, encyptedPassword);

		return count > 0;
	}

	public void afterPropertiesSet() throws Exception {
		this.sql = SQL_PREFIX + this.tableUsers + " Where " + this.fieldUser
				+ " = ? And " + this.fieldPassword + " = ?";
	}

	/**
	 * @param fieldPassword
	 *            The fieldPassword to set.
	 */
	public final void setFieldPassword(final String fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	/**
	 * @param fieldUser
	 *            The fieldUser to set.
	 */
	public final void setFieldUser(final String fieldUser) {
		this.fieldUser = fieldUser;
	}

	/**
	 * @param tableUsers
	 *            The tableUsers to set.
	 */
	public final void setTableUsers(final String tableUsers) {
		this.tableUsers = tableUsers;
	}
}
