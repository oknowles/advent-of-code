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

    public void runIterations(int numIterations, boolean removeCollisions) {
        for (int i = 0; i < numIterations; i++) {
            particles.forEach(Particle::increment);

            if (removeCollisions) {
                removeCollidingParticles();
            }
        }
    }

    public int getClosestParticle() {
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

    public int getRemainingParticles() {
        return particles.size();
    }

    private void removeCollidingParticles() {
        Map<String, Particle> particlePositionsMap = new HashMap<>();
        Set<Integer> particleIdsToRemove = new HashSet<>();
        for (Particle particle : particles) {
            String positionKey = getPositionKey(particle);
            Particle collidingParticle = particlePositionsMap.get(positionKey);
            if (collidingParticle != null) {
                particleIdsToRemove.add(particle.getId());
                particleIdsToRemove.add(collidingParticle.getId());
            } else {
                particlePositionsMap.put(positionKey, particle);
            }
        }

        particles.removeIf(particle -> particleIdsToRemove.contains(particle.getId()));
    }

    private String getPositionKey(Particle particle) {
        long[] position = particle.getPosition();
        return position[0] + "-" + position[1] + "-" + position[2];
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

        public long[] getPosition() {
            return position;
        }

        public long getDistanceFromOrigin() {
            long distance = 0;
            for (Long dimensionDistance : position) {
                distance += Math.abs(dimensionDistance);
            }
            return distance;
        }

        public void increment() {
            for (int dimension = 0; dimension < 3; dimension++) {
                velocity[dimension] += acceleration[dimension];
                position[dimension] += velocity[dimension];
            }
        }
    }
}
