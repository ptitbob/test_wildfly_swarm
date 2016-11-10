package org.shipstone.sandbox.wildfly.test01.ws.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author robert-f
 */
@Path("ping")
public class PingResources {

    @GET
    public String pong() {
        return "pong";
    }

}
