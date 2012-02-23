package com.codegears.toursmthai.data;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class PlaceData {
	
	private String id;
	private String subcategory_id;
	private String title;
	private String main_picture;
	private String sub_picture;
	private String type;
	private String description;
	private String latitude;
	private String longitude;
	private String link;
	private ArrayList<HotDealData> hotDeal;
	
	public PlaceData(){
		hotDeal = new ArrayList<HotDealData>();
	}
	
	public void setDataFromXmlNode(Node node){
		id = node.getAttributes().getNamedItem( "id" ).getNodeValue();
		subcategory_id = node.getAttributes().getNamedItem( "subcategory_id" ).getNodeValue();
		title = node.getAttributes().getNamedItem( "title" ).getNodeValue();
		main_picture = node.getAttributes().getNamedItem( "main_picture" ).getNodeValue();
		sub_picture = node.getAttributes().getNamedItem( "sub_picture" ).getNodeValue();
		type = node.getAttributes().getNamedItem( "type" ).getNodeValue();
		description = node.getAttributes().getNamedItem( "description" ).getNodeValue();
		latitude = node.getAttributes().getNamedItem( "latitude" ).getNodeValue();
		longitude = node.getAttributes().getNamedItem( "longitude" ).getNodeValue();
		link = node.getAttributes().getNamedItem( "link" ).getNodeValue();
		
		for(int i = 0; i < node.getChildNodes().getLength(); i++){
			Node getNode = node.getChildNodes().item(i);
			
			if( getNode.getNodeName().equals( "hot_deal" ) ){
				HotDealData newHotDealData = new HotDealData();
				newHotDealData.setDataFromXmlNode( getNode );
				hotDeal.add( newHotDealData );
			}
		}
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public String getURL(){
		return this.link;
	}
	
	public String getSubPictureURL(){
		return this.sub_picture;
	}
	
	public String getMainPictureURL(){
		return this.main_picture;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getType(){
		return this.type;
	}
}
