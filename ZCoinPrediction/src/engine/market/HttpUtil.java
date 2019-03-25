package engine.market;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

public class HttpUtil {
	private static Gson gson = new Gson();
	
	private static Logger logger = LogManager.getLogger(HttpUtil.class);
	
	private static final String IPV4_PATTERN ="(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
	
	private static List<RequestConfig> configList = new ArrayList<RequestConfig>();
	
	private static Map<String, Integer> identifierMap = new HashMap<String, Integer>();
	
	private static int configSize = 0;
	
	private static final int TIMEOUT = 5000;
	
	static {
		try {
			java.util.Enumeration<NetworkInterface> enuIfs = NetworkInterface.getNetworkInterfaces();
			if (null != enuIfs)
			{
				while (enuIfs.hasMoreElements()) 
				{
					NetworkInterface ni = (NetworkInterface)enuIfs.nextElement();
					java.util.Enumeration<InetAddress> enuAddrs = ni.getInetAddresses();
					if (ni.getName() == null || ni.getName().indexOf("eth") < 0) {
						continue;
					}
					while (enuAddrs.hasMoreElements()) 
					{
						InetAddress in4 = (InetAddress)enuAddrs.nextElement();
						
						if (in4.getHostAddress() != null && in4.getHostAddress().matches(IPV4_PATTERN)) {
							logger.info("IP(" + configSize + ") : " + in4.getHostAddress());
							configList.add(RequestConfig.custom()
									.setConnectionRequestTimeout(TIMEOUT)
									.setConnectTimeout(TIMEOUT)
									.setSocketTimeout(TIMEOUT)
									.setLocalAddress(in4).build());
							configSize++;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String httpGet(String identifier, String uri) {
		return httpGet(identifier, uri, null);
	}
	
	public static String httpGet(String identifier, String uri, Map<String, String> header) {
		String result = "";
		StringBuilder sb = new StringBuilder();
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
			Integer configIndex = getRequestConfigIndex(identifier);
					
			HttpGet httpGet = new HttpGet(uri);
			if (configList != null & configList.size() > 0) {
				httpGet.setConfig(configList.get(configIndex));
			}

			// set headers
			if (header != null && header.size() > 0) {
				for (String key : header.keySet()) {
					httpGet.addHeader(key, header.get(key));
				}
			}
			
			httpClient = HttpClients.createDefault();
			response = httpClient.execute(httpGet);
			
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				logger.info("Http get request succeeded with config : " + configIndex + " [" + identifier + "]" + uri);
				result = sb.toString();
			} else {
				logger.error("Http get request failed with config : " + configIndex + " [" + identifier + "]" + uri);
				logger.error("Http ResponseCode " + response.getStatusLine().getStatusCode()  + " Http Content : " + sb.toString());
			}
				
			
			

		} catch (SocketTimeoutException e) {
			logger.error("Http get request Timeout! [" + identifier + "]" + uri);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static String httpPost(String identifier, String uri, Map<String, String> header, Map<String, String> param, ContentType contentType) {

		String result = "";
		StringBuilder sb = new StringBuilder();
		
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		
		try {
			Integer configIndex = getRequestConfigIndex(identifier);
					
			HttpPost httpPost = new HttpPost(uri);
			if (configList != null & configList.size() > 0) {
				httpPost.setConfig(configList.get(configIndex));
			}

			httpClient = HttpClients.createDefault();
			
			// set headers
			httpPost.addHeader(HttpHeaders.CONTENT_TYPE, contentType.toString());
			if (header != null && header.size() > 0) {
				for (String key : header.keySet()) {
					httpPost.addHeader(key, header.get(key));
				}
			}
			
			// set parameters
			if (param != null && param.size() > 0) {
				if (ContentType.APPLICATION_FORM_URLENCODED.equals(contentType)) {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					for (String key : param.keySet()) {
						params.add(new BasicNameValuePair(key, param.get(key)));
					}
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				} else if (ContentType.APPLICATION_JSON.equals(contentType)) {
					String jsonString = gson.toJson(param);
					httpPost.setEntity(new StringEntity(jsonString));
				}
			}
			
			response = httpClient.execute(httpPost);
			
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent(), StandardCharsets.UTF_8));

			String line = "";
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				logger.info("Http post request succeeded with config : " + configIndex + " [" + identifier + "]" + uri);
				result = sb.toString();
			} else {				
				logger.error("Http post request failed with config : " + configIndex + " [" + identifier + "]" + uri);
				logger.error("Http ResponseCode " + response.getStatusLine().getStatusCode()  + " Http Content : " + sb.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	private static synchronized Integer getRequestConfigIndex(String identifier) {
		
		Integer configIndex = 0;

		if (identifierMap.containsKey(identifier)) {
			configIndex = identifierMap.get(identifier);
			configIndex++;
			if (configIndex >= configSize) {
				configIndex = 0;
			}
		}
		identifierMap.put(identifier, configIndex);
		return configIndex;
	}
	
	public static String mapToRequestString(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return "";
		}
		StringBuilder string = new StringBuilder();

		for (Entry<String, String> entry : map.entrySet()) {
			string.append(entry.getKey());
			string.append("=");
			string.append(entry.getValue());
			string.append("&");
		}

		String result = string.toString();
		return result.substring(0, result.length() - 1);
	}
}
