package com.springBootwithMongo.demo;


import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class KafkaConsumer {
    private final EmployeeService employeeService;

    private final KafkaProducer kafkaProducer;


    @Autowired
    public KafkaConsumer(EmployeeService employeeService, KafkaProducer kafkaProducer) {
        this.employeeService = employeeService;
        this.kafkaProducer = kafkaProducer;
    }


    @KafkaListener(topics = "kafkatopic",containerFactory = "employeeKafkaListenerFactory")
    public void consumeJson(@Payload  @Valid  CreateEmployeeRequest createEmployeeRequest) {
        log.debug("Received request to create employee in kafka {}",createEmployeeRequest);
        employeeService.newEmployee(createEmployeeRequest);
        kafkaProducer.sendMessage("Employee Stored in database");
    }


}
