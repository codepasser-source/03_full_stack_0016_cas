package matt.damon.cas.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import matt.damon.cas.env.RequireEnvironment;
import matt.damon.cas.vo.UserInfo;

@Path("/env")
public class EnvrionmentService {

	@GET
	@Path("/userInfo")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public UserInfo getUserInfo() {
		RequireEnvironment env = RequireEnvironment.getEnv();
		UserInfo userInfo = new UserInfo();
		userInfo.setId(env.getUserID());
		userInfo.setUserName(env.getUserName());
		return userInfo;
	}
}
