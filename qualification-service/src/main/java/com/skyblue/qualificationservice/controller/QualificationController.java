package com.skyblue.qualificationservice.controller;

import com.skyblue.qualificationservice.entities.Qualification;
import com.skyblue.qualificationservice.repository.QualificationRepository;
import com.skyblue.qualificationservice.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    @PostMapping
    public ResponseEntity<Qualification> saveQualification(@RequestBody Qualification qualification){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.qualificationService.creaateQualification(qualification));
    }
    @GetMapping
    public  ResponseEntity<List<Qualification>> listQualifications(){
        return ResponseEntity.status(HttpStatus.OK).body(this.qualificationService.getQualification());
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Qualification>>listQualificationsForUserId(@PathVariable String userId){
        return  ResponseEntity.status(HttpStatus.OK).body(this.qualificationService.getQualificationByUserId(userId));
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Qualification>>listQualificationsForHotelId(@PathVariable String hotelId){
        return  ResponseEntity.status(HttpStatus.OK).body(this.qualificationService.getQualificationByHotelId(hotelId));
    }

}
