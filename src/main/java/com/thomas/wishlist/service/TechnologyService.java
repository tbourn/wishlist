package com.thomas.wishlist.service;

import com.thomas.wishlist.exception.TechnologyNotFoundException;
import com.thomas.wishlist.entity.Technology;

import java.util.List;

public interface TechnologyService {
    Technology createTechnology(Technology technology);

    Technology findByName(String name) throws TechnologyNotFoundException;

    Technology findById(Integer technologyId) throws TechnologyNotFoundException;

    Technology updateTechnology(Technology technology, Integer id) throws TechnologyNotFoundException;

    List<Technology> findAllTechnology();

    boolean deleteTechnologyById(Integer technologyId) throws TechnologyNotFoundException;

    boolean deleteTechnologyByName(String name) throws TechnologyNotFoundException;
}
