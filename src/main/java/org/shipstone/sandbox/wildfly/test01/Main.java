package org.shipstone.sandbox.wildfly.test01;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.shipstone.sandbox.wildfly.test01.ws.rs.TestApplication;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * @author François Robert
 */
public class Main {

  static public void main(String... args) throws Exception {
    Swarm containerSwarm = new Swarm();
    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    deployment.addPackages(true, TestApplication.class.getPackage());
    containerSwarm.start();
    containerSwarm.deploy(deployment);
  }
}
