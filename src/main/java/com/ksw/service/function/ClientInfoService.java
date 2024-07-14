package com.ksw.service.function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import java.util.*;

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
    public void saveClientInfoInSession(HttpServletRequest request, HttpSession session) {
        String clientIp = getClientIp(request);
        String userAgent = getClientAgent(request);

        session.setAttribute("clientIp", clientIp);
        session.setAttribute("userAgent", userAgent);
    }

    // 세션에 저장된 클라이언트 정보가 변경되었는지 확인하는 메소드
    public boolean isClientInfoChanged(HttpSession session, HttpServletRequest request) {
        String sessionClientIp = (String) session.getAttribute("clientIp");
        String sessionUserAgent = (String) session.getAttribute("userAgent");

        String currentClientIp = getClientIp(request);
        String currentUserAgent = getClientAgent(request);

        return !sessionClientIp.equals(currentClientIp) || !sessionUserAgent.equals(currentUserAgent);
    }
}

