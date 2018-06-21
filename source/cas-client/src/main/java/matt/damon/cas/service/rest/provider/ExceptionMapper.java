package matt.damon.cas.service.rest.provider;

import java.util.UUID;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import matt.damon.cas.vo.ErrorMessage;

@Provider
public class ExceptionMapper implements
		javax.ws.rs.ext.ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {

		ErrorMessage message = new ErrorMessage();
		message.setCode(UUID.randomUUID().toString());
		message.setMessage(exception.getMessage());

		ResponseBuilder responseBuilder = Response.serverError();
		responseBuilder.type(MediaType.APPLICATION_JSON + ";charset=utf-8");
		responseBuilder.entity(message);
		return responseBuilder.build();
	}

}
