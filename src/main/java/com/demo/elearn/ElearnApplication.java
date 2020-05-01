package com.demo.elearn;

import com.demo.elearn.models.Course;
import com.demo.elearn.models.Price;
import com.demo.elearn.models.PriceComponent;
import com.demo.elearn.models.Subscription;
import com.demo.elearn.models.enums.*;
import com.demo.elearn.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class ElearnApplication implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepo;


	public static void main(String[] args) {
		SpringApplication.run(ElearnApplication.class, args);
	}
	
	@Override
	@Transactional
	public void run(String...args){


		// Creating a course named Data Transactions with Spring with 2 subscription plans and 2 price components(2 for USA and 1 for India)
		Subscription subscription1 = new Subscription("Subscribe For 3 Months", 3, 2.0);
		Subscription subscription2 = new Subscription("Subscribe For 6 Months", 6, 5.0);

		PriceComponent taxComponentIndia = new PriceComponent("IN", PriceComponentType.TAX, ValueType.PERCENTAGE, 12);
		PriceComponent taxComponentUsa = new PriceComponent("US", PriceComponentType.TAX, ValueType.PERCENTAGE, 7.5);
		PriceComponent currencyConversionComponent = new PriceComponent("US", PriceComponentType.CURRENCY_CONVERSION, ValueType.PERCENTAGE, 2.0);

		Price springCoursePrice = new Price(1200.99, Currency.INR);
		taxComponentIndia.setPrice(springCoursePrice);
		taxComponentUsa.setPrice(springCoursePrice);
		currencyConversionComponent.setPrice(springCoursePrice);
		springCoursePrice.getPriceComponent().add(taxComponentIndia);
		springCoursePrice.getPriceComponent().add(taxComponentUsa);
		springCoursePrice.getPriceComponent().add(currencyConversionComponent);


		Course springCourse = new Course("Data Transactions with Spring", "This course will teach you how to implement both declarative and programmatic topics", PriceStrategy.SUBSCRIPTION);
		springCoursePrice.setCourse(springCourse);
		springCourse.setPrice(springCoursePrice);

		subscription1.setCourse(springCourse);
		springCourse.getSubscriptions().add(subscription1);
		subscription2.setCourse(springCourse);
		springCourse.getSubscriptions().add(subscription2);
		springCourse.setUuid("eb01803e-0a8c-4a10-bfce-4cabbea90944");
		courseRepo.save(springCourse);


		// Creating a course named Java Fundamentals with one time payment and 2 price components(both for India)
		PriceComponent otherPriceComponent = new PriceComponent("IN", PriceComponentType.OTHER, ValueType.VALUE, 50.0);
		Price javaCoursePrice = new Price(600.00, Currency.INR);
		otherPriceComponent.setPrice(javaCoursePrice);
		javaCoursePrice.getPriceComponent().add(otherPriceComponent);
		javaCoursePrice.getPriceComponent().add(taxComponentIndia);
		Course javaCourse = new Course("Java Fundamentals", "Java Fundamentals", PriceStrategy.ONETIME);
		javaCoursePrice.setCourse(javaCourse);
		javaCourse.setPrice(javaCoursePrice);
		javaCourse.setUuid("925feecc-a5e0-4dd9-a564-776dce799691");
		courseRepo.save(javaCourse);

		// Creating a free course named Agile
		Course freeCourse = new Course("Agile", "Agile methodology", PriceStrategy.FREE);
		freeCourse.setUuid("2e5501b7-cbcb-4334-a32e-cfe0cc910a69");
		courseRepo.save(freeCourse);
	}

}
