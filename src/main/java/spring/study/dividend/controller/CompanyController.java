package spring.study.dividend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @GetMapping("/autocomplete")
    public ResponseEntity<?> autocomplete(@PathVariable String keyword) {
        return null;
    }

    @GetMapping
    public ResponseEntity<?> searchCompany() {
        return null;
    }

    @PostMapping
    public ResponseEntity<?> addCompany() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCompany() {
        return null;
    }
}
