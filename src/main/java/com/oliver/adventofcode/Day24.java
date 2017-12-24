package com.oliver.adventofcode;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 {

    private List<Component> components;
    private Set<List<Component>> bridges;

    public Day24(List<String> availableComponents) {
        components = new ArrayList<>();
        for (String componentString : availableComponents) {
            String[] parts = componentString.split("/");
            int portType1 = Integer.parseInt(parts[0]);
            int portType2 = Integer.parseInt(parts[1]);
            components.add(new Component(portType1, portType2));
        }
        bridges = new HashSet<>();
        getAllBridges(0, new ArrayList<>(), components, bridges);
    }

    public int getStrongestBridge() {
        int strongestBridge = Integer.MIN_VALUE;
        for (List<Component> bridge : bridges) {
            int bridgeStrength = getBridgeStrength(bridge);
            if (bridgeStrength > strongestBridge) {
                strongestBridge = bridgeStrength;
            }
        }
        return strongestBridge;
    }

    public int getLongestBridgeStrength() {
        List<List<Component>> sortedBridges = getSortedBridges(bridges);

        int strongestBridge = Integer.MIN_VALUE;
        int bridgePos = sortedBridges.size() - 1;
        List<Component> curBridge = sortedBridges.get(bridgePos);
        int longestBridgeLength = curBridge.size();

        while (curBridge.size() == longestBridgeLength) {
            int bridgeStrength = getBridgeStrength(curBridge);
            if (bridgeStrength > strongestBridge) {
                strongestBridge = bridgeStrength;
            }
            bridgePos--;
            curBridge = sortedBridges.get(bridgePos);
        }

        return strongestBridge;
    }

    private void getAllBridges(int requiredPortType, List<Component> currentBridge, List<Component> availableComponents, Set<List<Component>> bridges) {
        bridges.add(currentBridge);
        for (int componentPos = 0; componentPos < availableComponents.size(); componentPos++) {
            Component component = availableComponents.get(componentPos);
            if (component.hasPortType(requiredPortType)) {
                List<Component> remainingComponents = new ArrayList<>(availableComponents);
                remainingComponents.remove(componentPos);
                int exitPortType = component.getExitPortType(requiredPortType);
                List<Component> newBridge = new ArrayList<>(currentBridge);
                newBridge.add(component);
                getAllBridges(exitPortType, newBridge, remainingComponents, bridges);
            }
        }
    }

    private List<List<Component>> getSortedBridges(Set<List<Component>> unsortedBridges) {
        return bridges.stream()
                .sorted(Comparator.comparingInt(List::size))
                .collect(Collectors.toList());

    }

    private int getBridgeStrength(List<Component> bridge) {
        return bridge.stream().mapToInt(Component::getStrength).sum();
    }

    class Component {
        private int portType1;
        private int portType2;

        public Component(int portType1, int portType2) {
            this.portType1 = portType1;
            this.portType2 = portType2;
        }

        public boolean hasPortType(int portType) {
            return portType == portType1 || portType == portType2;
        }

        public int getExitPortType(int inputPortType) {
            return (portType1 == inputPortType) ? portType2 : portType1;
        }

        public int getStrength() {
            return portType1 + portType2;
        }
    }
}
