package com.kdk.app.common.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * <pre>
 * -----------------------------------
 * 개정이력
 * -----------------------------------
 * 2024. 6. 9. 김대광	최초작성
 * </pre>
 *
 *
 * @author 김대광
 */
public class RequestUtil {

	private RequestUtil() {
		super();
	}

	private static final String UNKNOW = "unknown";

	/**
	 * IP 주소 가져오기
	 * @param request
	 * @return
	 */
	public static String getRequestIpAddress(HttpServletRequest request) {
		String sIpAddr = request.getHeader("X-Forwarded-For");

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("Proxy-Client-IP");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("WL-Proxy-Client-IP");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("HTTP_CLIENT_IP");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("X-Real-IP");
        }

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("X-RealIP");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getHeader("REMOTE_ADDR");
		}

		if (sIpAddr == null || sIpAddr.length() == 0 || UNKNOW.equalsIgnoreCase(sIpAddr)) {
			sIpAddr = request.getRemoteAddr();
		}

		return sIpAddr;
	}

	/**
	 * 도메인 가져오기 (포트 있는 경우, 포트 포함)
	 * @param request
	 * @return
	 */
	public static String getRequestDomain(HttpServletRequest request) {
		String sReqUrl = request.getRequestURL().toString();
		String sServletPath = request.getServletPath();
		return sReqUrl.replace(sServletPath, "");
	}

}
