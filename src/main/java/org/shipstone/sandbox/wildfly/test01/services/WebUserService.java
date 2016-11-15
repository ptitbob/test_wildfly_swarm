package org.shipstone.sandbox.wildfly.test01.services;

import org.shipstone.sandbox.wildfly.test01.bean.WebUser;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRED;

/**
 * @author François Robert
 */
@Stateless
@Local(WebUserService.class)
public class WebUserService {

  @PersistenceContext(unitName = "primary")
  private EntityManager entityManager;

  /**
   * Liste de tous les utilisateurs
   * @return liste
   */
  @TransactionAttribute(REQUIRED)
  public List<WebUser> getAll() {
    return entityManager.createNamedQuery(WebUser.GET_ALL, WebUser.class)
        .getResultList();
  }

  /**
   * Renvoi un utilisateur selon son Id
   * @param id identifiant de l'utilisateur
   * @return utisateur
   * @throws NotFoundException en cas d'utilisateur non trouvé - version 1 --> renvoi une erreur 404
   */
  @TransactionAttribute(REQUIRED)
  public WebUser getById(Long id) {
    WebUser webUser = entityManager.find(WebUser.class, id);
    if (webUser == null) {
      throw new NotFoundException("L'utilisateur n'a pas été localisé");
    }
    return webUser;
  }

  /**
   * Création d'un utilisateur
   * @param login login
   * @param firstname prénom
   * @param lastname nom
   * @return utilisateur créé.
   */
  @TransactionAttribute(REQUIRED)
  public WebUser create(String login, String firstname, String lastname) {
    WebUser webUser = new WebUser(login, firstname, lastname);
    entityManager.persist(webUser);
    return webUser;
  }
}
