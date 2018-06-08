package org.sanpao.flare.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.beans.factory.annotation.Value;

public abstract class IgniteCacheConfig {

	//@Value("${flare.ignite.name}")
	private String name;

	@Value("${flare.ignite.addresses}")
	private String[] addresses;

	public abstract List<CacheConfiguration<?, ?>> cacheConfigurations();

	//@Bean
	public Ignite igniteInstance() {
		TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
		TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
		ipFinder.setAddresses(Arrays.asList(addresses));
		discoverySpi.setIpFinder(ipFinder);
		// Ignite persistence configuration.
		DataStorageConfiguration storageConfiguration = new DataStorageConfiguration();
		// Enabling the persistence.
		storageConfiguration.getDefaultDataRegionConfiguration().setPersistenceEnabled(true);
		IgniteConfiguration configuration = new IgniteConfiguration();
		configuration.setDiscoverySpi(discoverySpi);
		configuration.setUserAttributes(Collections.singletonMap(IgniteNodeTypes.DATA_NODE.name(), true));
		// Setting some custom name for the node.
		configuration.setIgniteInstanceName(name);
		// Enabling peer-class loading feature.
		// configuration.setPeerClassLoadingEnabled(true);
		configuration.setDataStorageConfiguration(storageConfiguration);
		cacheConfigurations().stream().forEach(cacheConfig -> cacheConfig.setNodeFilter(node -> {
			Boolean value = node.attribute(IgniteNodeTypes.DATA_NODE.name());
			if (null != value) {
				return value.booleanValue();
			}
			return false;
		}));
		configuration.setCacheConfiguration(cacheConfigurations().toArray(new CacheConfiguration[] {}));
		Ignite ignite = Ignition.getOrStart(configuration);
		return ignite;
	}

}
