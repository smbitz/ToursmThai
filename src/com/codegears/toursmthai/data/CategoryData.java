package com.codegears.toursmthai.data;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class CategoryData {
	
	private String title;
	private String id;
	private ArrayList<SubCategoryData> SubCategory;
	
	public CategoryData(){
		SubCategory = new ArrayList<SubCategoryData>();
	}
	
	public void setDataFromXmlNode(Node node){
		title = node.getAttributes().getNamedItem( "title" ).getNodeValue();
		id = node.getAttributes().getNamedItem( "id" ).getNodeValue();
		
		for(int i = 0; i < node.getChildNodes().getLength(); i++){
			Node getNode = node.getChildNodes().item(i);
			
			if( getNode.getNodeName().equals( "subcategory" ) ){
				SubCategoryData newSubCategoryData = new SubCategoryData();
				newSubCategoryData.setDataFromXmlNode( getNode );
				SubCategory.add( newSubCategoryData );
			}
		}
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getId(){
		return id;
	}
	
	public ArrayList<SubCategoryData> getSubCategoryData(){
		return SubCategory;
	}
}
