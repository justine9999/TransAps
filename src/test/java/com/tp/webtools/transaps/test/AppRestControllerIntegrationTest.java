package com.tp.webtools.transaps.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tp.webtools.transaps.controller.AppController;
import com.tp.webtools.transaps.model.App;
import com.tp.webtools.transaps.model.Tag;
import com.tp.webtools.transaps.service.AppService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class AppRestControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AppService appService;
	
	@Test
	public void givenApp_whenGetApps_thenReturnJsonArray() throws Exception {
		App app = new App();
		app.setTitle("transaps1");
		App app2 = new App();
		app2.setTitle("transaps2");
		String tags = "[]";
		String sort = "0";
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("tags", tags);
		map.add("sort", sort);
		
		List<App> apps = new ArrayList<App>();
		apps.add(app);
		apps.add(app2);
		given(appService.findAllApps(new Tag[]{}, 0)).willReturn(apps);
		
		mvc.perform(get("/api/app/")
				  .params(map)
			      .contentType(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(jsonPath("$", hasSize(2)))
			      .andExpect(jsonPath("$[0].title", is(app.getTitle())));
	}
}
