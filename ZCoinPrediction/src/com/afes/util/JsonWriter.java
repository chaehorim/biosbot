package com.afes.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonWriter {
	public static void writeJson(HttpServletResponse response, Object data) {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        
        Gson gson = new Gson();
        String resp = gson.toJson(data);
        System.out.println(resp);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				out.flush();
				out.close();
			}
		}
		
	}
}
