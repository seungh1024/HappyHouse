package com.ssafy.happyhouse.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ssafy.happyhouse.model.dto.DijkstraData;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.service.AptService;

@Controller
@CrossOrigin("*")
@RequestMapping("/apt")
public class AptController {
	
	@Autowired
	private AptService aservice;
	
	//실거래가 조회 페이지 응답
	@GetMapping("/info")
	public String deal() {
		return "deal/dealInfo";
	}
	
	//아파트 거래정보 -> 중복없는 아파트의 고유정보 리스트
	@GetMapping("/info/{dongCode}")
	@ResponseBody
	public ResponseEntity<?> houseInfo(@PathVariable("dongCode")String dongCode){
//		System.out.println(dongCode);
//		System.out.println(aservice.houseInfo(dongCode));
		return new ResponseEntity<List<HouseInfo>>(aservice.houseInfo(dongCode),HttpStatus.OK);
	}
	
	//아파트 이름으로 검색
	@GetMapping("/info/{dongCode}/{aptName}")
	@ResponseBody
	public ResponseEntity<?> searchHouse(@PathVariable("dongCode") String dongCode, @PathVariable("aptName")String aptName){
		System.out.println(aptName);
		System.out.println(aservice.houseInfo(aptName,dongCode));
		return new ResponseEntity<List<HouseInfo>>(aservice.houseInfo(aptName, dongCode),HttpStatus.OK);
	}
	
	@GetMapping("/search/allDeal/{aptName}")
	@ResponseBody
	public ResponseEntity<?> allInfo(@PathVariable("aptName") String aptName){
		System.out.println(aptName);
		System.out.println(aservice.allInfo(aptName));
		return new ResponseEntity<List<HouseInfo>>(aservice.allInfo(aptName),HttpStatus.OK);
		
	}
	
	@GetMapping("/deal/{aptCode}")
	@ResponseBody
	public ResponseEntity<?> dealInfo(@PathVariable("aptCode") long aptCode){
		return new ResponseEntity<List<HouseDeal>>(aservice.dealList(aptCode),HttpStatus.OK);
	}
	
	@PostMapping("/route")
	@ResponseBody
	public ResponseEntity<?> makeRoute(@RequestBody Map<String,Object> allData) {
		System.out.println("allData: "+allData);

		Gson gson = new Gson();
//		Map<String, Object[]> map = gson.fromJson((String) allData.get("dijkstraData"), Map.class);
//		System.out.println("dijkstra" + map.get("dijkstraData"));

//		String ds = gson.toJson(map.get("dijkstraData"), new TypeToken<ArrayList<DijkstraData>>() {
//		}.getType());
//		System.out.println(ds);
		DijkstraData[] dijkstraData = gson.fromJson((String) allData.get("dijkstraData"), DijkstraData[].class);
//		ArrayList<DijkstraData> dijkstraData = (ArrayList<DijkstraData>)allData.get("dijkstraData");
		System.out.println(dijkstraData);
		System.out.println(Arrays.toString(dijkstraData));
		for (DijkstraData d : dijkstraData) {
			System.out.println(d);
		}

//		System.out.println(map.get("size"));
//		String stringSize = gson.toJson(map.get("size"), new TypeToken<Double>() {
//		}.getType());
//		int size = gson.fromJson(stringSize, Integer.class);
		System.out.println("dijkstra Size: "+allData.get("size"));
		
		
		int size = ((Double)allData.get("size")).intValue();
		System.out.println(size);

		return new ResponseEntity<int[]>(aservice.dijkstra(dijkstraData, size), HttpStatus.OK);
//		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
}
