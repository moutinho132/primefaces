/**
 * Copyright 2009-2018 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.component.tree;

import org.primefaces.model.TreeNode;

public class TreeDragDropInfo {

    private TreeNode dragNode;

    private TreeNode[] dragNodes;

    private TreeNode dropNode;

    private int dropIndex;

    private boolean droppedNodeCopy;

    public TreeDragDropInfo(TreeNode dragNode, TreeNode dropNode, int dropIndex, boolean droppedNodeCopy) {
        this.dragNode = dragNode;
        this.dropNode = dropNode;
        this.dropIndex = dropIndex;
        this.droppedNodeCopy = droppedNodeCopy;
    }

    public TreeDragDropInfo(TreeNode[] dragNodes, TreeNode dropNode, int dropIndex, boolean droppedNodeCopy) {
        this.dragNodes = dragNodes;
        this.dropNode = dropNode;
        this.dropIndex = dropIndex;
        this.droppedNodeCopy = droppedNodeCopy;
    }

    public TreeNode getDragNode() {
        return dragNode;
    }

    public TreeNode[] getDragNodes() {
        return dragNodes;
    }

    public TreeNode getDropNode() {
        return dropNode;
    }

    public int getDropIndex() {
        return dropIndex;
    }

    public boolean isDroppedNodeCopy() {
        return droppedNodeCopy;
    }
}
