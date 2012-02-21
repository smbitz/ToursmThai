package com.codegears.toursmthai.data;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.codegears.toursmthai.LoadListener;
import com.codegears.toursmthai.MyApp;
import com.codegears.toursmthai.util.NetworkThreadUtil;
import com.codegears.toursmthai.util.NetworkThreadUtil.NetworkThreadListener;

public class CategoryManager implements NetworkThreadListener {

	private static final String URL_GET_CATEGORY = "URL_GET_CATEGORY";
	
	private LoadListener listener;
	private MyApp app;
	private ArrayList<CategoryData> category;
	
	public CategoryManager(MyApp myApp) {
		this.app = myApp;
		category = new ArrayList<CategoryData>();
	}
	
	public void load(){
		String urlString = app.getConfig().get( URL_GET_CATEGORY ).toString();
		NetworkThreadUtil.getXml( urlString , this );
	}
	
	public ArrayList<CategoryData> getCategory(){
		return category;
	}
	
	public void setLoadListener( LoadListener loadlistener ) {
		this.listener = loadlistener;
	}

	private void onXmlComplete(Document document) {
		NodeList fetchXml = document.getDocumentElement().getElementsByTagName("category");
		for(int i = 0; i < fetchXml.getLength(); i++){
			CategoryData newCategory = new CategoryData();
			newCategory.setDataFromXmlNode(fetchXml.item(i));
			category.add(newCategory);
		}
	}
	
	public CategoryData getCategoryById( String getCategoryId ){
		for( CategoryData fetchCategory:category ){
			if( fetchCategory.getId().equals( getCategoryId ) ){
				return fetchCategory;
			}
		}
		
		return null;
	}
	
	public SubCategoryData getSubCategoryById( String getSubCategoryId ){
		for( CategoryData fetchCategory:category ){
			for( SubCategoryData fetchSubCategoryData:fetchCategory.getSubCategoryData() ){
				if( fetchSubCategoryData.getId().equals( getSubCategoryId ) ){
					return fetchSubCategoryData;
				}
			}
		}
		
		return null;
	}
	
	@Override
	public void onNetworkDocSuccess(String urlString, Document document) {
		onXmlComplete( document );
		listener.onLoadComplete( this );
	}

	@Override
	public void onNetworkRawSuccess(String urlString, String result) {
		
	}

	@Override
	public void onNetworkFail(String urlString) {
		
	}

}
