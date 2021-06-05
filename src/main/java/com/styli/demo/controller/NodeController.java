package com.styli.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.styli.demo.document.Node;
import com.styli.demo.service.NodeService;

@RestController
public class NodeController {

    @Autowired
    NodeService nodeService;

    @GetMapping(value = "/app/{treeId}")
    public ResponseEntity<Node> getFullTree(@PathVariable("treeId") int treeId) {
        return ResponseEntity.ok(nodeService.getFullTree(treeId));
    }

    @GetMapping(value = "/app/{treeId}/st/{nodeId}")
    public ResponseEntity<Node> getSubtree(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId) {
        return ResponseEntity.ok(nodeService.getSubTree(treeId, nodeId, null));
    }

	@PutMapping(value = "/app/{treeId}/{nodeId}")
	public ResponseEntity<Void> move(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId,
			@RequestParam int newParentNodeId) {
		nodeService.move(treeId, nodeId , newParentNodeId);
		return ResponseEntity.ok().build();
	}
	
    @DeleteMapping(value = "/app/{treeId}/{nodeId}")
	public ResponseEntity<Void> deleteNodes(@PathVariable("treeId") int treeId, @PathVariable("nodeId") int nodeId) {
    	nodeService.deleteNodes(treeId, nodeId);
    	return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/app/")
	public ResponseEntity<Void> create(@RequestBody Node treeNode) {
		nodeService.create(treeNode);
		return ResponseEntity.ok().build();
	}
}
