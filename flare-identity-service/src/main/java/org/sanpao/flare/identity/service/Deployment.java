package org.sanpao.flare.identity.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.common.ignite.IgniteNodeTypes;
import org.sanpao.flare.identity.api.BindMobilePhone;
import org.sanpao.flare.identity.service.impl.BindMobilePhoneImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("identityDeployment")
public class Deployment {

	@Autowired
	@Qualifier("identityServiceIgnite")
	private Ignite ignite;

	@PostConstruct
	public void deploy() {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		IgniteServices services = ignite.services(clusterGroup);
		// 部署函数服务
		services.deployNodeSingleton(BindMobilePhone.class.getName(), new BindMobilePhoneImpl());
	}

}
