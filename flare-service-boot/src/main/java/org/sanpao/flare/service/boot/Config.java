package org.sanpao.flare.service.boot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.sanpao.flare.common.IgniteNodeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Value("${flare.ignite.addresses}")
	private String[] addresses;

	@Autowired
	@Qualifier("advertisementCacheConfigurations")
	private List<CacheConfiguration<?, ?>> advertisementCacheConfigurations;

	@Autowired
	@Qualifier("identityCacheConfigurations")
	private List<CacheConfiguration<?, ?>> identityCacheConfigurations;

	@Autowired
	@Qualifier("orderCacheConfigurations")
	private List<CacheConfiguration<?, ?>> orderCacheConfigurations;

	@Autowired
	@Qualifier("paymentCacheConfigurations")
	private List<CacheConfiguration<?, ?>> paymentCacheConfigurations;

	@Autowired
	@Qualifier("tagCacheConfigurations")
	private List<CacheConfiguration<?, ?>> tagCacheConfigurations;

	@Autowired
	@Qualifier("reputationCacheConfigurations")
	private List<CacheConfiguration<?, ?>> reputationCacheConfigurations;

	@Bean
	public Ignite igniteInstance() {
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Arrays.asList(addresses));
		discoverySpi.setIpFinder(ipFinder);
		// Enabling the persistence.
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setDiscoverySpi(discoverySpi);
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeTypes.DATA_NODE.name(), true));
		// Setting some custom name for the node.
		configuration.setIgniteInstanceName("FLARE_DATA_NODE");
		// Enabling peer-class loading feature.
		// configuration.setPeerClassLoadingEnabled(true);
		List<CacheConfiguration<?, ?>> cacheConfigurations = new ArrayList<CacheConfiguration<?, ?>>();
		cacheConfigurations.addAll(advertisementCacheConfigurations);
		cacheConfigurations.addAll(identityCacheConfigurations);
		cacheConfigurations.addAll(orderCacheConfigurations);
		cacheConfigurations.addAll(paymentCacheConfigurations);
		cacheConfigurations.addAll(tagCacheConfigurations);
		cacheConfigurations.addAll(reputationCacheConfigurations);
		configuration.setCacheConfiguration(cacheConfigurations.toArray(new CacheConfiguration[] {}));
		Ignite ignite = Ignition.getOrStart(configuration);
		return ignite;
	}

}
