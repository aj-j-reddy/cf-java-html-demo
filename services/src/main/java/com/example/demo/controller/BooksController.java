package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// import com.sap.alicloud.hc.SpringBootHANADemo.service.NativeSQLRunner;

@RestController
public class BooksController {
    // @Autowired
    // NativeSQLRunner runner_native_sql;

    @RequestMapping("/")
    public String hello() {

        return "Hello!";
    }
// 
//     @RequestMapping("/test_native_sql")
//     public String test_native_sql() {
// 
//         runner_native_sql.startTest();
// 
//         return "Test Native SQL Started!";
//     }

}