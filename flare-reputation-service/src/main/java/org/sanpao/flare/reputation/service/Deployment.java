package org.sanpao.flare.reputation.service;

import javax.annotation.PostConstruct;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.common.IgniteNodeTypes;
import org.sanpao.flare.reputation.api.PublishBadReview;
import org.sanpao.flare.reputation.service.impl.PublishBadReviewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("reputationDeployment")
public class Deployment {

	@Autowired
	@Qualifier("reputationServiceIgnite")
	private Ignite ignite;

	@PostConstruct
	public void deploy() {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		IgniteServices services = ignite.services(clusterGroup);
		// 部署函数服务
		services.deployNodeSingleton(PublishBadReview.class.getName(), new PublishBadReviewImpl());
	}

}
