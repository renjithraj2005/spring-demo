package com.demo.elearn;

import com.demo.elearn.models.*;
import com.demo.elearn.models.enums.*;
import com.demo.elearn.repository.CourseRepository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseControllerUnitTest {

    private static final Logger log = LoggerFactory.getLogger(CourseControllerUnitTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CourseRepository courseRepo;

    private Course course;

    @BeforeEach
    public void setup(){

        // Creating a course named Business Intelligence with 1 subscription plan and 2 price components(1 for USA and 1 for India)
        Subscription subscription = new Subscription("Subscribe For 4 Months", 3, 5.0);

		PriceComponent taxComponentUS = new PriceComponent("US", PriceComponentType.TAX, ValueType.PERCENTAGE, 5.0);
		PriceComponent taxComponentIndia = new PriceComponent("IN", PriceComponentType.TAX, ValueType.PERCENTAGE, 10.0);
		
		Price coursePrice= new Price(700.00, Currency.INR);
		taxComponentUS.setPrice(coursePrice);
		taxComponentIndia.setPrice(coursePrice);
		coursePrice.getPriceComponent().add(taxComponentUS);
		coursePrice.getPriceComponent().add(taxComponentIndia);
		

		this.course = new Course("Business Intelligence", "A course on Business Intelligence", PriceStrategy.SUBSCRIPTION);
		coursePrice.setCourse(this.course);
		this.course.setPrice(coursePrice);

		subscription.setCourse(this.course);
        this.course.getSubscriptions().add(subscription);
        this.course.setUuid("244642db-a7bc-4f37-8d5a-67cb454ee957");
		this.course = courseRepo.save(this.course);


    }

    // Passing id of created course to the  price API. API returns the Course object and 200 status
    @Test
    @DisplayName("get course details with valid course id")
    @Order(1)
    public void getCourseWithValidId() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/course/price/"+this.course.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)
                    ).andExpect(status().isOk());


    }

    // Passing invalid id to the price API. API return 404 status
    @Test
    @DisplayName("get course details with invalid course id")
    @Order(2)
    public void getCourseWithInValidId() throws Exception {

        long id = 999L;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/course/price/"+id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.ALL)
                    ).andExpect(status().isNotFound());
    }
}
