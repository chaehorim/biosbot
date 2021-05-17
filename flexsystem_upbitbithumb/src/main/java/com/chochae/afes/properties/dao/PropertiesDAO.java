package com.chochae.afes.properties.dao;

import com.chochae.afes.properties.modal.PropertyInfo;

public interface PropertiesDAO {

	public PropertyInfo selectPrperties() throws Exception ;
	
	public boolean insertProperties(PropertyInfo prop) throws Exception;
}
