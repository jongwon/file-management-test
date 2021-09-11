package com.sp.api.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
  @GetMapping("/")
  String foo() {
    return "bar";
  }
}
