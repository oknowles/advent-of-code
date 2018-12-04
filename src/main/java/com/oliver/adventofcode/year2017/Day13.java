package com.oliver.adventofcode.year2017;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 {

    private Map<Integer, Integer> layerDepthMap;
    private int maxLayer;

    public Day13(List<String> inputs) {
        maxLayer = -1;
        layerDepthMap = new HashMap<>();
        for (String input : inputs) {
            String[] parts = input.split(": ");
            int layer = Integer.parseInt(parts[0]);
            layerDepthMap.put(layer, Integer.parseInt(parts[1]));
            maxLayer = (layer > maxLayer) ? layer : maxLayer;
        }
    }

    public int getSeverityOfTrip() {
        int severity = 0;
        for (int layer = 1; layer <= maxLayer; layer++) {
            if (detectedInLayer(layer, layer)) {
                severity += layer * layerDepthMap.get(layer);
            }
        }
        return severity;
    }

    public int getUndetectedDelay() {
        int delay = 0;
        boolean undetected = false;
        while (!undetected) {
            undetected = undetected(delay);
            delay++;
        }
        return delay - 1;
    }

    private boolean undetected(int startDelay) {
        for (int layer = 0; layer <= maxLayer; layer++) {
            if (detectedInLayer(layer + startDelay, layer)) {
                return false;
            }
        }
        return true;
    }

    private boolean detectedInLayer(int timeStep, int layer) {
        Integer layerDepth = layerDepthMap.get(layer);
        if (layerDepth != null) {
            int layerCycle = 2 * (layerDepth - 1);
            return timeStep % layerCycle == 0;
        }
        return false;
    }
}
