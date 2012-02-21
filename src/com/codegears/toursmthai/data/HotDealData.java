package com.codegears.toursmthai.data;

import org.w3c.dom.Node;

public class HotDealData {
	
	private String id;
	private String title;
	private String picture;
	private String description;
	
	public void setDataFromXmlNode(Node node){
		id = node.getAttributes().getNamedItem( "id" ).getNodeValue();
		title = node.getAttributes().getNamedItem( "title" ).getNodeValue();
		picture = node.getAttributes().getNamedItem( "picture" ).getNodeValue();
		description = node.getAttributes().getNamedItem( "description" ).getNodeValue();
	}
}
