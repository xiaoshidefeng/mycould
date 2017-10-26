package com.example.mycould;

import com.example.mycould.util.FoldOperate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MycouldApplicationTests {

	@Test
	public void contextLoads() {

		FoldOperate foldOperate = new FoldOperate();
		foldOperate.traverseFolder2("H:\\动漫");
	}

}
