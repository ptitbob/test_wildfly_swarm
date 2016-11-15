package org.shipstone.sandbox.wildfly.test01.ws.rs;

import org.shipstone.sandbox.wildfly.test01.bean.WebUser;
import org.shipstone.sandbox.wildfly.test01.services.WebUserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;

/**
 * @author François Robert
 */
@Path("users")
public class WebUserResources {

  @Inject
  private WebUserService webUserService;

  @GET
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public List<WebUser> getAll() {
    return webUserService.getAll();
  }

  @GET
  @Path("{id: [(0-9)*]}")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public WebUser getWebUserById(@PathParam("id") Long id) {
    return webUserService.getById(id);
  }

  @POST
  @Consumes(APPLICATION_FORM_URLENCODED)
  @Produces({WILDCARD, APPLICATION_JSON, APPLICATION_XML})
  public Response createWebUser(
      @Context HttpHeaders httpHeaders,
      @FormParam("login") String login,
      @FormParam("firstname") String firstname,
      @FormParam("lastname") String lastname
  ) {
    WebUser webUser = webUserService.create(login, firstname, lastname);
    return Response.created(UriBuilder.fromResource(getClass()).path(String.valueOf(webUser.getId())).build())
        .entity((httpHeaders.getAcceptableMediaTypes().contains(WILDCARD_TYPE) ? null : webUser))
        .build();
  }

}
