package TaskMgr;

import java.sql.ResultSet;
import CubeMgr.CubeBase.CubeQuery;

/**
 * @author  Asterix
 */
public abstract class ExtractionMethod {
   
    public Result Res;
    
    public ExtractionMethod(){
    	Res=new Result();
    }

    abstract public boolean setResult(ResultSet resultSet);
    abstract public Result getResult();
    abstract public String toString();
    abstract public void produceExtractionMethod(CubeQuery cubeQuery);
    abstract public boolean compareExtractionMethod(ExtractionMethod toCompare);
    
}
