package org.shipstone.sandbox.wildfly.test01.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author François Robert
 */
@Entity
@Table(name = "web_user")
@NamedQueries({
    @NamedQuery(name = WebUser.GET_ALL
        , query = "select wu from WebUser wu"
    )
})
@SequenceGenerator(name = "webuser_sequence", sequenceName = "seq_user_id", allocationSize = 1)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WebUser implements Serializable{

  public static final String GET_ALL = "WebUser.GET_ALL";
  @Id
  @Column(name = "id")
  @XmlAttribute
  private Long id;

  @Column(name = "login")
  private String login;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  public WebUser() {
  }

  public WebUser(String login, String firstname, String lastname) {
    this.login = login;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WebUser)) return false;
    WebUser webUser = (WebUser) o;
    return Objects.equals(getLogin(), webUser.getLogin()) &&
        Objects.equals(getFirstname(), webUser.getFirstname()) &&
        Objects.equals(getLastname(), webUser.getLastname());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLogin());
  }

  @Override
  public String toString() {
    return "WebUser{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }
}
