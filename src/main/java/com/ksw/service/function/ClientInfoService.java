package com.ksw.service.function;

import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class ClientInfoService {
	
	// 클라이언트의 ip를 얻어오는 메소드
    public String getClientIp(HttpServletRequest request) {
    	// ip 정보를 주로 담고있는 he	ader 이름들을 배열로 묶어둔다.
        String[] headerNames = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "X-Real-IP",
            "X-Cluster-Client-IP",
            "Forwarded-For",
            "Forwarded"
        };
        
        // 반환된 ip들을 저장할 리스트를 만든다.
        List<String> ipList = new ArrayList<>();

        // 헤더 이름들을 순환하면서, request객체를 통해 각 헤더에 기재된 ip들을 받는다.
        for (String header : headerNames) {
            String ipHeader = request.getHeader(header);
            // 받은 헤더가 null, 공백, unknown이 아닌 경우 아래의 반복문을 실행한다.
            if (isValidIp(ipHeader)) {
            	// 192.168.0.1, 192.168.0.2, ... 이런 식으로 넘어온 모든 ip들을 분리하고 배열에 저장한다.
                String[] ips = ipHeader.split(",");
                // 배열의 ip들을 순환시키며, 제대로 된 ip가 맞는 지 체크하고, 처음 만들었던 ipList(ip목록)에 넣는다.
                for (String ip : ips) {
                    ip = ip.trim();
                    if (isValidIp(ip)) {
                        ipList.add(ip);
                    }
                }
            }
        }

        // 만약 헤더들에 ip가 없었다면, RemoteAddr을 통해서 ip를 받아온다.
        if (ipList.isEmpty()) {
            return request.getRemoteAddr();
        }

        // ip목록이 비어있지 않다면, 가장 많이 반복된 ip를 반환한다.
        return getMostFrequentIp(ipList);
    }

    // 해당 ip가 멀쩡한 형태인 지 확인하고 반환한다.
    private boolean isValidIp(String ip) {
        return ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip);
    }

    // 가장 자주 발견되는 IP를반환한다.
    private String getMostFrequentIp(List<String> ipList) {
    	
    	// <ip주소, 등장횟수>로 이루어진 HashMap을 만든다.
        Map<String, Integer> ipCountMap = new HashMap<>();
        
        // ip 목록에 들어있는 각각의 ip를 돌며 조사한다.
        for (String ip : ipList) {
        	// ipCountMap에 해당 ip의 등장횟가 없으면 ipCountMap에 새로 1카운트로 추가하고, 아니라면+1한다.
            ipCountMap.put(ip, ipCountMap.getOrDefault(ip, 0) + 1);
        }

        // 기본값 설정
        Map.Entry<String, Integer> defaultEntry = new AbstractMap.SimpleEntry<>("None", 0);
        
        // 스트림 API를 사용하여 값이 가장 큰 엔트리 찾기
        Map.Entry<String, Integer> maxEntry = ipCountMap.entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .orElse(defaultEntry);
        
        // maxEntry의 키반 
        return maxEntry.getKey();
    }

    
    // 클라이언트의 User-Agent 정보를 얻어오는 메소드
    public String getClientAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    // 세션에 클라이언트 정보를 저장하는 메소드
    public void saveClientInfoInSession(
    		Integer noteNo, 
    		HttpServletRequest request, 
    		HttpSession session) {
        String clientIp = getClientIp(request);
        String userAgent = getClientAgent(request);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        HashMap<Integer, HashMap<String, String>> clientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos");
        if (clientInfos == null) {
        	clientInfos = new HashMap<>();
        }
        
        HashMap<String, String> clientInfo = clientInfos.getOrDefault(noteNo, new HashMap<>());
        
        if(clientInfo.get(noteNo) == null || clientInfo.get(noteNo).isEmpty()) {
        	clientInfo.put("clientIp", clientIp);
        	clientInfo.put("userAgent", userAgent);
        	clientInfo.put("currentTime", currentTime.toString());
        	
        	clientInfos.put(noteNo, clientInfo);
        }
        
        // 기록이 50개가 넘어가면 가장 오래된 기록 삭제
        if(clientInfos.size() > 50 ) {
            removeOldestRecord(clientInfos);
        }
        
        session.setAttribute("clientInfos", clientInfos);
    }
    
    // 세션에 클라이언트 정보를 저장하는 메소드
    public void saveCategoryViewHistoryClientInfoInSession(
    		Integer categoryNo, 
    		HttpServletRequest request, 
    		HttpSession session) {
        String clientIp = getClientIp(request);
        String userAgent = getClientAgent(request);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        HashMap<Integer, HashMap<String, String>> clientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos_category");
        if (clientInfos == null) {
        	clientInfos = new HashMap<>();
        }
        
        HashMap<String, String> clientInfo = clientInfos.getOrDefault(categoryNo, new HashMap<>());
        
        if(clientInfo.get(categoryNo) == null || clientInfo.get(categoryNo).isEmpty()) {
        	clientInfo.put("clientIp", clientIp);
        	clientInfo.put("userAgent", userAgent);
        	clientInfo.put("currentTime", currentTime.toString());
        	
        	clientInfos.put(categoryNo, clientInfo);
        }
        
        // 기록이 50개가 넘어가면 가장 오래된 기록 삭제
        if(clientInfos.size() > 50 ) {
            removeOldestRecord(clientInfos);
        }
        
        session.setAttribute("clientInfos_category", clientInfos);
    }

//    // 가장 오래된 기록을 삭제하는 메소드
//    private void removeOldestRecord(HashMap<Integer, HashMap<String, String>> clientInfos) {
//        Integer oldestKey = null;
//        Timestamp oldestTimestamp = null;
//        
//        //	clientInfos 조회 기록의 각각의 map들을 entrySet으로 묶음. 그리고 각 entry들을 돌면서 가장 오래된 기록을 찾음. 
//        for (HashMap.Entry<Integer, HashMap<String, String>> entry : clientInfos.entrySet()) {
//            Timestamp currentTimestamp = Timestamp.valueOf(entry.getValue().get("currentTime"));
//            if (oldestTimestamp == null || currentTimestamp.before(oldestTimestamp)) {
//                oldestTimestamp = currentTimestamp;
//                oldestKey = entry.getKey();
//            }
//        }
//
//        // 가장 오래된 기록이 null이 아니라면, 해당 기록을 지움
//        if (oldestKey != null) {
//            clientInfos.remove(oldestKey);
//        }
//    }
    
    // 가장 오래된 기록을 삭제하는 메소드 (Stream API 사용)
    private void removeOldestRecord(HashMap<Integer, HashMap<String, String>> clientInfos) {
    	// Optional :: 없으면 그냥 빈 객체를 넣어라 / 
    	// clientInfos의 요소들을 entrySet으로 각각 만들
        Optional<Map.Entry<Integer, HashMap<String, String>>> oldestEntry = 
        		clientInfos.entrySet()    	// clientInfos의 요소들을 entrySet으로 각각 만들고
        		.stream()					// 각 요소들을 돌면서
        		.min(Comparator.comparing(	// Comparator로 각 요소를 비교하는데
        				entry -> Timestamp.valueOf(entry.getValue().get("currentTime")))); // 각 요소의 현재 시간 값을 가져와서, Timestamp로 변환하고 비교한다.  

        oldestEntry.ifPresent(entry -> clientInfos.remove(entry.getKey())); // 그렇게 나온 oldestEntry를 clientInfos에서 지운다.	
    }
    
    // 세션에 해당 게시글을 본 기록이 있는 지 확인 후, 조회수를 올려도 될 지 말지 정해주는 메소드 -> false면 조회수 증가 O, true면 증가 X
    public boolean getSessionClientViewHistory(Integer noteNo, HttpSession session, HttpServletRequest request) {
    	HashMap<Integer, HashMap<String, String>> sessionClientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos");
    	
    	HashMap<String, String> sessionClientInfo = new HashMap<String, String>();
    	if (sessionClientInfos == null || sessionClientInfos.isEmpty()) {
    		return false;
    	}
    	
    	if (sessionClientInfos.get(noteNo) == null || sessionClientInfos.get(noteNo).isEmpty()) {
    		return false;
    	}
    	sessionClientInfo = sessionClientInfos.get(noteNo);
        
        String sessionClientIp = sessionClientInfo.get("clientIp");
        String sessionUserAgent = sessionClientInfo.get("userAgent");
        Timestamp sessionTime = Timestamp.valueOf(sessionClientInfo.get("currentTime")); 
        
        String currentClientIp = getClientIp(request);
        String currentUserAgent = getClientAgent(request);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis()); 

        return (
        		sessionClientIp.equals(currentClientIp) 
        		&& currentTime.getTime() - sessionTime.getTime() <= 5 * 60 * 1000);
    }
    
    // 세션에 해당 게시글을 본 기록이 있는 지 확인 후, 조회수를 올려도 될 지 말지 정해주는 메소드 -> false면 조회수 증가 O, true면 증가 X
    public boolean getSessionClientCategoryViewHistory(Integer categoryNo, HttpSession session, HttpServletRequest request) {
    	HashMap<Integer, HashMap<String, String>> sessionClientInfos = (HashMap<Integer, HashMap<String, String>>) session.getAttribute("clientInfos_category");
    	
    	HashMap<String, String> sessionClientInfo = new HashMap<String, String>();
    	if (sessionClientInfos == null || sessionClientInfos.isEmpty()) {
    		return false;
    	}
    	
    	if (sessionClientInfos.get(categoryNo) == null || sessionClientInfos.get(categoryNo).isEmpty()) {
    		return false;
    	}
    	sessionClientInfo = sessionClientInfos.get(categoryNo);
        
        String sessionClientIp = sessionClientInfo.get("clientIp");
        String sessionUserAgent = sessionClientInfo.get("userAgent");
        Timestamp sessionTime = Timestamp.valueOf(sessionClientInfo.get("currentTime")); 
        
        String currentClientIp = getClientIp(request);
        String currentUserAgent = getClientAgent(request);
        Timestamp currentTime = new Timestamp(System.currentTimeMillis()); 

        return (
        		sessionClientIp.equals(currentClientIp) 
        		&& currentTime.getTime() - sessionTime.getTime() <= 5 * 60 * 1000);
    }
}

