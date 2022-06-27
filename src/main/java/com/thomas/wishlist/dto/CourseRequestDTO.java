package com.thomas.wishlist.dto;

import lombok.Data;

@Data
public class CourseRequestDTO {
    public Integer technologyId;
    public String name;
    public double completionPercentage;
}
