package com.tp.webtools.transaps.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.repository.AppRepository;
import com.tp.webtools.transaps.service.AppService;
import com.tp.webtools.transaps.service.AppServiceImpl;

@RunWith(SpringRunner.class)
public class AppServiceImplIntegrationTest {
	
	@TestConfiguration
	static class AppServiceImplTestContextConfiguration {
		@Bean
		public AppService appService() {
			return new AppServiceImpl();
		}
	}
	
	@Autowired
	private AppService appService;
	
	@MockBean
	private AppRepository appRepository;
	
	@Before
	public void setUp() {
	    App app = new App();
	    app.setTitle("transaps");
	 
	    Mockito.when(appRepository.findAppByTitle(app.getTitle()))
	      .thenReturn(app);
	}
	
	@Test
	public void whenValidTitle_thenAppShouldBeFound() {
	    String title = "transaps";
	    App app = appService.findByTitle(title);
	  
	    assertThat(app.getTitle(),equalTo(title));
	 }

}
