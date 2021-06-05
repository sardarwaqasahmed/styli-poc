package com.styli.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.styli.demo.document.Node;

public interface NodeService {

    int DEFAULT_ROOT_NODE_ID = -1;

    Node getFullTree(int treeId);

    Node getSubTree(int treeId, int nodeId, Long maxDepth);

    void deleteNodes(int treeId, int nodeId);

	void create(Node Node);

	void move(int treeId, int nodeId, int newParentNodeId);
	
	static Node buildTree(final List<Node> nodeList, final int rootNodeId) {
        final Map<Integer, Node> nodeMap = new LinkedHashMap<>();
        // Save all NodeList to a map
        for (final Node current : nodeList) {
            nodeMap.put(current.getNodeId(), current);
        }
        // Loop and assign parent/child relationships
        for (final Node current : nodeList) {
            final List<Integer> parents = current.getParentId();

            if (!CollectionUtils.isEmpty(parents)) {
                for (final Integer pid : parents) {
                    final Node parent = nodeMap.get(pid);
                    if (parent != null) {
                        parent.addChild(current);
                        current.addParent(parent);
                    }
                }
            }
        }
        return nodeMap.get(rootNodeId);
    }

}
