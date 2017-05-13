package SparkStreaming;

import java.util.List;
import java.util.Map;

import com.sagittarius.bean.result.BooleanPoint;
import com.sagittarius.bean.result.DoublePoint;
import com.sagittarius.bean.result.FloatPoint;
import com.sagittarius.bean.result.GeoPoint;
import com.sagittarius.bean.result.IntPoint;
import com.sagittarius.bean.result.LongPoint;
import com.sagittarius.bean.result.StringPoint;

import edu.thss.platform.pas.userfunc.UserFunction;


public class TempFaultDist extends UserFunction {

	@Override
	public Map<String, Object> runOne(List<String> deviceList, Map<String, Map<String, List<FloatPoint>>> floatInput,
			Map<String, Map<String, List<DoublePoint>>> doubleInput,
			Map<String, Map<String, List<StringPoint>>> stringInput, Map<String, Map<String, List<IntPoint>>> intInput,
			Map<String, Map<String, List<LongPoint>>> longInput,
			Map<String, Map<String, List<BooleanPoint>>> booleanInput,
			Map<String, Map<String, List<GeoPoint>>> geoInput, long startTime, long endTime) {
		if(deviceList==null)
			return output;
		if(deviceList.size()==0)
			return output;
		String deviceID = deviceList.get(0);
		float distSum = 0;
		float tempFaultDistSum = 0;
		if(floatInput!=null&&floatInput.get(deviceID)!=null&&floatInput.get(deviceID).get("高精度总里程(EE)")!=null&&floatInput.get(deviceID).get("发动机进气歧管1温度")!=null){
			List<FloatPoint> distList = floatInput.get(deviceID).get("高精度总里程(EE)");
			List<FloatPoint> tempList = floatInput.get(deviceID).get("发动机进气歧管1温度");
			
			//去除空数据
			//前后相邻做差，计算有效里程
			for(int i = 1; i < distList.size(); i++){
				 float distDiff = distList.get(i).getValue() - distList.get(i-1).getValue();
				 long timeDiff = distList.get(i).getPrimaryTime() - distList.get(i-1).getPrimaryTime();
				 if(distDiff >= 0 && distDiff < 0.042*timeDiff){
					 distSum = distSum + distDiff;
				 }
			}
					
			//计算温度异常总里程
			int i = 1;
			int j = 1;
			while( i < distList.size() && j < tempList.size() ){
				if( distList.get(i).getPrimaryTime() == tempList.get(j).getPrimaryTime() ){
					if( tempList.get(j).getValue() >= 30 ){
						float distDiff = distList.get(i).getValue() - distList.get(i-1).getValue();
						long timeDiff = distList.get(i).getPrimaryTime() - distList.get(i-1).getPrimaryTime();
						if(distDiff >= 0 && distDiff < 0.042*timeDiff){
							tempFaultDistSum = tempFaultDistSum + distDiff;
						}
					}
					i++;
					j++;
				}else if( distList.get(i).getPrimaryTime() > tempList.get(j).getPrimaryTime() )
					j++;
				else
					i++;
			}
		}

		//存入计算结果
		output.put("运行总里程", distSum);
		output.put("温度异常总里程", tempFaultDistSum);
		return output;
	}

}