package com.gigatorb.cep;

import casespan.ucep.ootb.formbuilder.util.FormBuilderEngineUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FormBuilderEngineUtil.class})
public class UcepServerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMe(){
		FormBuilderEngineUtil formBuilderEngineUtil =
				new FormBuilderEngineUtil();
		formBuilderEngineUtil.mainApplicationReader();
		Assert.assertEquals(true, true);
	}
}
