
package com.styli.demo.document;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "ecommerece")
@Getter
@Setter
public class Node {

  @Id
  private String id;

  @Field(name = "nodeId")
  private int nodeId;

  @Field(name = "name")
  private String name;

  @Field(name = "treeId")
  private int treeId;

  @Field(name = "parentId")
  private List<Integer> parentId;

  @Field(name = "childrens")
  private List<Node> childrens;
  
  @Field(name = "parents")
  private List<Node> parents;

  public Node() {
      this.parentId = new ArrayList<>();
      this.childrens = new ArrayList<>();
      this.parents = new ArrayList<>();
  }

  public void addChild(Node child) {
      if (this.childrens!= null && !this.childrens.contains(child) && child != null)
          this.childrens.add(child);
  }

  public void addParent(Node parent) {
      if (this.parents!= null && !this.parents.contains(parent) && parent != null)
          this.parents.add(parent);
  }
}
