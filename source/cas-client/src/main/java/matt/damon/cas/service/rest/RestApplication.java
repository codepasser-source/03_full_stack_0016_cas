package matt.damon.cas.service.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import matt.damon.cas.service.EnvrionmentService;
import matt.damon.cas.service.rest.provider.ExceptionMapper;

public class RestApplication extends Application {

	HashSet<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new ExceptionMapper());
		singletons.add(new EnvrionmentService());
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}
