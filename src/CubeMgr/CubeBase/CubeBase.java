package CubeMgr.CubeBase;

import CubeMgr.StarSchema.Database;
import CubeMgr.StarSchema.DimensionTable;
import CubeMgr.StarSchema.FactTable;
import CubeMgr.StarSchema.Table;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CubeBase {
    public String name;
    public Database DB;
    public List<Dimension> dimensions;
    public List<BasicStoredCube> BasicCubes;
    
    public CubeBase (){
    	DB=new Database();
    	dimensions=new ArrayList<>();
    	BasicCubes=new ArrayList<>();
    }
    
    /*public CubeBase(String User,String Pass){
    	DB=new Database();
    	dim=new ArrayList<>();
    	cubes=new ArrayList<>();
    	DB.setUsername(User);
    	DB.setPassword(Pass);
    }*/
    
    public void registerCubeBase(String filename){
        name=filename;
        
        DB.setDBName(name);
        DB.setUsername("root");
        DB.setPassword("");
        DB.registerDatabase();
        DB.GenerateTableList();
        //DB.PrintTableList();
    }
    
    public void addDimension(String nameDim){
    	dimensions.add(new Dimension(nameDim));
    }

	public void addDimensionTbl(String dimensionTbl) {
		Table tmp_tbl=DB.getDBTableInstance(dimensionTbl);
		DimensionTable dm=new DimensionTable(tmp_tbl.TblName);
		dm.LstAttr.addAll(tmp_tbl.LstAttr);
		this.getLastInsertedDimension().setDimTbl(dm);
	}

	public void addLevels(ArrayList<String> fld_Name,ArrayList<String> customFld_Name) {
		
	}

	public void setDimensionLinearHierachy(ArrayList<String> hierachylst,List<String> fld_tbl,List<String> custFld_name) {
		Dimension tmp;
		tmp = this.getLastInsertedDimension();
		LinearHierarchy LinHier=new LinearHierarchy();
		LinHier.setDimension(tmp);
		for(int i=0;i<hierachylst.size();i++){
			if(custFld_name.equals(hierachylst.get(i))){
				Level lvl=new Level(i,hierachylst.get(i));
				
				String temp_string=fld_tbl.get(i).replace(".","#");
				String[] tmp_str=temp_string.split("#");
				
				LevelAttribute lvlattr=new LevelAttribute(tmp_str[1],tmp_str[0]);
				lvlattr.setLevel(lvl);
				lvlattr.setAttribute(DB.getFieldOfSqlTable(tmp_str[0],tmp_str[1]));
				
				//lvl.setLevelAttribute(lvlattr);
				lvl.addLevelAttribute(lvlattr);
				lvl.setLinearHierarchy(LinHier);
				
				LinHier.lvls.add(lvl);
			}
		}
	}
	
	
	public Dimension getLastInsertedDimension(){
		return dimensions.get(dimensions.size()-1);
	}

	public void addCube(String name_creation) {
		BasicCubes.add(new BasicStoredCube(name_creation));
		
	}

	public void addSqlRelatedTbl(String sqltable) {
		Table tmp_tbl=DB.getDBTableInstance(sqltable);
		BasicStoredCube tmp=BasicCubes.get(BasicCubes.size()-1);
		FactTable fctbl=new FactTable(tmp_tbl.TblName);
		fctbl.LstAttr.addAll(tmp_tbl.LstAttr);
		tmp.setFactTable(fctbl);
	}

	public void setCubeDimension(ArrayList<String> dimensionlst,ArrayList<String>  DimemsionRefField) {
		BasicStoredCube last_cube=BasicCubes.get(BasicCubes.size()-1);
		int i=0;
		for(String item : dimensionlst){
			int tmp=findDimensionIdByName(item);
			if(tmp==-1){
				System.err.println("Error with Dimension");
				System.exit(1);
			}
			last_cube.addDimension(this.dimensions.get(tmp));
			last_cube.addDimensionRefField(DimemsionRefField.get(i));
			i++;
		}
	}
	
	
	public Connection getSqlConnection(){
		return DB.getConnection();
	}
	
	private Integer findDimensionIdByName(String nameDimension){
		int ret_val=-1;
		int i=0;
		while(ret_val==-1 && i<this.dimensions.size()){
			if(this.dimensions.get(i).name.equals(nameDimension)) ret_val=i;
			i++;
		}
		return ret_val;
	}

	public boolean returnIfTableIsDimensionTbl(String table) {
		boolean ret_value=true;
		for(BasicStoredCube basiccube: this.BasicCubes){
			if(basiccube.FactTable().TblName==table) ret_value=false;
		}
		return ret_value;
	}
}
