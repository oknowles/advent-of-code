package com.oliver.adventofcode.year2017;

import java.util.*;

public class Day18 {

    private Map<Character, Long> registers;
    private Map<Integer, Map<Character, Long>> registersForProgramId;

    public Day18() {
        registers = new HashMap<>();
        registersForProgramId = new HashMap<>();
        registersForProgramId.put(0, new HashMap<>());
        registersForProgramId.put(1, new HashMap<>());
    }

    public long getFirstPositiveRecoveredSound(List<String> instructions) {
        Stack<Long> playedSounds = new Stack<>();
        int instructionPos = 0;
        while (instructionPos < instructions.size()) {
            String instruction = instructions.get(instructionPos);
            String[] parts = instruction.split(" ");
            char charToSet;
            long prevVal;
            switch (parts[0]) {
                case "snd":
                    playedSounds.push(getValue(parts[1]));
                    break;
                case "set":
                    charToSet = parts[1].charAt(0);
                    registers.put(charToSet, getValue(parts[2]));
                    break;
                case "add":
                    charToSet = parts[1].charAt(0);
                    prevVal = getValue(parts[1]);
                    registers.put(charToSet, prevVal + getValue(parts[2]));
                    break;
                case "mul":
                    charToSet = parts[1].charAt(0);
                    prevVal = getValue(parts[1]);
                    registers.put(charToSet, prevVal * getValue(parts[2]));
                    break;
                case "mod":
                    charToSet = parts[1].charAt(0);
                    prevVal = getValue(parts[1]);
                    registers.put(charToSet, prevVal % getValue(parts[2]));
                    break;
                case "rcv":
                    if (!playedSounds.empty() && playedSounds.peek() > 0) {
                        return playedSounds.pop();
                    }
                    break;
                case "jgz":
                    long val = getValue(parts[1]);
                    if (val > 0) {
                        instructionPos += getValue(parts[2]);
                        continue;
                    }
            }
            instructionPos++;
        }
        return -1;
    }

    private long getValue(String variable) {
        char firstChar = variable.charAt(0);
        if (Character.isAlphabetic(firstChar)) {
            return registers.getOrDefault(firstChar, 0L);
        }
        return Long.parseLong(variable);
    }

    public int runParallelPrograms(List<String> instructions) {
        Program program0 = new Program(0, instructions);
        Program program1 = new Program(1, instructions);
        program0.setPairedProgram(program1);
        program1.setPairedProgram(program0);

        while (program0.canContinue() || program1.canContinue()) {
            while (program0.canContinue()) {
                program0.processNextInstruction();
            }

            while (program1.canContinue()) {
                program1.processNextInstruction();
            }
        }

        return program1.getSentMessages();
    }

    class Program {
        private long[] registers;
        private Deque<Long> receivedValues;
        private List<String> instructions;
        private int instructionPosition;
        private Program pairedProgram;
        private int sentMessages;

        Program(int programId, List<String> instructions) {
            this.instructions = instructions;
            this.instructionPosition = 0;
            this.receivedValues = new ArrayDeque<>();
            this.sentMessages = 0;
            this.registers = new long[26];
            for (int i = 0; i < 26; i++) {
                registers[i] = 0;
            }
            registers[getRegisterPos('p')] = programId;
        }

        public void setPairedProgram(Program pairedProgram) {
            this.pairedProgram = pairedProgram;
        }

        private int getRegisterPos(char c) {
            return c - 'a';
        }

        public void processNextInstruction() {
            String instruction = instructions.get(instructionPosition);
            String[] parts = instruction.split("\\s");
            String command = parts[0];
            if (command.equals("snd")) {
                sendToPairedProgram(getValue(parts[1]));
                sentMessages++;
            } else if (command.equals("set")) {
                registers[getRegisterPos(parts[1].charAt(0))] = getValue(parts[2]);
            } else if (command.equals("add")) {
                registers[getRegisterPos(parts[1].charAt(0))] += getValue(parts[2]);
            } else if (command.equals("mul")) {
                registers[getRegisterPos(parts[1].charAt(0))] *= getValue(parts[2]);
            } else if (command.equals("mod")) {
                registers[getRegisterPos(parts[1].charAt(0))] %= getValue(parts[2]);
            } else if (command.equals("rcv")) {
                registers[getRegisterPos(parts[1].charAt(0))] = getReceivedValue();
            } else if (command.equals("jgz")) {
                if (getValue(parts[1]) > 0) {
                    instructionPosition += getValue(parts[2]);
                    return;
                }
            }
            instructionPosition++;
        }

        public void addToReceivedQueue(long value) {
            receivedValues.addLast(value);
        }

        public void sendToPairedProgram(long value) {
            pairedProgram.addToReceivedQueue(value);
        }

        public long getReceivedValue() {
            return receivedValues.remove();
        }

        public boolean isWaiting() {
            return receivedValues.isEmpty() && instructions.get(instructionPosition).startsWith("rcv");
        }

        public boolean isHalted() {
            return instructionPosition >= instructions.size();
        }

        public boolean canContinue() {
            return !isWaiting() && !isHalted();
        }

        public long getValue(String variable) {
            char location = variable.charAt(0);
            if (Character.isAlphabetic(location)) {
                return registers[getRegisterPos(location)];
            }
            return Long.parseLong(variable);
        }

        public int getSentMessages() {
            return sentMessages;
        }
    }
}
