package com.codegears.toursmthai.data;

import org.w3c.dom.Node;

public class CategoryPlaceGroup {
	
	private String title;
	private String id;
	
	public void setDataFromXmlNode(Node node){
		title = node.getAttributes().getNamedItem( "title" ).getNodeValue();
		id = node.getAttributes().getNamedItem( "id" ).getNodeValue();
	}
	
	public String getId(){
		return id;
	}
}
