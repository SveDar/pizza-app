package com.pizza.pizza.cotrollers;


import com.pizza.pizza.entities.City;
import com.pizza.pizza.entities.Customer;
import com.pizza.pizza.entities.Badge;
import com.pizza.pizza.payload.response.BadgeOwnerResponse;
import com.pizza.pizza.repositories.CityRepository;
import com.pizza.pizza.repositories.CustomerRepository;
import com.pizza.pizza.repositories.BadgeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepo;
    private final BadgeRepository badgeRepo;
    private final CityRepository cityRepo;


    CustomerController(CustomerRepository customerRepository,BadgeRepository badgeRepository,CityRepository cityRepository){

        this.customerRepo = customerRepository;
        this.badgeRepo = badgeRepository;
        this.cityRepo=cityRepository;
    }

    @PostMapping("/badge/save")
    public ResponseEntity<?> saveNewBadge(Integer discount,String customerPhoneNumber){
        Customer customer = customerRepo.findCustomerByPhoneNumber(customerPhoneNumber)
                .orElse(null);
        if(customer == null) return ResponseEntity.ok("No such customer!");
        if(badgeRepo.existsById(customer.getId()))return ResponseEntity.ok("Badge is already present!");
        return ResponseEntity.ok(badgeRepo.save(new Badge("Golden",30,customer)));
    }
    @PostMapping("/badge/delete")
    public ResponseEntity<?> deleteBadge(String customerPhoneNumber){
        Customer customer = customerRepo.findCustomerByPhoneNumber(customerPhoneNumber)
                .orElse(null);
        if(customer == null)
            return ResponseEntity.ok("No such customer!");
        if(badgeRepo.existsById(customer.getId())){
            badgeRepo.delete(customer.getBadge());
            return ResponseEntity.ok("Badge was deleted!");
        }
        return ResponseEntity.ok("Not found badge associated with this customer!");
    }
    @GetMapping("/badge/all")
    private List<BadgeOwnerResponse> getAllBadges(){
        List<Badge> Badges =badgeRepo.findAll();
        List<BadgeOwnerResponse> badgeOwnerResponses = new ArrayList<>();
        for(Badge Badge:Badges){
            BadgeOwnerResponse bOR = new BadgeOwnerResponse();
            bOR.setCustomerName(Badge.getCustomer().getFirstName()+" "+Badge.getCustomer().getLastName());
            bOR.setCustomerPhoneNumber(Badge.getCustomer().getPhoneNumber());
            bOR.setBadgeName(Badge.getBadgeName());
            bOR.setDiscountPercentage((Badge.getDiscountPercentage()));
            badgeOwnerResponses.add(bOR);
        }
        return badgeOwnerResponses;
    }

    @GetMapping("/fetch")
    private List<Customer> getAllCustomers(){
        return customerRepo.findAll();
    }

    @GetMapping("/find/name")
    public ResponseEntity<?> findCustomerByName(String firstName,String lastName) {
        Optional<Customer> customer = customerRepo.findCustomerByFirstNameAndLastName(firstName, lastName);
        return customer.isPresent() ? ResponseEntity.ok(customer.get()) : ResponseEntity.ok("not found");
    }

    @GetMapping("/find/number")
    public ResponseEntity<?> findCustomerByPhoneNumber(String phoneNumber) {
        Optional<Customer> result = customerRepo.findCustomerByPhoneNumber(phoneNumber);
        return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.ok("not found");
    }
    @GetMapping("/filter/city")
    public Set<Customer> filterCustomersByCity(String city){
        return customerRepo.findCustomersByCity(city);
    }

    @PostMapping("/save")
    ResponseEntity<?> persistCustomer(String firstName, String lastName, String phoneNumber, String password, String address) {
        Customer customer = customerRepo.findCustomerByPhoneNumber(phoneNumber)
            .orElse(new Customer(firstName, lastName, phoneNumber, password, address));
        if (customer.getId() != null) {
        return ResponseEntity.ok("Customer already exists!If you want to update it please go to the update section!");}

        customerRepo.save(customer);
        return ResponseEntity.ok("New customer with telephone number:" + phoneNumber + " was saved!");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(String firstName, String lastName, String phoneNumber, String password, String address)
    {
        Customer customer =customerRepo.findCustomerByPhoneNumber(phoneNumber)
                .orElse(null);
        if(customer == null){return  ResponseEntity.ok("Customer with telephone number:"+phoneNumber+" wasn't found");}
        customer.setAddress(address);
        customer.setPassword(password);
        customerRepo.save(customer);
        return ResponseEntity.ok("Customer was updated!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCustomer(String phoneNumber){
        Optional<Customer> customer = customerRepo.findCustomerByPhoneNumber(phoneNumber);
        if(customer.isEmpty()) {
            return ResponseEntity.ok("customer not found");
        }

        customerRepo.delete(customer.get());
        return ResponseEntity.ok(customer.get().getFirstName()+" "+customer.get().getLastName()+" with number:"+phoneNumber+" was deleted!");
    }
}
