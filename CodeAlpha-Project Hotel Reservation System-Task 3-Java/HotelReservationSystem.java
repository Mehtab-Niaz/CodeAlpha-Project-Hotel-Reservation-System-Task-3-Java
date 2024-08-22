import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Room {
    private String category;
    private double price;
    private boolean isAvailable;

    public Room(String category, double price) {
        this.category = category;
        this.price = price;
        this.isAvailable = true;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void reserve() {
        this.isAvailable = false;
    }

    public void release() {
        this.isAvailable = true;
    }
}

class Reservation {
    private String customerName;
    private Room room;
    private double payment;

    public Reservation(String customerName, Room room, double payment) {
        this.customerName = customerName;
        this.room = room;
        this.payment = payment;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Room getRoom() {
        return room;
    }

    public double getPayment() {
        return payment;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hotel Reservation System:");
            System.out.println("1. Search for Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                searchAvailableRooms();
            } else if (choice == 2) {
                makeReservation(scanner);
            } else if (choice == 3) {
                viewBookingDetails(scanner);
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void initializeRooms() {
        rooms.add(new Room("Single", 100.0));
        rooms.add(new Room("Double", 150.0));
        rooms.add(new Room("Suite", 250.0));
        rooms.add(new Room("Single", 100.0));
        rooms.add(new Room("Double", 150.0));
        rooms.add(new Room("Suite", 250.0));
    }

    private static void searchAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Category: " + room.getCategory() + ", Price: $" + room.getPrice());
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter room category (Single, Double, Suite): ");
        String category = scanner.nextLine();

        Room roomToReserve = null;
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory().equalsIgnoreCase(category)) {
                roomToReserve = room;
                break;
            }
        }

        if (roomToReserve != null) {
            System.out.print("Enter payment amount: $");
            double payment = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            if (payment >= roomToReserve.getPrice()) {
                roomToReserve.reserve();
                reservations.add(new Reservation(customerName, roomToReserve, payment));
                System.out.println("Reservation successful!");
            } else {
                System.out.println("Insufficient payment. Reservation failed.");
            }
        } else {
            System.out.println("No available rooms in this category.");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        boolean found = false;
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println("Booking Details:");
                System.out.println("Customer Name: " + reservation.getCustomerName());
                System.out.println("Room Category: " + reservation.getRoom().getCategory());
                System.out.println("Payment: $" + reservation.getPayment());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("No bookings found for " + customerName);
        }
    }
}
