package io.github.r_h.wicket_notes_demo;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class Start {

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		ServerConnector connector = new ServerConnector(server);
		// Set some timeout options to make debugging easier.
		connector.setIdleTimeout(1000 * 60 * 60);
		connector.setPort(8080);
		server.setConnectors(new Connector[] { connector });

		// FIXME : Do we want to add jetty-webapp dep for this ?! 
		// WebContext bb = new WebContext();
		//bb.setServer(server);
		//bb.setContextPath("/");
		//bb.setWar("src/main/webapp");

		
		// START JMX SERVER
		// MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		// MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		// server.getContainer().addEventListener(mBeanContainer);
		// mBeanContainer.start();
		
		//server.addHandler(bb);

		try {
			System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
			server.start();
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
