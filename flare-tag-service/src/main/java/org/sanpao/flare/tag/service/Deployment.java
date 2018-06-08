package org.sanpao.flare.tag.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.common.IgniteNodeTypes;
import org.sanpao.flare.tag.api.MakeTag;
import org.sanpao.flare.tag.service.impl.MakeTagImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tagDeployment")
public class Deployment {

	@Autowired
	@Qualifier("tagServiceIgnite")
	private Ignite ignite;

	@PostConstruct
	public void deploy() {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		IgniteServices services = ignite.services(clusterGroup);
		// 部署函数服务
		services.deployNodeSingleton(MakeTag.class.getName(), new MakeTagImpl());
	}

}
