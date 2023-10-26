package com.skyblue.qualificationservice.services.impl;

import com.skyblue.qualificationservice.entities.Qualification;
import com.skyblue.qualificationservice.repository.QualificationRepository;
import com.skyblue.qualificationservice.services.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QualificationServiceImpl implements QualificationService {
    private final QualificationRepository qualifierRepository;

    public QualificationServiceImpl(QualificationRepository qualifierRepository) {
        this.qualifierRepository = qualifierRepository;
    }

    @Override
    public Qualification creaateQualification(Qualification qualifier) {
        return this.qualifierRepository.save(qualifier);
    }

    @Override
    public List<Qualification> getQualification() {
        return this.qualifierRepository.findAll();
    }

    @Override
    public List<Qualification> getQualificationByUserId(String userId) {
        return this.qualifierRepository.findByUserId(userId);
    }

    @Override
    public List<Qualification> getQualificationByHotelId(String hotelId) {
        return this.qualifierRepository.findByHotelId(hotelId);
    }
}
