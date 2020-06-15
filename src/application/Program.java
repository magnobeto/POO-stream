
package application;

import entities.Employee;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Roberto
 */
public class Program {
    
    public static void main(String[] args){
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter full file path: ");
        String path = sc.nextLine();
        
        try(BufferedReader br = new BufferedReader(new FileReader(path))){
            
            List<Employee> list = new ArrayList<>();
            String line = br.readLine();
            
            while(line != null){
                String[] fields = line.split(",");
                Employee emp = new Employee(fields[0],fields[1],Double.parseDouble(fields[2]));
                list.add(emp);
                line = br.readLine();
            }
            
            System.out.print("Enter salary: ");
            double salary = sc.nextDouble();
            
            List<String> emails = list.stream().filter(x -> x.getSalary() >= salary).map(x -> x.getEmail()).sorted().collect(Collectors.toList());
            System.out.printf("Email of people whose salary is more than %.2f:\n",salary);
            emails.forEach(System.out::println);
            
            double sum = list.stream().filter(x -> x.getName().toUpperCase().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x,y) -> x+y);
            System.out.printf("Sum of salary of people whose name starts with 'M': %.2f\n", sum);
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
              
        sc.close();
    }
}
