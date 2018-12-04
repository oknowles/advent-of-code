package com.oliver.adventofcode.year2017;

import com.oliver.adventofcode.Utils;

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
        Map<String, Node> nodeList = new HashMap<>();
        instructions.forEach(instruction -> processInstruction(instruction, nodeList));

        for (Node node : nodeList.values()) {
            if (node.getParent() == null) {
                return node;
            }
        }
        return null;
    }

    private void processInstruction(String instruction, Map<String, Node> nodeList) {
        String[] parts = instruction.split(" -> ");
        String[] programParts = parts[0].split("\\s");
        String name = programParts[0];
        int weight = Integer.parseInt(programParts[1].replaceAll("[()]", ""));

        Node node = nodeList.getOrDefault(name, new Node(name));
        node.setWeight(weight);
        nodeList.put(name, node);

        if (parts.length > 1) {
            List<String> children = Arrays.asList(parts[1].split(", "));
            for (String child : children) {
                Node childNode = nodeList.getOrDefault(child, new Node(child));
                childNode.setParent(node);
                nodeList.put(child, childNode);
                node.addChild(childNode);
            }
        }
    }

    private Node getIncorrectNode(List<Node> nodes) {
        Map<Integer, Integer> cumulativeWeightCount = new HashMap<>();
        for (Node node : nodes) {
            int weightCount = cumulativeWeightCount.getOrDefault(node.getCumulativeWeight(), 0);
            cumulativeWeightCount.put(node.getCumulativeWeight(), weightCount + 1);
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
        private Node parent;
        private List<Node> children;

        public Node(String name) {
            this.name = name;
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

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public List<Node> getChildren() {
            return children;
        }
    }
}
