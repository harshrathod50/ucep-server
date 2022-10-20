package com.casespan.ucep;

import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.formbuilder.util.FormBuilderEngineUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FormBuilderEngineUtil.class})
public class UcepServerApplicationTests {
	//Ucep test
	@Test
	public void contextLoads() {
	}

	@Test
	public void testMe(){
		FormBuilderEngineUtil formBuilderEngineUtil =
				new FormBuilderEngineUtil();
		ApplicationKey appKey = new ApplicationKey();
		appKey.setApplicationName("MainApplication");
		formBuilderEngineUtil.loadApplicationSchema(appKey);
		Assert.assertEquals(true, true);
	}
}
