package org.sanpao.flare.common.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterGroup;
import org.sanpao.flare.common.ApiFunction;

public class IgniteFunctionFactory {

	public static <T extends ApiFunction<?>> T newFunction(Ignite ignite, Class<T> functionInterface) {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		return ignite.services(clusterGroup).serviceProxy(functionInterface.getName(), functionInterface, true);
	}

}
