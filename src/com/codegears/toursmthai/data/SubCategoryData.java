package com.codegears.toursmthai.data;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class SubCategoryData {
	
	private String title;
	private String id;
	private ArrayList<CategoryPlaceGroup> categoryPlaceGroup;
	
	public SubCategoryData(){
		categoryPlaceGroup = new ArrayList<CategoryPlaceGroup>();
	}
	
	public void setDataFromXmlNode(Node node){
		title = node.getAttributes().getNamedItem( "title" ).getNodeValue();
		id = node.getAttributes().getNamedItem( "id" ).getNodeValue();
		
		for(int i = 0; i < node.getChildNodes().getLength(); i++){
			Node getNode = node.getChildNodes().item(i);
			
			if( getNode.getNodeName().equals( "place_group" ) ){
				CategoryPlaceGroup newPlaceGroup = new CategoryPlaceGroup();
				newPlaceGroup.setDataFromXmlNode( getNode );
				categoryPlaceGroup.add( newPlaceGroup );
			}
		}
	}
	
	public String getId(){
		return id;
	}
	
	public ArrayList<CategoryPlaceGroup> getCategoryPlaceGroup(){
		return categoryPlaceGroup;
	}
}
