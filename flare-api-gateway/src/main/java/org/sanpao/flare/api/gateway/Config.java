package org.sanpao.flare.api.gateway;

import java.util.Arrays;
import java.util.Collections;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.sanpao.flare.common.IgniteNodeTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Value("${flare.ignite.name}")
	private String name;

	@Value("${flare.ignite.addresses}")
	private String[] addresses;

	@Bean
	public Ignite ignite() {
		TcpDiscoverySpi spi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Arrays.asList(addresses));
		spi.setIpFinder(ipFinder);
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setDiscoverySpi(spi);
		configuration.setIgniteInstanceName(name);
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeTypes.GATEWAY_NODE.name(), true));
		Ignite ignite = Ignition.getOrStart(configuration);
		return ignite;
	}

}
