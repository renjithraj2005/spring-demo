package com.demo.elearn.services.impl;

import java.util.Optional;

import com.demo.elearn.models.Course;
import com.demo.elearn.repository.CourseRepository;
import com.demo.elearn.services.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course getCourse(String courseUuid){

        Optional<Course> course = courseRepository.findByUuid(courseUuid);
        if(course.isPresent()){
            return course.get();
        }
        else{
            return null;
        }
    }
}