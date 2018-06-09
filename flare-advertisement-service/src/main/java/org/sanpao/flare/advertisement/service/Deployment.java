package org.sanpao.flare.advertisement.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.advertisement.api.PublishAdvertisement;
import org.sanpao.flare.advertisement.service.impl.PublishAdvertisementImpl;
import org.sanpao.flare.common.ignite.IgniteNodeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("advertisementDeployment")
public class Deployment {

	@Autowired
	@Qualifier("advertisementServiceIgnite")
	private Ignite ignite;

	@PostConstruct
	public void deploy() {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		IgniteServices services = ignite.services(clusterGroup);
		// 部署函数服务
		services.deployNodeSingleton(PublishAdvertisement.class.getName(), new PublishAdvertisementImpl());
	}

}
