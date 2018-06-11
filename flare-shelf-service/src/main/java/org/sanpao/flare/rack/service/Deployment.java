package org.sanpao.flare.rack.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.common.ignite.IgniteNodeTypes;
import org.sanpao.flare.rack.api.AddAdvertisementToShelf;
import org.sanpao.flare.rack.service.impl.AddAdvertisementToShelfImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("rackDeployment")
public class Deployment {

	@Autowired
	@Qualifier("rackServiceIgnite")
	private Ignite ignite;

	@PostConstruct
	public void deploy() {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		IgniteServices services = ignite.services(clusterGroup);
		// 部署函数服务
		services.deployNodeSingleton(AddAdvertisementToShelf.class.getName(), new AddAdvertisementToShelfImpl());
	}

}
