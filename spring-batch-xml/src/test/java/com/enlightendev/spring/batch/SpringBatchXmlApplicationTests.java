package com.enlightendev.spring.batch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBatchXmlApplication.class)
@ContextConfiguration()
public class SpringBatchXmlApplicationTests {

	@Test
	public void contextLoads() {
	}

}
