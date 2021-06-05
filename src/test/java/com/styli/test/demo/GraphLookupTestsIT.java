package com.styli.test.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.styli.demo.document.Node;
import com.styli.demo.repository.NodeRepository;
import com.styli.demo.service.NodeService;

@SpringBootTest
class GraphLookupTestsIT {

    @Autowired
    NodeService nodeService;

    @Autowired
	NodeRepository nodeRepository;

    @BeforeAll
	@Transactional
	void populate() {
		Node treeNode = new Node();
		treeNode.setTreeId(1001);
		treeNode.setNodeId(0);
		treeNode.setName("oak");
		treeNode.setParentId(List.of(-1));

		nodeRepository.save(treeNode);

		Node leafNode = new Node();
		leafNode.setTreeId(1001);
		leafNode.setNodeId(5);
		leafNode.setName("leaf");
		leafNode.setParentId(List.of(0));

		nodeRepository.save(leafNode);
	}

	@DisplayName(value = "given an existing tree and nodeId, retrieve its descendants")
    @Test
    void testSubTreeRetrieval() {
        Node node = nodeService.getSubTree(1001, 0, null);
        assertNotNull(node);
    }

}
