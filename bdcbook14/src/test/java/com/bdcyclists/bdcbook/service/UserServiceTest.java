package com.bdcyclists.bdcbook.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bdcyclists.bdcbook.BdcbookApplication;
import com.bdcyclists.bdcbook.domain.User;
import com.bdcyclists.bdcbook.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BdcbookApplication.class)
@WebAppConfiguration
public class UserServiceTest {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void findByEmailTest() {
		
		User user1 = new User(1L, "test1@livingoncodes.com", "TestFirst1", "TestLast1", "pass1");
		User user2 = new User(2L, "test2@livingoncodes.com", "TestFirst2", "TestLast2", "pass2");
		User user3 = new User(3L, "test3@livingoncodes.com", "TestFirst3", "TestLast3", "pass3");
		User user4 = new User(4L, "test4@livingoncodes.com", "TestFirst4", "TestLast4", "pass4");

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		
		System.out.println(userService.findByEmail("test2@livingoncodes.com"));

		
	}
}
