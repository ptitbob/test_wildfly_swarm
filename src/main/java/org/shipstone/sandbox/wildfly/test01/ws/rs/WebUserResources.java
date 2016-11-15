package org.shipstone.sandbox.wildfly.test01.ws.rs;

import org.shipstone.sandbox.wildfly.test01.bean.WebUser;
import org.shipstone.sandbox.wildfly.test01.services.WebUserService;

import javax.ejb.EJB;
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

  @EJB
  private WebUserService webUserService;

  /**
   * Renvoi la liste de tous les utilisateur
   * format par défaut (car en premier) : application/json
   * ex :
   *    - http :8080/api/users
   *    - http :8080/api/users accept:application/xml
   * @return liste
   */
  @GET
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public List<WebUser> getAll() {
    return webUserService.getAll();
  }

  /**
   * Renvoi un utilisateur selon son id
   * format par défaut (car en premier) : application/json
   * ex :
   *    - http :8080/api/users/1
   *    - http :8080/api/users/1 accept:application/xml
   * @param id identifiant de l'utilisateur (paramétré via un regex)
   * @return utilisateur
   */
  @GET
  @Path("{id: [(0-9)*]}")
  @Produces({APPLICATION_JSON, APPLICATION_XML})
  public WebUser getWebUserById(@PathParam("id") Long id) {
    return webUserService.getById(id);
  }

  /**
   * Créé un utilisateur via l'exposition d'un formulaire
   * L'URL d'accès GET est fourni dans le header 'location'
   * Si le format du contenu demandé est spécifié (application/json ou application/xml), alors l'utilisateur est renvoyé dans le body de la réponse
   * ex :
   *    - http -f :8080/api/users login='machin' firstname='truc' lastname='bidule'
   *    - demande de revoi en JSON : http -f :8080/api/users login='machin' firstname='truc' lastname='bidule' accept:application/json
   *    - demande de renvoi en XML : http -f :8080/api/users login='machin' firstname='truc' lastname='bidule' accept:application/xml
   * @param httpHeaders header http injecté depuis le contexte de l'appel
   * @param login login
   * @param firstname prénom
   * @param lastname nom
   * @return Réponse avec ou non l'utilisateur au niveau du body
   */
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
