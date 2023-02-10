package org.goonagoobob.mapper.product;
//<!--*********************************
//* @function : 상품 관련 Mapper Test
//* @author : 조일우
//* @Date : Feb 6. 2023
//* 상품 상세 Mapper test 추가 Feb 6. 2023
//* 상품 카테고리 리스트 Mapper test 추가 Feb 9. 2023
//* 상품 리스트 Mapper test 추가 Feb 10. 2023
//********************************* -->
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class productMapperTests {
	
	@Autowired
	private productMapper mapper;
	
	@Test
	public void productDetailTests() {
		System.out.println(mapper.getPDetail("TM2CAKOP661W"));
		
	}
	
	@Test
	public void productColorChgTests() {
		System.out.println(mapper.getPColor("TM2CAKOP661W"));
	}
	@Test
	public void getCategoryTests() {
		System.out.println(mapper.getBrd());
		System.out.println(mapper.getCtgr());
	}
	@Test
	public void getListTests() {
		System.out.println(mapper.getList("TIME", "여성", null, null, "order by pcprice", 1, 8));
		System.out.println(mapper.getList("TIME", "여성", null, null, "order by pcprice", 1, 8).size());
	
	}
}
