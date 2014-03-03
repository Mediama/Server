package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServletResult {
	@JsonProperty("result_code")
	public int result;
	
	
	public ServletResult(int result){
		this.result=result;
	}
	
	public static final void sendResult(HttpServletResponse response, int resultCode) throws JsonProcessingException, IOException{
		ObjectMapper oMap=new ObjectMapper();
		
		response.getWriter().append(
				oMap.writeValueAsString(new ServletResult(resultCode)));
		
		response.getWriter().close();
	}

	public static final int SUCCESS=1;
	public static final int ERROR=0;
	public static final int NOT_FOUND=50;
	public static final int MISSING_TITLE=100;
	public static final int MISSING_AUTHOR=101;
	public static final int MISSING_MEDIA_TYPE=102;
	public static final int MISSING_ID=103;
	public static final int BAD_INT_FORMAT=200;
	public static final int BAD_FLOAT_FORMAT=201;
}
