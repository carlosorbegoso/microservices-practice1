package com.skyblue.qualificationservice.services;

import com.skyblue.qualificationservice.entities.Qualification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QualificationService {
   Qualification creaateQualification(Qualification qualifier);
    List<Qualification> getQualification();
    List<Qualification> getQualificationByUserId(String userId);
    List<Qualification> getQualificationByHotelId(String hotelId);


}
