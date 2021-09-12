package com.chochae.afes.offer;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chochae.afes.resource.ResourceManager;
import com.chochae.afes.resource.dto.UserAssetInfo;
import com.chochae.afes.user.UserManager;
import com.chochae.afes.user.model.LoginForm;
import com.chochae.afes.user.model.UserSettingInfo;

public class OfferDealerController {

	@RequestMapping(value = "/autooffer", method = RequestMethod.GET)
	public String autoOffer(HttpServletRequest request, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		LoginForm userData = (LoginForm) request.getSession().getAttribute("LOGGEDIN_USER");
		UserSettingInfo setting = UserManager.getUserSetting(userData.getUsername());
		setting.setUserId(userData.getUsername());
		
		List<UserAssetInfo> assetList = ResourceManager.getUserAssets(userData.getUsername());
		try {
			String str = mapper.writeValueAsString(setting);
			model.addAttribute("userSetting", str);
			String assetStr = mapper.writeValueAsString(assetList);
			model.addAttribute("marketAsset", assetStr);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/operator/autooffer";
	}	
	
}
