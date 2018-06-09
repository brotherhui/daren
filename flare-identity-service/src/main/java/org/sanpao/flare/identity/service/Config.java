package org.sanpao.flare.identity.service;

import java.util.Arrays;
import java.util.Collections;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.sanpao.flare.common.ignite.IgniteNodeTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource({ "classpath:identity-service-config.properties" })
public class Config {

	@Value("${flare.ignite.addresses}")
	private String[] addresses;

	@Bean
	public Ignite identityServiceIgnite() {
		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Arrays.asList(addresses));
		spi.setIpFinder(ipFinder);
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setDiscoverySpi(spi);
		configuration.setIgniteInstanceName("FLARE_IDENTITY_SERVICE_NODE");
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeTypes.SERVICE_NODE.name(), true));
		Ignite ignite = Ignition.getOrStart(configuration);
		return ignite;
	}

}
