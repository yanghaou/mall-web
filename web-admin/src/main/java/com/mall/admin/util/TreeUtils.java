package com.mall.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtils {
	public static <T> Tree<T> build(List<Tree<T>> nodes) {
		if (nodes == null) {
			return null;
		}
		List<Tree<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			Long pid = children.getParentId();
			if (pid == null || pid==0) {
				topNodes.add(children);
				return;
			}
			for (Tree<T> parent : nodes) {
				Long id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setHasChildren(true);
					return;
				}
			}
		});

		Tree<T> root = new Tree<>();
		root.setId(0L);
		root.setParentId(0L);
		root.setHasParent(false);
		root.setHasChildren(true);
		root.setChecked(true);
		root.setChildren(topNodes);
		root.setName("根节点");
		Map<String, Object> state = new HashMap<>(16);
		state.put("opened", true);
		root.setState(state);
		return root;
	}

	public static <T> List<Tree<T>> buildList(List<Tree<T>> nodes, Long idParam) {
		if (nodes == null) {
			return new ArrayList<>();
		}
		List<Tree<T>> topNodes = new ArrayList<>();
		nodes.forEach(children -> {
			Long pid = children.getParentId();
			if (pid == null || idParam==pid) {
				topNodes.add(children);
				return;
			}
			nodes.forEach(parent -> {
				Long id = parent.getId();
				if (id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					children.setHasParent(true);
					parent.setHasChildren(true);
				}
			});
		});
		return topNodes;
	}
}