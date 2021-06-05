package com.styli.demo.repository;

import java.util.List;
import java.util.Optional;

import com.styli.demo.document.Node;

public interface NodeGraphLookupRepository {

	Optional<List<Node>> getSubTree(int treeId, int nodeId, Long maxDepth);

}
