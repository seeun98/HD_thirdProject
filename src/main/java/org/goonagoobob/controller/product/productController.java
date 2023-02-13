/*********************************
 * @function : Product Controller 
 * @author : 조일우
 * @Date : Feb 6. 2023
 * 상품 상세 Controller 추가 Feb 6. 2023
 * 상품 컬러 변경 기능 추가 Feb 7. 2023
 * 상품 카테고리 리스트 호출 Feb 10. 2023
 *********************************/

package org.goonagoobob.controller.product;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.goonagoobob.domain.product.FileDto;
import org.goonagoobob.domain.product.brandVO;
import org.goonagoobob.domain.product.depth1VO;
import org.goonagoobob.domain.product.productColorVO;
import org.goonagoobob.domain.product.productCommonVO;
import org.goonagoobob.domain.product.reviewVO;
import org.goonagoobob.service.product.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/product")
public class productController {
	@Autowired
	private productService service;
	@GetMapping("/productList")
	public void ProductList(@RequestParam(value = "brand", required=false) String brand,
			@RequestParam(value = "depth1", required=false) String depth1,
			@RequestParam(value = "depth2", required=false) String depth2,
			@RequestParam(value = "depth3", required=false) String depth3,
			@RequestParam(value = "Piter", required=false, defaultValue = "1") int Piter,
			@RequestParam(value = "productNum", required=false, defaultValue = "8") int productNum,
			@RequestParam(value = "orderBy", required=false, defaultValue = "0") int orderBy,
			Model model
			) {
		String depth0 ="";
		if (brand == "") {
			brand =null;
		}
		if (depth1 == "") {
			depth1 =null;
		}
		if (depth2 == "") {
			depth2 =null;
		}if (depth3 == "") {
			depth3 =null;
		}
		if (brand != null) {
			
			if(depth1 != null) {
				if(depth2 != null) {
						depth0 = depth2;
						System.out.println("depth2 :"+ depth2 );
				}
				else {
					depth0 = depth1;
					System.out.println("depth1 :"+ depth1 );
				}
				
			}
			else {
				depth0 = brand;
				System.out.println("brand :"+ brand );
			}
		}
		else {
				if(depth2 == null) {
					if(depth3 == null) {
						depth0 = depth2;
					}
					else {
						depth0 = depth1;
						
					}
				}
				else {
						depth0 = depth1;
				}
		}

		List<productCommonVO> VOList = service.getList(brand, depth1, depth2, depth3, orderBy, Piter, productNum);
		List<String> ctgr = service.getCtgrList(brand, depth1, depth2, depth3);
		int count = service.getCount(brand, depth1, depth2, depth3);
		System.out.println(VOList);
		System.out.println("depth0 : "+depth0);
		model.addAttribute("brand",brand);
		model.addAttribute("depth0",depth0);
		model.addAttribute("depth1",depth1);
		model.addAttribute("depth2",depth2);
		model.addAttribute("depth3",depth3);
		model.addAttribute("orderBy",orderBy);
		model.addAttribute("Piter",Piter);
		model.addAttribute("productNum",productNum);
		model.addAttribute("productVO", VOList);
		model.addAttribute("ctgrList", ctgr);
		model.addAttribute("count", count);
		if(brand == null) {
			model.addAttribute("depth",depth1);
		} else  {
			model.addAttribute("depth",brand);
		}
	
	}
	@GetMapping("/productListMore")
	@ResponseBody
	public List<productCommonVO> ProductListMore(@RequestParam(value = "brand", required=false) String brand,
			@RequestParam(value = "depth1", required=false) String depth1,
			@RequestParam(value = "depth2", required=false) String depth2,
			@RequestParam(value = "depth3", required=false) String depth3,
			@RequestParam(value = "Piter", required=false, defaultValue = "1") int Piter,
			@RequestParam(value = "productNum", required=false, defaultValue = "8") int productNum,
			@RequestParam(value = "orderBy", required=false, defaultValue = "0") int orderBy
			) {
		if (brand == "") {
			brand =null;
		}
		if (depth1 == "") {
			depth1 =null;
		}
		if (depth2 == "") {
			depth2 =null;
		}if (depth3 == "") {
			depth3 =null;
		}
		List<productCommonVO> VOList = service.getList(brand, depth1, depth2, depth3, orderBy, Piter, productNum);
		System.out.println(VOList);
		return VOList;
	}
	@GetMapping("/productDetail")
	public void Productdetail(@RequestParam(value = "pid", required=false) String pid,
			@RequestParam(value = "pcid", required=false) String pcid,Model model ,Principal principal) {
		productCommonVO vo = service.getPDetail(pid);
		List<reviewVO> reviewvoList = service.getReview(pid);
		System.out.println(vo);
		System.out.println(reviewvoList);
		model.addAttribute("productVO", vo);
		model.addAttribute("reviewVO", reviewvoList);
		model.addAttribute("pcid", pcid);
		System.out.println(principal);
	}
	
	@GetMapping("/colorChg")
	@ResponseBody
	public productColorVO colorChg(@RequestParam(value = "pcid", required=false) String pcid) {
		System.out.println("ajax in");
		productColorVO vo = service.getPColor(pcid);
		System.out.println(vo);
		return vo;
	}
	
	@GetMapping("/getBrdCtgr")
	@ResponseBody
	public List<brandVO> getBrdCtgr() {
		List<brandVO> vo = service.getBrd();
		return vo;
	}
	
	@GetMapping("/getCtgr")
	@ResponseBody
	public List<depth1VO> getCtgr() {
		List<depth1VO> vo = service.getCtgr();
		System.out.println(vo);
		return vo;
	}
	@GetMapping("/main1")
	public void getdsdsCtgr(Model model) {
		List<productCommonVO> newVO = service.getNewList();
		List<productCommonVO>	bestVO = service.getBestList();
		int newCount = service.newCount();
		System.out.println(newVO);
		System.out.println(bestVO);
		model.addAttribute("bestVO",bestVO);
		model.addAttribute("newVO",newVO);
		model.addAttribute("newCount",newCount);
	}
	@PostMapping("/reviewAdd")
	public void reviewAdd(@RequestParam("reviewFile") MultipartFile uploadfile,/* @RequestParam("reviewFile1") MultipartFile uploadfile1,
			@RequestParam("reviewFile2") MultipartFile uploadfile2, @RequestParam("reviewFile3") MultipartFile uploadfile3,
			@RequestParam("reviewFile4") MultipartFile uploadfile4, @RequestParam("reviewFile5") MultipartFile uploadfile5
			@RequestParam("reviewFile") MultipartFile uploadfile,,*/
			Principal principal, @RequestParam(value="age", required=false, defaultValue="미 기입") String age, 
			@RequestParam("height") String height,
			@RequestParam("bodyType") String bodyType,
			@RequestParam("enjoySize") String enjoySize,
			@RequestParam("rating") int rating,
			@RequestParam("realWearSize1") String realWearSize1,
			@RequestParam("realWearSize2") String realWearSize2,
			@RequestParam("realWearSize3") String realWearSize3,
			@RequestParam("realProductColor") String realProductColor,
			@RequestParam("headline") String headline,
			@RequestParam("psid") String psid
			
			
			) {
			reviewVO vo = new reviewVO(-100,principal.getName(),headline,"",psid,age,height,bodyType,
					enjoySize,rating,realWearSize1,realWearSize2,realWearSize3,realProductColor);
            FileDto dto = new FileDto(UUID.randomUUID().toString(),
            		"/Users/choilwoo/Desktop/dev64/workspace_goo/HD_thirdProject/src/main/resources/static/product/img"+uploadfile.getName(),
                    uploadfile.getOriginalFilename(), 
                    uploadfile.getContentType());
			File newFileName = new File(dto.getFILENAME());
	        System.out.println(UUID.randomUUID().toString());
			try {
				uploadfile.transferTo(newFileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			service.insertReview(vo,dto);
	}
		
}
