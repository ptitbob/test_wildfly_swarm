package org.shipstone.sandbox.wildfly.test01.services;

import org.shipstone.sandbox.wildfly.test01.bean.WebUser;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.NotFoundException;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRED;

/**
 * @author François Robert
 */
@RequestScoped
public class WebUserService {

  @PersistenceContext(unitName = "primary")
  private EntityManager entityManager;

  public List<WebUser> getAll() {
    return entityManager.createNamedQuery(WebUser.GET_ALL, WebUser.class)
        .getResultList();
  }


  public WebUser getById(Long id) {
    WebUser webUser = entityManager.find(WebUser.class, id);
    if (webUser == null) {
      throw new NotFoundException("L'utilisateur n'a pas été localisé");
    }
    return webUser;
  }

  public WebUser create(String login, String firstname, String lastname) {
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    WebUser webUser = new WebUser(login, firstname, lastname);
    entityManager.persist(webUser);
    entityTransaction.commit();
    return webUser;
  }
}
