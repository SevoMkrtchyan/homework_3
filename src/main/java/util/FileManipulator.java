package util;

import model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileManipulator {

    private static final String FILES_DIR = "/home/sevo/IdeaProjects/homework_3/data/";
    private static final List<Customer> CUSTOMERS = new ArrayList<>();

    // TODO: 13.11.21 1. Receives a file as a command line parameter. 
    // TODO: 13.11.21 2. Reads all lines of a file into a Customer object
    public static void fileReader() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("write file name");
        String s = scanner.nextLine();
        Customer customer;
        try (Scanner fileScanner = new Scanner(new File(FILES_DIR + s))) {
            while (fileScanner.hasNext()) {
                String customerLine = fileScanner.nextLine();
                String[] fields = customerLine.split(", ");
                customer = new Customer(fields[0], fields[1], Integer.parseUnsignedInt(fields[2]));
                CUSTOMERS.add(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    // TODO: 13.11.21 3. Sorts by the score field
    // TODO: 13.11.21 4. The result of sorting is output to a new file.
    public static void sortCustomers() {
        List<Customer> collect = CUSTOMERS.stream().sorted(Customer::compareTo).collect(Collectors.toList());
        File file = new File(FILES_DIR + "sortedCustomers");
        try (OutputStream outputStream = new FileOutputStream(file)) {
            for (Customer customer : collect) {
                outputStream.write(customer.toString().getBytes());
                outputStream.write("\n".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
