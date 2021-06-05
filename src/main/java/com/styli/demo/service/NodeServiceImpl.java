package com.styli.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.styli.demo.document.Node;
import com.styli.demo.exception.NotFoundException;
import com.styli.demo.repository.NodeRepository;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node getFullTree(int treeId) {
        List<Node> nodes = nodeRepository.findByTreeId(treeId).orElseThrow(NotFoundException::new);

        List<Node> nodesList = new ArrayList<>();
        for (Node node : nodes) {
            Node treeNode = new Node();
            BeanUtils.copyProperties(node, treeNode);
            nodesList.add(treeNode);
        }

        return NodeService.buildTree(nodesList, NodeService.DEFAULT_ROOT_NODE_ID);
    }

    @Override
	@Transactional(readOnly = true)
    public Node getSubTree(int treeId, int nodeId, Long maxDepth) {
        List<Node> nodes = nodeRepository.getSubTree(treeId, nodeId, null).orElseThrow(NotFoundException::new);

        List<Node> flatList = nodes.stream()
                .map(Node::getChildrens)
                .flatMap(Collection::stream)
                .map(node -> {
                    Node tr = new Node();
                    BeanUtils.copyProperties(node, tr, "id");
                    return tr;
                })
                .collect(Collectors.toList());

        Node root = new Node();
        BeanUtils.copyProperties(nodes.get(0), root, "id", "childrens");
        flatList.add(root);

        return (NodeService.buildTree(flatList, nodeId));
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void move(int treeId, int nodeId, int newParentNodeId) {
		// ... perform validations etc.
		var node = nodeRepository.findByTreeIdAndNodeId(treeId, nodeId).orElseThrow(NotFoundException::new);
		node.setParentId(List.of(newParentNodeId));
		nodeRepository.save(node);
	}
	
	@Override
    @Transactional(rollbackFor = Exception.class)
	public void deleteNodes(int treeId, int nodeId)  {
		List<Node> nodes = nodeRepository.getSubTree(treeId, nodeId, 1L).orElseThrow(NotFoundException::new);
		var target = nodes.get(0);
		if (!CollectionUtils.isEmpty(target.getChildrens())) {
			target.getChildrens().forEach(n -> n.setParentId(target.getParentId()));
			nodeRepository.saveAll(target.getChildrens());
		}

		nodeRepository.delete(target);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void create(Node node) {
    	// ... check if parent exists etc.
		Node parentNode = nodeRepository.findByNodeId(node.getParentId().get(0));
		if(null == parentNode) {
			Node newNode = new Node();
			newNode.setTreeId(node.getTreeId());
			newNode.setParentId(node.getParentId());
			newNode.setName(node.getName());
			newNode.setNodeId(new Random().nextInt());

	    	nodeRepository.save(newNode);
	    	List<Integer> parents = new ArrayList<>();
	    	for(Node child:node.getChildrens()) {
	    		
	    		parents.add(newNode.getNodeId());
	        	Node childNode = new Node();
	        	childNode.setTreeId(child.getTreeId());
	        	childNode.setParentId(parents);
	        	childNode.setName(child.getName());
	        	childNode.setNodeId(new Random().nextInt());

	        	nodeRepository.save(childNode);
	    	}	
		} else {
			
			Node childNode = new Node();
			childNode.setTreeId(parentNode.getTreeId());
			childNode.setParentId(parentNode.getParentId());
			childNode.setName(node.getName());
			childNode.setNodeId(new Random().nextInt());
	    	nodeRepository.save(childNode);
			
	    	List<Integer> parents = new ArrayList<>();
	    	for(Node child:node.getChildrens()) {
	    		
	    		parents.add(childNode.getNodeId());
	        	Node newChildNode = new Node();
	        	newChildNode.setTreeId(child.getTreeId());
	        	newChildNode.setParentId(parents);
	        	newChildNode.setName(child.getName());
	        	newChildNode.setNodeId(new Random().nextInt());

	        	nodeRepository.save(newChildNode);
	    	}	
		}
    	
	}
}
