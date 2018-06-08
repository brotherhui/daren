package org.sanpao.flare.common;

import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterGroup;

public class IgniteServiceFactory {

	public static <T extends Function<?, ?>> T newFunction(Ignite ignite, Class<T> functionInterface) {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		return ignite.services(clusterGroup).serviceProxy(functionInterface.getName(), functionInterface, true);
	}

	public static <T extends Consumer<?>> T newConsumer(Ignite ignite, Class<T> consumerInterface) {
		ClusterGroup clusterGroup = ignite.cluster().forAttribute(IgniteNodeTypes.SERVICE_NODE.name(), true);
		return ignite.services(clusterGroup).serviceProxy(consumerInterface.getName(), consumerInterface, true);
	}

}
