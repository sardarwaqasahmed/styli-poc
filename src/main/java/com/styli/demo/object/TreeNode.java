//package com.styli.demo.object;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.javers.core.metamodel.annotation.DiffIgnore;
//import org.javers.core.metamodel.annotation.Id;
//import org.javers.core.metamodel.annotation.TypeName;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@TypeName("TreeNode")
//@Getter
//@Setter
//@ToString
//public class TreeNode implements Serializable {
//
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//    private int nodeId;
//
//    private String name;
//
//    @DiffIgnore
//    private int treeId;
//
//    @DiffIgnore
//    private List<Integer> parentId;
//
//    private List<TreeNode> childrens;
//
//    @DiffIgnore
//    @JsonIgnore
//    private List<TreeNode> parents;
//
//    public TreeNode() {
//        this.parentId = new ArrayList<>();
//        this.childrens = new ArrayList<>();
//        this.parents = new ArrayList<>();
//    }
//
//    public void addChild(TreeNode child) {
//        if (this.childrens!= null && !this.childrens.contains(child) && child != null)
//            this.childrens.add(child);
//    }
//
//    public void addParent(TreeNode parent) {
//        if (this.parents!= null && !this.parents.contains(parent) && parent != null)
//            this.parents.add(parent);
//    }
//
//}