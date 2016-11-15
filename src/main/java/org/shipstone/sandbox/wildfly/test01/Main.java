package org.shipstone.sandbox.wildfly.test01;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;
import org.wildfly.swarm.jpa.JPAFraction;

/**
 * @author François Robert
 */
public class Main {

  static public void main(String... args) throws Exception {
    // initialisation du container
    Swarm containerSwarm = new Swarm();

    containerSwarm.fraction(new DatasourcesFraction()
        .jdbcDriver("org.postgresql", (jdbcDriver) -> {
          jdbcDriver.driverClassName("org.postgresql.Driver");
          jdbcDriver.xaDatasourceClass("org.postgresql.xa.PGXADataSource");
          jdbcDriver.driverModuleName("org.postgresql");
        })
        .dataSource("DataDS", (dataSource) -> {
          dataSource.driverName("org.postgresql");
          dataSource.connectionUrl("jdbc:postgresql://localhost:5432/DataDB");
          dataSource.userName("data");
          dataSource.password("data");
        })
    );

    containerSwarm.fraction(
        new JPAFraction().defaultDatasource("java:jboss/datasources/DataDS")
    );

    //System.setProperty("swarm.use.db", "h2");
    //String driverModule = "org.postgresql";

    // Création du deploiement
    JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
    // Ajout des du package de la classe Main, et parcours recurssif - Un peu trop large, mais pour notre exemple cela suffit
    deployment.addPackages(true, Main.class.getPackage());
    //deployment.addModule(driverModule);
    deployment.addAsWebInfResource(new ClassLoaderAsset("META-INF/persistence.xml", Main.class.getClassLoader()), "classes/META-INF/persistence.xml");

    // Lancement du serveur
    containerSwarm.start();
    // Ajout de "l'archive"
    containerSwarm.deploy(deployment);

  }

}
