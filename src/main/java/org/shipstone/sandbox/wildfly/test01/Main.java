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
    // initialisation du container
    Swarm containerSwarm = new Swarm();

    // Création du deploiement
    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    // Ajout des du package de la classe Main, et parcours recurssif - Un peu trop large, mais pour notre exemple cela suffit
    deployment.addPackages(true, TestApplication.class.getPackage());

    // Lancement du serveur
    containerSwarm.start();
    // Ajout de "l'archive"
    containerSwarm.deploy(deployment);
  }
}
