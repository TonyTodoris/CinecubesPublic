/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CubeMgr;

import java.util.ArrayList;

import CubeMgr.CubeBase.CubeBase;

/**
 *
 * @author Asterix
 */
public class CubeMgr {
    
    public CubeBase CBase;
    //public MetaData MD;
    public CubeMgr(){
    	CBase=new CubeBase();
    }
    
    public void CreateCubeBase(String filename){
    	CBase.registerCubeBase(filename);
    }
    
	public void InsertionDimensionLvl(String dimensionName,
									  String dimensionTbl, 
									  ArrayList<String> fld_Name,
									  ArrayList<String> customFld_Name, 
									  ArrayList<String> hierachylst) {
		
		CBase.addDimension(dimensionName);
		CBase.addDimensionTbl(dimensionTbl);
		CBase.setDimensionLinearHierachy(hierachylst,fld_Name,customFld_Name);
	}

	public void InsertionCube(String name_creation, String sqltable,ArrayList<String> dimensionlst,ArrayList<String>  DimemsionRefField) {
		
		CBase.addCube(name_creation);
		CBase.addSqlRelatedTbl(sqltable);
		CBase.setCubeDimension(dimensionlst,DimemsionRefField);
	}
       
}