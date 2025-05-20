

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelaz.addressbook.model.AddressModel;

@RestController
@RequestMapping("/addressbook")
public class AddressController {
	@GetMapping("/")
    public ResponseEntity<String> getAllContacts() {
        return ResponseEntity.ok("Getting all address book contacts");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getContactById(@PathVariable int id) {
        return ResponseEntity.ok("Getting contact with ID: " + id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createContact(@RequestBody AddressModel contact) {
        return ResponseEntity.ok("Created contact: " + contact.getName());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateContact(@PathVariable int id, @RequestBody AddressModel contact) {
        return ResponseEntity.ok("Updated contact with ID: " + id + ", Name: " + contact.getName());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable int id) {
        return ResponseEntity.ok("Deleted contact with ID: " + id);
    }

}
