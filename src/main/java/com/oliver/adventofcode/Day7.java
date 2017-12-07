package com.oliver.adventofcode;

import java.util.*;

public class Day7 {

    private Node root;

    public Day7(List<String> instructions) {
        this.root = buildTree(instructions);
    }

    public static Day7 fromInput() {
        List<String> instructions = Utils.readFile(7);
        return new Day7(instructions);
    }

    public String getBottomProgram() {
        return root.getName();
    }

    public int getCorrectWeight() {
        computeCumulativeWeights();

        Node curNode = root;
        Node incorrectChild = getIncorrectNode(curNode.getChildren());

        while (incorrectChild != null) {
            curNode = incorrectChild;
            incorrectChild = getIncorrectNode(incorrectChild.getChildren());
        }

        int weightDiff = curNode.getBalancedCumulativeWeight() - curNode.getCumulativeWeight();
        return curNode.getWeight() + weightDiff;
    }

    private Node buildTree(List<String> instructions) {
        Map<String, Node> parentlessNodes = new HashMap<>();
        Map<String, Node> partialChildNodes = new HashMap<>();
        for (String input : instructions) {
            String[] parts = input.split(" -> ");
            String[] parentParts = parts[0].split("\\s");
            String name = parentParts[0];
            int weight = Integer.parseInt(parentParts[1].replaceAll("[()]", ""));

            Node curNode;
            if (partialChildNodes.containsKey(name)) {
                // a previous child of another node so can use that and remove from map
                curNode = partialChildNodes.get(name);
                curNode.setWeight(weight);
                partialChildNodes.remove(name);
            } else {
                // no currently known parents so add to parentless map
                curNode = new Node(name, weight);
                parentlessNodes.put(name, curNode);
            }

            if (parts.length > 1) {
                List<String> children = Arrays.asList(parts[1].split(", "));
                for (String child : children) {
                    Node childNode;
                    if (parentlessNodes.containsKey(child)) {
                        // child was previously parentless but now not
                        childNode = parentlessNodes.get(child);
                        parentlessNodes.remove(child);
                    } else {
                        childNode = new Node(child);
                        partialChildNodes.put(child, childNode);
                    }

                    curNode.addChild(childNode);
                }
            }
        }
        return parentlessNodes.values().stream().findFirst().get();
    }

    private Node getIncorrectNode(List<Node> nodes) {
        Map<Integer, Integer> cumulativeWeightCount = new HashMap<>();
        for (Node node : nodes) {
            int weightCount = cumulativeWeightCount.getOrDefault(node.getCumulativeWeight(), 0);
            cumulativeWeightCount.put(node.getCumulativeWeight(), weightCount + 1);
        }

        if (cumulativeWeightCount.size() == 1) {
            return null;
        }

        int mostCommonWeightCount = -1;
        int mostCommonWeight = -1;
        for (Map.Entry<Integer, Integer> entry : cumulativeWeightCount.entrySet()) {
            if (entry.getValue() > mostCommonWeightCount) {
                mostCommonWeight = entry.getKey();
                mostCommonWeightCount = entry.getValue();
            }
        }

        for (Node node : nodes) {
            if (node.getCumulativeWeight() != mostCommonWeight) {
                node.setBalancedCumulativeWeight(mostCommonWeight);
                return node;
            }
        }
        return null;
    }

    private void computeCumulativeWeights() {
        getCumulativeWeight(root);
    }

    private int getCumulativeWeight(Node curNode) {
        if (curNode.getChildren().isEmpty()) {
            return curNode.getWeight();
        }

        int cumulativeWeight = curNode.getWeight();
        for (Node child : curNode.getChildren()) {
            cumulativeWeight += getCumulativeWeight(child);
        }
        curNode.setCumulativeWeight(cumulativeWeight);
        return cumulativeWeight;
    }

    static class Node {
        private String name;
        private Integer weight;
        private Integer cumulativeWeight;
        private Integer balancedCumulativeWeight;
        private List<Node> children;

        public Node(String name) {
            this.name = name;
            this.children = new ArrayList<>();
        }

        public Node(String name, int weight) {
            this.name = name;
            this.weight = weight;
            this.children = new ArrayList<>();
        }

        public void addChild(Node node) {
            children.add(node);
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public Integer getCumulativeWeight() {
            return cumulativeWeight;
        }

        public void setCumulativeWeight(Integer cumulativeWeight) {
            this.cumulativeWeight = cumulativeWeight;
        }

        public Integer getBalancedCumulativeWeight() {
            return balancedCumulativeWeight;
        }

        public void setBalancedCumulativeWeight(Integer balancedCumulativeWeight) {
            this.balancedCumulativeWeight = balancedCumulativeWeight;
        }

        public List<Node> getChildren() {
            return children;
        }
    }
}
