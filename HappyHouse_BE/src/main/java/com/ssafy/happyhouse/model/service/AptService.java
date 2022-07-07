package com.ssafy.happyhouse.model.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DijkstraData;
import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.mapper.HouseMapper;

@Service
public class AptService {
	
	@Autowired
	private HouseMapper hmapper;
	
	//아파트 정보 -> 중복없는 아파트 고유 정보
	public List<HouseInfo> houseInfo(String dongCode){
		return hmapper.houseInfo(dongCode);
	}
	
	//아파트 이름으로 검색
	public List<HouseInfo> houseInfo(String aptName, String dongCode){
		aptName = "%"+aptName+"%";
		return hmapper.searchHouse(aptName, dongCode);
	}
	
	public List<HouseInfo> allInfo(String aptName){
		aptName = "%"+aptName+"%";
		return hmapper.allInfo(aptName);
	}
	
	public List<HouseDeal> dealList(long aptCode){
		return hmapper.dealList(aptCode);
	}
	
	public int[] dijkstra(DijkstraData[] dijkstraData, int N) {
		List<Edge>[] adjList = new LinkedList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < dijkstraData.length; i++) {
			int u = dijkstraData[i].getFrom();
			int v = dijkstraData[i].getTo();
			double time = dijkstraData[i].getTime();
			adjList[u].add(new Edge(v, time));
			adjList[v].add(new Edge(u, time));
		}
		
		int[] shortPath = new int[N];
		double[] cost = new double[N];
		boolean[] visit = new boolean[N];
		Arrays.fill(cost, Integer.MAX_VALUE);

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		cost[N-1] = 0.0;
		pq.offer(new Edge(N - 1, 0.0));
		int index = 0;
		shortPath[index++] = N-1;
		System.out.println("check1");
		visit[N-1] = true;
		
		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			for (Edge e : adjList[current.v]) {
				System.out.println(e.v + " " + current.v);
				if (!visit[e.v] && cost[e.v] > cost[current.v] + e.time) {
					cost[e.v] = cost[current.v] + e.time;
					pq.offer(new Edge(e.v, cost[e.v]));
					shortPath[index++] = e.v;
					visit[e.v] = true;
				}
			}
		}
		System.out.println(Arrays.toString(cost));
		System.out.println(Arrays.toString(shortPath));
		return shortPath;
	}

	public static class Edge implements Comparable<Edge> {
		int v;
		double time;

		public Edge(int v, double t) {
			this.v = v;
			this.time = t;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.time, o.time);
		}
	}

}
