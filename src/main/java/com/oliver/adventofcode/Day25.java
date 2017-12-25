package com.oliver.adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day25 {

    private int diagnosticChecksum;
    private Map<String, MachineRule> machineRules;
    private Map<Integer, Integer> tape;
    private int cursor;
    private String state;

    public Day25(List<String> blueprints) {
        String firstLine = blueprints.get(0);
        String secondLine = blueprints.get(1);
        String startingState = firstLine.substring(15, firstLine.length() - 1);
        this.diagnosticChecksum = Integer.parseInt(secondLine.substring(36, secondLine.length() - 7));
        this.machineRules = buildRules(blueprints);
        this.tape = new HashMap<>();
        this.cursor = 0;
        this.state = startingState;
    }

    private Map<String, MachineRule> buildRules(List<String> blueprints) {
        Map<String, MachineRule> machineRules = new HashMap<>();
        for (int blueprintPos = 3; blueprintPos < blueprints.size(); blueprintPos += 10) {
            String ruleState = blueprints.get(blueprintPos).substring(9, 10);
            Map<Integer, MachineSubRule> machineSubRules = new HashMap<>();
            for (int state = 0; state < 2; state++) {
                int valToWrite = Integer.parseInt(blueprints.get(4*state + blueprintPos + 2).substring(22, 23));
                int slotsToMove = (blueprints.get(4*state + blueprintPos + 3).charAt(27) == 'l') ? -1 : 1;
                String subsequentState = blueprints.get(4*state + blueprintPos + 4).substring(26, 27);
                machineSubRules.put(state, new MachineSubRule(valToWrite, slotsToMove, subsequentState));
            }
            machineRules.put(ruleState, new MachineRule(machineSubRules));
        }
        return machineRules;
    }

    public int getDiagnosticChecksum() {
        for (int i = 0; i < diagnosticChecksum; i++) {
            int curVal = tape.getOrDefault(cursor, 0);
            MachineSubRule rule = machineRules.get(state).getSubRule(curVal);
            tape.put(cursor, rule.getValToWrite());
            cursor += rule.getCursorChange();
            state = rule.getSubsequentState();
        }

        return tape.values().stream().mapToInt(Integer::intValue).sum();
    }

    class MachineRule {

        private Map<Integer, MachineSubRule> machineSubRules;

        public MachineRule(Map<Integer, MachineSubRule> machineSubRules) {
            this.machineSubRules = machineSubRules;
        }

        public MachineSubRule getSubRule(int state) {
            return machineSubRules.get(state);
        }
    }

    class MachineSubRule {
        private int valToWrite;
        private int cursorChange;
        private String subsequentState;

        public MachineSubRule(int valToWrite, int cursorChange, String subsequentState) {
            this.valToWrite = valToWrite;
            this.cursorChange = cursorChange;
            this.subsequentState = subsequentState;
        }

        public String getSubsequentState() {
            return subsequentState;
        }

        public int getCursorChange() {
            return cursorChange;
        }

        public int getValToWrite() {
            return valToWrite;
        }
    }
}
