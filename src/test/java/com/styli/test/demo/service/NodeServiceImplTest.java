package com.styli.test.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.styli.demo.document.Node;
import com.styli.demo.repository.NodeRepository;
import com.styli.demo.service.NodeService;
import com.styli.demo.service.NodeServiceImpl;

@ExtendWith(MockitoExtension.class)
class NodeServiceImplTest {

    @InjectMocks
    private NodeServiceImpl nodeService;

    @Mock
    private NodeRepository nodeRepository;

    @Test
    void getFullTree() throws Exception {
        final Node node = new Node();
        node.setTreeId(1);
        node.setNodeId(NodeService.DEFAULT_ROOT_NODE_ID);
        node.setName("name1");

        when(nodeRepository.findByTreeId(1)).thenReturn(Optional.of(Collections.singletonList(node)));

        final Node fullTree = nodeService.getFullTree(1);
        assertNotNull(fullTree);
    }
}