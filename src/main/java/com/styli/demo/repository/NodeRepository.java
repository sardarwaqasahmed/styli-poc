package com.styli.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.styli.demo.document.Node;

public interface NodeRepository extends MongoRepository<Node, Object>, NodeGraphLookupRepository {

	Optional<List<Node>> findByTreeId(int treeId);

	Node findByNodeId(int nodeId);

	Optional<Node> findByTreeIdAndNodeId(int treeId, int nodeId);

}