package org.shipstone.sandbox.wildfly.test01.ws.rs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author robert-f
 */
@Path("ping")
public class PingResources {

    @GET
    @Produces("text/plain")
    public String pong() {
        return "pong";
    }

}
