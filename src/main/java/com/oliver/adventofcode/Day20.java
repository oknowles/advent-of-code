package com.oliver.adventofcode;

import java.util.*;

public class Day20 {

    private List<Particle> particles;

    public Day20(List<String> input) {
        particles = new ArrayList<>(input.size());
        for (int i = 0; i < input.size(); i++) {
            String[] parts = input.get(i).split(", ");
            long[] position = getCoordinates(parts[0]);
            long[] velocity = getCoordinates(parts[1]);
            long[] acceleration = getCoordinates(parts[2]);
            particles.add(new Particle(i, position, velocity, acceleration));
        }
    }

    private long[] getCoordinates(String input) {
        String[] coordinateStrings = input
                .substring(3)
                .replace(">", "")
                .trim()
                .split(",");

        long[] result = new long[3];
        for (int dimension = 0; dimension < 3; dimension++) {
            result[dimension] = Long.parseLong(coordinateStrings[dimension]);
        }
        return result;
    }

    public int getClosestParticle() {
        for (int i = 0; i < 1_000; i++) {
            for (Particle particle : particles) {
                particle.increment();
            }
            removeCollidingParticles();
        }

        System.out.println(particles.size());

        int closestParticleId = -1;
        long closestParticleDistance = Long.MAX_VALUE;
        for (Particle particle : particles) {
            long particleDistance = particle.getDistanceFromOrigin();
            if (closestParticleDistance > particleDistance) {
                closestParticleDistance = particleDistance;
                closestParticleId = particle.getId();
            }
        }
        return closestParticleId;
    }

    private void removeCollidingParticles() {
        Set<Integer> particleIdsToRemove = new HashSet<>();
        for (int particleId1 = 0; particleId1 < particles.size(); particleId1++) {
            for (int particleId2 = particleId1 + 1; particleId2 < particles.size(); particleId2++) {
                if (particles.get(particleId1).samePosition(particles.get(particleId2))) {
                    particleIdsToRemove.add(particleId1);
                    particleIdsToRemove.add(particleId2);
                }
            }
        }

        particles.removeIf(particle -> particleIdsToRemove.contains(particle.getId()));
    }

    class Particle {
        private int id;
        private long[] position;
        private long[] velocity;
        private long[] acceleration;

        Particle(int id, long[] position, long[] velocity, long[] acceleration) {
            this.id = id;
            this.position = position;
            this.velocity = velocity;
            this.acceleration = acceleration;
        }

        public int getId(){
            return id;
        }

        public boolean samePosition(Particle particle) {
            for (int dimension = 0; dimension < 3; dimension++) {
                if (this.position[dimension] != particle.position[dimension]) {
                    return false;
                }
            }
            return true;
        }

        public long getDistanceFromOrigin() {
            long distance = 0;
            for (Long dimensionDistance : position) {
                distance += Math.abs(dimensionDistance);
            }
            return distance;
        }

        public void increment() {
            incrementVelocity();
            incrementPosition();
        }

        public void incrementVelocity() {
            for (int dimension = 0; dimension < 3; dimension++) {
                velocity[dimension] += acceleration[dimension];
            }
        }

        public void incrementPosition() {
            for (int dimension = 0; dimension < 3; dimension++) {
                position[dimension] += velocity[dimension];
            }
        }
    }
}
