package com.skyblue.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {
    private String qualificationId;
    private String userId;
    private String hotelId;
    private Integer qualification;
    private String observation;
    private Hotel hotel;

}
