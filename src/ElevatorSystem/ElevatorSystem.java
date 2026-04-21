package ElevatorSystem;

import java.util.*;

public class ElevatorSystem {

    // 🔷 ENUMS
    enum Direction { UP, DOWN, IDLE }

    // 🔷 LOGGER
    static class Logger {
        public static void log(String message) {
            System.out.println("[" + System.currentTimeMillis() + "] " + message);
        }
    }

    // 🔷 CLOCK
    static class Clock {
        private long time = 0;

        public void tick() {
            time++;
        }

        public long getTime() {
            return time;
        }
    }

    // 🔷 PASSENGER
    static class Passenger {
        int sourceFloor;
        int destinationFloor;

        public Passenger(int source, int dest) {
            this.sourceFloor = source;
            this.destinationFloor = dest;
        }
    }

    // 🔷 BUTTON (ABSTRACTION)
    static abstract class Button {
        boolean isPressed;
    }

    static class CallButton extends Button {
        Direction direction;

        public CallButton(Direction direction) {
            this.direction = direction;
        }
    }

    static class FloorButton extends Button {
        int floor;

        public FloorButton(int floor) {
            this.floor = floor;
        }
    }

    static class EmergencyButton extends Button {}

    // 🔷 DOOR
    static class Door {
        boolean isOpen;

        public void open() {
            isOpen = true;
            Logger.log("Door opened");
        }

        public void close() {
            isOpen = false;
            Logger.log("Door closed");
        }
    }

    // 🔷 INDICATOR
    static class Indicator {
        int currentFloor;
        Direction direction;

        public void update(int floor, Direction dir) {
            this.currentFloor = floor;
            this.direction = dir;
        }
    }

    // 🔷 CONTROL PANEL
    static class ControlPanel {
        List<FloorButton> floorButtons = new ArrayList<>();
        Button openButton = new Button() {};
        Button closeButton = new Button() {};
        EmergencyButton emergencyButton = new EmergencyButton();

        public ControlPanel(int totalFloors) {
            for (int i = 1; i <= totalFloors; i++) {
                floorButtons.add(new FloorButton(i));
            }
        }
    }

    // 🔷 ELEVATOR
    static class Elevator {
        int id;
        int currentFloor = 1;
        Direction direction = Direction.IDLE;
        int capacity = 6;

        List<Passenger> passengers = new ArrayList<>();
        Queue<Integer> targets = new LinkedList<>();

        Door door = new Door();
        Indicator indicator = new Indicator();
        ControlPanel panel = new ControlPanel(12);

        public Elevator(int id) {
            this.id = id;
        }

        public void addTarget(int floor) {
            targets.add(floor);
        }

        public void step() {
            if (targets.isEmpty()) {
                direction = Direction.IDLE;
                return;
            }

            int target = targets.peek();

            if (currentFloor < target) {
                currentFloor++;
                direction = Direction.UP;
            } else if (currentFloor > target) {
                currentFloor--;
                direction = Direction.DOWN;
            } else {
                arrive();
                targets.poll();
            }

            indicator.update(currentFloor, direction);
            Logger.log("Elevator " + id + " at floor " + currentFloor);
        }

        private void arrive() {
            Logger.log("Elevator " + id + " arrived at floor " + currentFloor);
            door.open();
            door.close();
        }
    }

    // 🔷 FLOOR
    static class Floor {
        int number;
        CallButton upButton = new CallButton(Direction.UP);
        CallButton downButton = new CallButton(Direction.DOWN);

        public Floor(int number) {
            this.number = number;
        }
    }

    // 🔷 CONTROLLER (BRAIN)
    static class ElevatorController {
        List<Elevator> elevators;

        public ElevatorController(List<Elevator> elevators) {
            this.elevators = elevators;
        }

        public Elevator assignElevator(int floor) {
            Elevator best = elevators.get(0);

            for (Elevator e : elevators) {
                if (Math.abs(e.currentFloor - floor) <
                        Math.abs(best.currentFloor - floor)) {
                    best = e;
                }
            }

            Logger.log("Assigned Elevator " + best.id + " to floor " + floor);
            best.addTarget(floor);
            return best;
        }
    }

    // 🔷 BUILDING
    static class Building {
        List<Floor> floors = new ArrayList<>();
        List<Elevator> elevators = new ArrayList<>();
        ElevatorController controller;

        public Building(int floorCount, int elevatorCount) {
            for (int i = 1; i <= floorCount; i++) {
                floors.add(new Floor(i));
            }

            for (int i = 1; i <= elevatorCount; i++) {
                elevators.add(new Elevator(i));
            }

            controller = new ElevatorController(elevators);
        }
    }

    // 🔷 RANDOM GENERATOR
    static class RandomGenerator {
        Random random = new Random();

        public Passenger generatePassenger(int maxFloor) {
            int source = random.nextInt(maxFloor) + 1;
            int dest;
            do {
                dest = random.nextInt(maxFloor) + 1;
            } while (dest == source);

            return new Passenger(source, dest);
        }
    }

    // 🔷 MAIN SIMULATION
    public static void main(String[] args) {

        Building building = new Building(12, 5);
        Clock clock = new Clock();
        RandomGenerator generator = new RandomGenerator();

        for (int t = 0; t < 20; t++) {

            clock.tick();

            // Yeni yolcu oluştur
            Passenger p = generator.generatePassenger(12);
            Logger.log("Passenger created from " + p.sourceFloor + " to " + p.destinationFloor);

            // Asansör çağır
            Elevator e = building.controller.assignElevator(p.sourceFloor);
            e.addTarget(p.destinationFloor);

            // Tüm asansörleri ilerlet
            for (Elevator elevator : building.elevators) {
                elevator.step();
            }

            System.out.println("------------");
        }
    }
}